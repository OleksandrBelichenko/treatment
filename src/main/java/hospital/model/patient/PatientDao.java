package hospital.model.patient;

import hospital.model.common.CommonEntityDao;

import java.util.List;

/**
 * @author Oleksandr Belichenko
 */
public interface PatientDao extends CommonEntityDao<Long, Patient> {
    void addPatient(Patient patient);
    void updatePatient(Patient patient);
    List<Patient> getPatientsByFIO(String name, String secondName, String surname);
    List<Patient> getPatientsByDiagnosis(String diagnosis);
    void removePatient(Patient patient);
}
