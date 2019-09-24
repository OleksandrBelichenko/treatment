package hospital.controller;


import hospital.model.procedure.Procedure;
import hospital.service.ProcedureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Oleksandr Belichenko
 */
@Controller
@RequestMapping(value = "/procedure")
public class ProcedureController {

    final static Logger logger = LoggerFactory.getLogger(ProcedureController.class);

    @Autowired
    private ProcedureService procedureService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String addPatient(@RequestParam String name, @RequestParam String description, @RequestParam Double price, Model model){
        model.addAttribute("procedures", new ArrayList<>());
        procedureService.addProcedure(name, description, price);
        return "main";
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public void updatePatient(@RequestParam String name, @RequestParam(required = false) String newName,
                              @RequestParam(required = false) String description, @RequestParam(required = false) Double price){
        procedureService.updateProcedure(name, newName, description, price);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void removePatient(@RequestParam String name){
        procedureService.removeProcedure(name);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public Procedure getProcedureByName(@RequestParam String name){
        return procedureService.getProcedureByName(name);
    }

    @RequestMapping(value = "/getProceduresByName", method = RequestMethod.GET)
    public List<Procedure> getProceduresByName(@RequestParam String name){
        return procedureService.getProceduresByName(name);
    }

    @RequestMapping(value = "/getPrice", method = RequestMethod.GET)
    public Double getPrice(@RequestParam String name){
        return procedureService.getPrice(name);
    }

    @GetMapping("/allProcedures")
    public String allProcedures(Model model){
        model.addAttribute("procedures", procedureService.getAll());
        return "addAssigment";
    }

    @GetMapping("/all")
    public Iterable<Procedure> getProcedures(Model model) {
        Iterable<Procedure> procedures = procedureService.getAll();
        model.addAttribute("procedures", procedures);
        return procedures;
    }
}
