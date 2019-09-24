package hospital.model.doctor;


import hospital.model.common.CommonEntityDaoImpl;
import hospital.service.GeneralService;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Oleksandr Belichenko
 */
@Repository
public class DoctorDaoImpl extends CommonEntityDaoImpl<Long, Doctor> implements DoctorDao {

    protected DoctorDaoImpl(){super(Doctor.class);}

    @Autowired
    private GeneralService generalService;

    @Override
    public void addDoctor(Doctor doctor) {
        saveOrUpdate(doctor);
    }

    @Override
    public void updateDoctor(Doctor doctor) {
        saveOrUpdate(doctor);
    }

    @Override
    public List<Doctor> getDoctorsByFIO(String name, String secondName, String surname) {
        List<Doctor> names = new ArrayList<>();
        List<Doctor> secondNames = new ArrayList<>();
        List<Doctor> surnames = new ArrayList<>();


        if (generalService.isValidStringData(name)){
            names = findAllByLikeAttributeCriteria("name", name);
        }
        if (generalService.isValidStringData(secondName)){
            secondNames = findAllByLikeAttributeCriteria("secondName", secondName);
        }
        if (generalService.isValidStringData(surname)){
            surnames = findAllByLikeAttributeCriteria("surname", surname);
        }

        List<Doctor> answer = new ArrayList<>();
        answer.addAll(names);
        answer.addAll(secondNames);
        answer.addAll(surnames);
        return answer.stream().distinct().collect(Collectors.toList());
    }

    @Override
    public List<Doctor> getDoctorsBySpecialization(String specialization) {
        return findAllByLikeAttributeCriteria("specialization", specialization);
    }

    @Override
    public void removeDoctor(Doctor doctor) {
        delete(doctor.getId());
    }

    @Override
    public List <String> getAllSpecializations() {
        String sqlQuery = "select specialization from Doctor";
        Query query = getSession().createSQLQuery(sqlQuery);
        return query.list();
    }
}
