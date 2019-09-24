package hospital.service;

import hospital.model.assigments.Assigments;
import hospital.model.patient.Patient;
import hospital.model.patient.PatientDao;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Oleksandr Belichenko
 */
@Service
public class PatientService {

    @Autowired
    private PatientDao patientDao;
    @Autowired
    private GeneralService generalService;
    @Autowired
    private AssigmentsService assigmentsService;

    public void addPatient(String name, String secondName, String surname, String diagnosis) {
        Patient patient = new Patient();
        patient.setName(name);
        patient.setSecondName(secondName);
        patient.setSurname(surname);
        patient.setDiagnosis(diagnosis);
        patientDao.addPatient(patient);
    }

    public void updatePatient(String name, String secondName, String surname, String diagnosis) {
        List<Patient> patients = getPatientsByFIO(null, null, surname);

        Patient patient = null;

        if (patients.size() == 1) {
            patient = patients.get(0);
        } else if (patients.size() > 1 && !name.isEmpty()) {
            if (filterPatients(patients, name).isPresent()){
                patient = filterPatients(patients, name).get();
            }
        } else if (patients.size() > 1 && !secondName.isEmpty()) {
            if (filterPatients(patients, secondName).isPresent()){
                patient = filterPatients(patients, secondName).get();
            }
        } else {
            addPatient(name, secondName, surname, diagnosis);
            return;
        }

        if (generalService.isValidStringData(name)){
            patient.setName(name);
        }

        if (generalService.isValidStringData(secondName)){
            patient.setSecondName(secondName);
        }

        if (generalService.isValidStringData(surname)){
            patient.setSurname(surname);
        }

        if (generalService.isValidStringData(diagnosis)){
            patient.setDiagnosis(diagnosis);
        }

        patientDao.updatePatient(patient);
    }

    private Optional<Patient> filterPatients(List<Patient> patients, String filter){
        Patient patient = null;
        patients = patients.stream().filter(doc -> doc.getName().equals(filter)).collect(Collectors.toList());
        if (patients.size() == 1) {
            patient = patients.get(0);
        }
        return Optional.of(patient);
    }

    public void removePatient(String name, String secondName, String surname){
        Patient patient = getPatientsByFIO(name, secondName, surname).get(0);
        patientDao.removePatient(patient);
    }

    public List<Patient> getPatientsByFIO(String name, String secondName, String surname){
        if ("".equals(secondName)){
            secondName = null;
        }
        if ("".equals(surname)){
            surname = null;
        }
        if ("".equals(name)){
            name = null;
        }
        return patientDao.getPatientsByFIO(name, secondName, surname);
    }

    public List<Patient> getPatientsByDiagnosis(String diagnosis){
        return patientDao.getPatientsByDiagnosis(diagnosis);
    }

    public Iterable<Patient> getAllPatients(){
        return patientDao.findAll();
    }

    public List<Patient> getByPeriod(String dateBefore, String dateAfter){
        List<Assigments> assigments = assigmentsService.getAll();
        DateTime beforeDate = generalService.convertStringToDateTime(dateBefore);
        DateTime afterDate = generalService.convertStringToDateTime(dateAfter);

        List<Patient> patients = new ArrayList<>();
        for (Assigments assigment : assigments){
            DateTime date = generalService.convertStringToDateTime(assigment.getDate());
            if (beforeDate.isBefore(date) && afterDate.isAfter(date)){
                patients.add(assigment.getPatient());
            }
        }
        return patients;
    }
}
