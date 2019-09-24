package hospital.model.assigments;

import hospital.model.common.CommonEntityDao;
import hospital.model.patient.Patient;
import hospital.model.procedure.Procedure;

import java.util.List;

/**
 * @author Oleksandr Belichenko
 */
public interface AssigmentsDao extends CommonEntityDao<Long, Assigments> {
    void addAssigments(Assigments assigments);
    void updateAssigments(Assigments assigments);
    void removeAssigments(Assigments assigments);
    List<Assigments> getAssigmentsByPatient(Patient patient);
    Assigments getAssigments(String dateTime);
    List<Assigments> getAssigmentsByProcedure(Procedure procedure);
}
