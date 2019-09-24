package hospital.controller;


import hospital.model.procedure.Procedure;
import hospital.service.ProcedureService;
import hospital.service.WorkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @author Oleksandr Belichenko
 */
@Controller
@RequestMapping(value = "/work")
public class WorkController {

    final static Logger logger = LoggerFactory.getLogger(WorkController.class);

    @Autowired
    private WorkService workService;
    @Autowired
    private ProcedureService procedureService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void addWork(@RequestParam String date){
        workService.addWork(date);
    }


    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void removeWork(@RequestParam String date){
        workService.removeWork(date);
    }

    @GetMapping("/getWorks")
    public String getWorks(@RequestParam String procedureName, Model model){
        Iterable<Procedure> procedures = procedureService.getAll();
        model.addAttribute("procedures", procedures);
        model.addAttribute("works", workService.getWorks(procedureName));
        return "reportProcedure";
    }

    @GetMapping("/getWorksByPatient")
    public String getWorksByPatient(@RequestParam String name, @RequestParam String secondName, @RequestParam String surname, Model model){
        model.addAttribute("works", workService.getWorksByPatient(name, secondName, surname));
        return "reportPatient";
    }
}