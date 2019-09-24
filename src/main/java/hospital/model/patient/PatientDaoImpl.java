package hospital.model.patient;

import hospital.model.common.CommonEntityDaoImpl;
import hospital.service.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Oleksandr Belichenko
 */
@Repository
public class PatientDaoImpl extends CommonEntityDaoImpl<Long, Patient> implements PatientDao {

    protected PatientDaoImpl(){super(Patient.class);}

    @Autowired
    private GeneralService generalService;

    @Override
    public void addPatient(Patient patient) {
        saveOrUpdate(patient);
    }

    @Override
    public void updatePatient(Patient patient) {
        saveOrUpdate(patient);
    }

    @Override
    public List<Patient> getPatientsByFIO(String name, String secondName, String surname) {
        List<Patient> names = new ArrayList<>();
        List<Patient> secondNames = new ArrayList<>();
        List<Patient> surnames = new ArrayList<>();


        if (generalService.isValidStringData(name)){
            names = findAllByLikeAttributeCriteria("name", name);
        }
        if (generalService.isValidStringData(secondName)){
            secondNames = findAllByLikeAttributeCriteria("secondName", secondName);
        }
        if (generalService.isValidStringData(surname)){
            surnames = findAllByLikeAttributeCriteria("surname", surname);
        }

        List<Patient> answer = new ArrayList<>();
        answer.addAll(names);
        answer.addAll(secondNames);
        answer.addAll(surnames);
        return answer.stream().distinct().collect(Collectors.toList());
    }

    @Override
    public List<Patient> getPatientsByDiagnosis(String diagnosis) {
        return findAllByLikeAttributeCriteria("diagnosis", diagnosis);
    }

    @Override
    public void removePatient(Patient patient) {
        delete(patient.getId());
    }
}
