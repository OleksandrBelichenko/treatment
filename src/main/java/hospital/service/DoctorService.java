package hospital.service;

import hospital.model.doctor.Doctor;
import hospital.model.doctor.DoctorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Oleksandr Belichenko
 */
@Service
public class DoctorService {

    @Autowired
    private DoctorDao doctorDao;
    @Autowired
    private GeneralService generalService;

    public void addDoctor(String name, String secondName, String surname, String specialization) {
        Doctor doctor = new Doctor();
        doctor.setName(name);
        doctor.setSecondName(secondName);
        doctor.setSurname(surname);
        doctor.setSpecialization(specialization);
        doctorDao.addDoctor(doctor);
    }

    public void updateDoctor(String name, String secondName, String surname, String specialization) {
        List<Doctor> doctors = getDoctorsByFIO(null, null, surname);

        Doctor doctor = null;

        if (doctors.size() == 1) {
            doctor = doctors.get(0);
        } else if (doctors.size() > 1 && !name.isEmpty()) {
            if (filterDoctors(doctors, name).isPresent()){
                doctor = filterDoctors(doctors, name).get();
            }
        } else if (doctors.size() > 1 && !secondName.isEmpty()) {
            if (filterDoctors(doctors, secondName).isPresent()){
                doctor = filterDoctors(doctors, secondName).get();
            }
        } else {
            if (filterDoctors(doctors, surname).isPresent()){
                addDoctor(name, secondName, surname, specialization);
            }
            return;
        }

        if (generalService.isValidStringData(name)){
            doctor.setName(name);
        }

        if (generalService.isValidStringData(secondName)){
            doctor.setSecondName(secondName);
        }

        if (generalService.isValidStringData(surname)){
            doctor.setSurname(surname);
        }

        if (generalService.isValidStringData(specialization)){
            doctor.setSpecialization(specialization);
        }

        doctorDao.updateDoctor(doctor);
    }

    private Optional<Doctor> filterDoctors(List<Doctor> doctors, String filter){
        Doctor doctor = null;
        doctors = doctors.stream().filter(doc -> doc.getName().equals(filter)).collect(Collectors.toList());
        if (doctors.size() == 1) {
            doctor = doctors.get(0);
        }
        return Optional.of(doctor);
    }

    public void removeDoctor(String name, String secondName, String surname){
        Doctor doctor = getDoctorsByFIO(name, secondName, surname).get(0);
        doctorDao.removeDoctor(doctor);
    }

    public List<Doctor> getDoctorsByFIO(String name, String secondName, String surname){
        if ("".equals(secondName)){
            secondName = null;
        }
        if ("".equals(surname)){
            surname = null;
        }
        if ("".equals(name)){
            name = null;
        }
        return doctorDao.getDoctorsByFIO(name, secondName, surname);
    }

    public List<Doctor> getDoctorsBySpecialization(String specialization){
        return doctorDao.getDoctorsBySpecialization(specialization);
    }

    public Iterable<Doctor> getAllDoctors(){
        return doctorDao.findAll();
    }

    public List <String> getAllSpecializations(){
        return doctorDao.getAllSpecializations();
    }
}
