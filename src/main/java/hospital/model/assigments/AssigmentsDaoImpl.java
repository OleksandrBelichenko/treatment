package hospital.model.assigments;

import hospital.model.common.CommonEntityDaoImpl;
import hospital.model.patient.Patient;
import hospital.model.procedure.Procedure;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Oleksandr Belichenko
 */
@Repository
public class AssigmentsDaoImpl extends CommonEntityDaoImpl<Long, Assigments> implements AssigmentsDao {
    protected AssigmentsDaoImpl(){super(Assigments.class);}

    @Override
    public void addAssigments(Assigments assigments) {
        saveOrUpdate(assigments);
    }

    @Override
    public void updateAssigments(Assigments assigments) {
        saveOrUpdate(assigments);
    }

    @Override
    public void removeAssigments(Assigments assigments) {
        delete(assigments.getId());
    }

    @Override
    public List<Assigments> getAssigmentsByPatient(Patient patient) {
        return findAllBy("patient", patient);
    }

    @Override
    public Assigments getAssigments(String dateTime) {
        return findBy("date", dateTime).orNull();
    }

    @Override
    public List<Assigments> getAssigmentsByProcedure(Procedure procedure) {
        return findAllBy("procedure", procedure);
    }
}
