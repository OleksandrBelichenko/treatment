package hospital.model.procedure;

import hospital.model.common.CommonEntityDao;

import java.util.List;

/**
 * @author Oleksandr Belichenko
 */
public interface ProcedureDao extends CommonEntityDao<Long, Procedure> {
    void addProcedure(Procedure procedure);
    void updateProcedure(Procedure procedure);
    void removeProcedure(Procedure procedure);
    List<Procedure> getProceduresByName(String name);
    Double getPrice(String name);
    Procedure getProcedureByName(String name);
}
