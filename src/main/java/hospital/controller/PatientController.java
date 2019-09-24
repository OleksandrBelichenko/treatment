package hospital.controller;


import hospital.model.patient.Patient;
import hospital.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Objects;

/**
 * @author Oleksandr Belichenko
 */
@Controller
@RequestMapping(value = "/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String addPatient(@RequestParam String name, @RequestParam String secondName,
                          @RequestParam String surname, @RequestParam String diagnosis, Model model){
        model.addAttribute("patients", new ArrayList<>());
        patientService.addPatient(name, secondName, surname, diagnosis);
        return "patients";
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public void updatePatient(@RequestParam(required = false) String name, @RequestParam(required = false) String secondName,
                             @RequestParam(required = false) String surname, @RequestParam(required = false) String diagnosis){
        patientService.updatePatient(name, secondName, surname, diagnosis);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void removePatient(@RequestParam(required = false) String name, @RequestParam(required = false) String secondName,
                             @RequestParam(required = false) String surname){
        patientService.removePatient(name, secondName, surname);
    }

    @RequestMapping(value = "/getPatientsByFIO", method = RequestMethod.GET)
    public String getPatientsByFIO(@RequestParam(required = false) String name, @RequestParam(required = false) String secondName,
                                   @RequestParam(required = false) String surname, Model model){
        if ((Objects.isNull(secondName) || secondName.isEmpty()) && (Objects.isNull(name) || name.isEmpty()) && (Objects.isNull(surname) || surname.isEmpty())){
            model.addAttribute("patients", patientService.getAllPatients());
        } else {
            model.addAttribute("patients", patientService.getPatientsByFIO(name, secondName, surname));
        }
        return "patients";
    }

    @RequestMapping(value = "/getPatientsByDiagnosis", method = RequestMethod.GET)
    public String getPatientsByDiagnosis(@RequestParam String diagnosis, Model model){
        if (null == diagnosis || "".equals(diagnosis)){
            model.addAttribute("patients", patientService.getAllPatients());
        }
        else {
            model.addAttribute("patients", patientService.getPatientsByDiagnosis(diagnosis));
        }
        return "patients";
    }

    @GetMapping("/all")
    public Iterable<Patient> getPatients(Model model) {
        Iterable<Patient> patients = patientService.getAllPatients();
        model.addAttribute("patients", patients);
        return patients;
    }

    @GetMapping("/allPatients")
    public String allPatients(Model model) {
        Iterable<Patient> patients = patientService.getAllPatients();
        model.addAttribute("patients", patients);
        return "addAssigment";
    }

    @GetMapping("/getByPeriod")
    public String getByPeriod(@RequestParam String dateBefore, @RequestParam String dateAfter, Model model){
        model.addAttribute("patients", patientService.getByPeriod(dateBefore, dateAfter));
        return "reportPeriod";
    }

}
