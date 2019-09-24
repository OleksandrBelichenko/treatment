package hospital.service;

import hospital.model.procedure.Procedure;
import hospital.model.procedure.ProcedureDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Oleksandr Belichenko
 */
@Service
public class ProcedureService {
    @Autowired
    private ProcedureDao procedureDao;
    @Autowired
    private GeneralService generalService;

    final static Logger logger = LoggerFactory.getLogger(ProcedureService.class);

    public void addProcedure(String name, String description, Double price){
        Procedure procedure = new Procedure();
        procedure.setDescription(description);
        procedure.setName(name);
        procedure.setPrice(price);
        procedureDao.addProcedure(procedure);
    }

    public void updateProcedure(String name, String newName, String description, Double price){
        Procedure procedure = getProcedureByName(name);
        if (procedure == null){
            addProcedure(name, description, price);
            return;
        }

        if (generalService.isValidStringData(name)){
            procedure.setName(newName);
        }

        procedure.setDescription(description);

        if (generalService.isValidStringData(price)){
            procedure.setPrice(price);
        }

        procedureDao.updateProcedure(procedure);
    }

    public void removeProcedure(String name){
        Procedure procedure = getProcedureByName(name);
        procedureDao.removeProcedure(procedure);
    }

    public Procedure getProcedureByName(String name){
        return procedureDao.getProcedureByName(name);
    }

    public List<Procedure> getProceduresByName(String name){
        return procedureDao.getProceduresByName(name);
    }

    public Double getPrice(String name){
        Procedure procedure = getProcedureByName(name);
        return procedure.getPrice();
    }

    public Iterable<Procedure> getAll(){
        Iterable<Procedure> procedures = procedureDao.findAll();
        for (Procedure procedure : procedures){
            logger.info(procedure.getName());
        }
        return procedureDao.findAll();
    }
}
