package hospital.model.procedure;

import hospital.model.common.CommonEntityDaoImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Oleksandr Belichenko
 */
@Repository
public class ProcedureDaoImpl extends CommonEntityDaoImpl<Long, Procedure> implements ProcedureDao {

    protected ProcedureDaoImpl(){super(Procedure.class);}

    @Override
    public void addProcedure(Procedure procedure) {
        saveOrUpdate(procedure);
    }

    @Override
    public void updateProcedure(Procedure procedure) {
        saveOrUpdate(procedure);
    }

    @Override
    public void removeProcedure(Procedure procedure) {
        delete(procedure.getId());
    }

    @Override
    public List<Procedure> getProceduresByName(String name) {
        return findAllByLikeAttributeCriteria("name", name);
    }

    @Override
    public Double getPrice(String name) {
        return getProceduresByName(name).stream().findFirst().orElse(null).getPrice();
    }

    @Override
    public Procedure getProcedureByName(String name){
        return findBy("name", name).orNull();
    }
}
