package hospital.controller;

import hospital.model.assigments.Assigments;
import hospital.service.AssigmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Oleksandr Belichenko
 */
@Controller
@RequestMapping(value = "/assigments")
public class AssigmentsController {

    @Autowired
    private AssigmentsService assigmentsService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void createAssigments(@RequestParam String doctorName, @RequestParam String doctorSecondName,
                              @RequestParam String doctorSurname, @RequestParam String patientName,
                              @RequestParam String patientSecondName, @RequestParam String patientSurname,
                              @RequestParam String materialNames, @RequestParam String procedureName,
                              @RequestParam String medicationNames, @RequestParam String date){
        assigmentsService.addAssigments(doctorName, doctorSecondName, doctorSurname, patientName, patientSecondName,
                patientSurname, materialNames, procedureName, medicationNames, date);
    }

    @RequestMapping(value = "/addAssigment", method = RequestMethod.POST)
    public String addAssigment(@RequestParam String doctor, @RequestParam String patient,
                              @RequestParam String materialName, @RequestParam String procedureName,
                              @RequestParam String medicationName, @RequestParam String date){
        assigmentsService.addAssigment(doctor, patient, materialName, procedureName, medicationName, date);
        return "main";
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public void updateAssigments(@RequestParam(required = false) String doctorName, @RequestParam(required = false) String doctorSecondName,
                                 @RequestParam(required = false) String doctorSurname, @RequestParam(required = false) String patientName,
                                 @RequestParam(required = false) String patientSecondName, @RequestParam(required = false) String patientSurname,
                                 @RequestParam(required = false) String materialNames, @RequestParam(required = false) String procedureName,
                                 @RequestParam(required = false) String medicationNames, @RequestParam String date){
        assigmentsService.updateAssigments(doctorName, doctorSecondName, doctorSurname, patientName, patientSecondName,
                patientSurname, materialNames, procedureName, medicationNames, date);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void removeAssigments(@RequestParam String dateTime){
        assigmentsService.removeAssigments(dateTime);
    }

    @RequestMapping(value = "/getAssigmentsByPatient", method = RequestMethod.GET)
    public List<Assigments> getAssigmentsByPatient(@RequestParam(required = false) String patientName, @RequestParam(required = false) String patientSecondName,
                                            @RequestParam(required = false) String patientSurname){
        return assigmentsService.getAssigmentsByPatient(patientName, patientSecondName, patientSurname);
    }

    @RequestMapping(value = "/getAssigmentsByProcedure", method = RequestMethod.GET)
    public List<Assigments> getAssigmentsByProcedure(@RequestParam String procedureName){
        return assigmentsService.getAssigmentsByProcedure(procedureName);
    }

    @RequestMapping(value = "/getAssigments", method = RequestMethod.GET)
    public Assigments getAssigments(@RequestParam String dateTime){
        return assigmentsService.getAssigments(dateTime);
    }

    @GetMapping("/all")
    public List<Assigments> getAll(){

        return assigmentsService.getAll();
    }

}
