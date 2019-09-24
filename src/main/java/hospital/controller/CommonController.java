package hospital.controller;


import hospital.model.doctor.Doctor;
import hospital.model.material.Material;
import hospital.model.medication.Medication;
import hospital.model.patient.Patient;
import hospital.model.procedure.Procedure;
import hospital.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Map;


/**
 * @author Oleksandr Belichenko
 */
@Controller
public class CommonController {
    @Autowired
    private UserService userService;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private PatientService patientService;
    @Autowired
    private ProcedureService procedureService;
    @Autowired
    private MaterialService materialService;
    @Autowired
    private MedicationService medicationService;

    final static Logger logger = LoggerFactory.getLogger(CommonController.class);

    @GetMapping("/main")
    public String main() {
        return "main";
    }

    @GetMapping
    public String main(Map<String, Object> model) {
        return "login";
    }

    @PostMapping("/login") //security like in Facebook
    public String login(
            @RequestParam String username,
            @RequestParam String password,
            Model model
    ) {
        logger.info(username, password);
        model.addAttribute("username", username);
        if (userService.isUser(password, username)) {
            return "main";
        }
        return "notAuthorized";
    }

    @GetMapping("/editUser")
    public String editUser() {
        return "editUser";
    }

    @GetMapping("/addUser")
    public String addUser() {
        return "addUser";
    }

    @GetMapping("/patients")
    public String patients(Model model) {
        model.addAttribute("patients", new ArrayList<>());
        return "patients";
    }

    @GetMapping("/addPatient")
    public String addPatient() {
        return "addPatient";
    }

    @GetMapping("/doctors")
    public String doctors(Model model) {
        model.addAttribute("doctors", new ArrayList<>());
        return "doctors";
    }

    @GetMapping("/addDoctor")
    public String addDoctor() {
        return "addDoctor";
    }

    @GetMapping("/works")
    public String works() {
        return "works";
    }

    @GetMapping("/addMaterial")
    public String addMaterial() {
        return "addMaterial";
    }

    @GetMapping("/addMedication")
    public String addMedication() {
        return "addMedication";
    }

    @GetMapping("/addAssigment")
    public String addAssigment(Model model) {
        Iterable<Doctor> doctors = doctorService.getAllDoctors();
        Iterable<Patient> patients = patientService.getAllPatients();
        Iterable<Procedure> procedures = procedureService.getAll();
        Iterable<Material> materials = materialService.getAll();
        Iterable<Medication> medications = medicationService.getAll();
        model.addAttribute("doctors", doctors);
        model.addAttribute("patients", patients);
        model.addAttribute("procedures", procedures);
        model.addAttribute("materials", materials);
        model.addAttribute("medications", medications);
        return "addAssigment";
    }

    @GetMapping("/addProcedures")
    public String addProcedures() {
        return "addProcedure";
    }

    @GetMapping("/reports")
    public String reports() {
        return "reports";
    }

    @GetMapping("/reportPeriod")
    public String reportPeriod(Model model) {
        model.addAttribute("patients", new ArrayList<>());
        return "reportPeriod";
    }

    @GetMapping("/reportProcedure")
    public String reportProcedure(Model model) {
        Iterable<Procedure> procedures = procedureService.getAll();
        model.addAttribute("procedures", procedures);
        model.addAttribute("works", new ArrayList<>());
        return "reportProcedure";
    }

    @GetMapping("/reportPatient")
    public String reportPatient(Model model) {
        model.addAttribute("works", new ArrayList<>());
        return "reportPatient";
    }

}