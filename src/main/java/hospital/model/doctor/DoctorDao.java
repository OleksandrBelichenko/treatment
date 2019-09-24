package hospital.model.doctor;

import hospital.model.common.CommonEntityDao;

import java.util.List;

/**
 * @author Oleksandr Belichenko
 */
public interface DoctorDao extends CommonEntityDao<Long, Doctor> {
    void addDoctor(Doctor doctor);
    void updateDoctor(Doctor doctor);
    List<Doctor> getDoctorsByFIO(String name, String secondName, String surname);
    List<Doctor> getDoctorsBySpecialization(String specialization);
    void removeDoctor(Doctor doctor);
    List <String> getAllSpecializations();
}
