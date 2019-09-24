package hospital.service;

import hospital.model.assigments.Assigments;
import hospital.model.material.Material;
import hospital.model.medication.Medication;
import hospital.model.procedure.Procedure;
import hospital.model.work.Work;
import hospital.model.work.WorkDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Oleksandr Belichenko
 */
@Service
public class WorkService {

    @Autowired
    private WorkDao workDao;
    @Autowired
    private AssigmentsService assigmentsService;

    final static Logger logger = LoggerFactory.getLogger(WorkService.class);

    public void addWork(String date){
        Assigments assigments = assigmentsService.getAssigments(date);
        addWork(assigments);
    }

    public void addWork(Assigments assigments){
        Double income = getIncome(assigments.getProcedure());
        Double consumption = getConsumption(assigments.getMaterial(), assigments.getMedication());

        Work work = new Work();
        work.setAssigments(assigments);
        work.setConsumption(consumption);
        work.setIncome(income);
        work.setProfit(income, consumption);

        workDao.addWork(work);
    }

    public void removeWork(String date){
        workDao.removeWork(getWorkByAssigments(date));
    }

    public Work getWorkByAssigments(String date){
        Assigments assigments = assigmentsService.getAssigments(date);
        return workDao.getWorkByAssigments(assigments);
    }

    private Double getIncome(Procedure procedure){
        return procedure.getPrice();
    }

    private Double getConsumption(Material material, Medication medication){
        return material.getPrice() + medication.getPrice();
    }

    public List<Work> getWorks(String procedure){
        List<Assigments> assigments = assigmentsService.getAssigmentsByProcedure(procedure);

        List<Work> works = new ArrayList<>();
        for (Assigments assigment : assigments){
            logger.info(assigment.toString());
            Work work = getWorkByAssigments(assigment.getDate());
            if (work != null){
                works.add(work);
            }
        }
        logger.info(works.toString());
        return works;
    }


    public List<Work> getWorksByPatient(String name, String secondName, String surname){
        List<Work> works = new ArrayList<>();


        List<Assigments> assigments = assigmentsService.getAssigmentsByPatient(name, secondName, surname);

        if (assigments != null){
            for (Assigments assigment : assigments){
                Work work = getWorkByAssigments(assigment.getDate());
                if (work != null){
                    works.add(work);
                }
            }
        }
        return works;
    }

}
