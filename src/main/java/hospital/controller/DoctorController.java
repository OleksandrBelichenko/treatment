package hospital.controller;


import hospital.model.doctor.Doctor;
import hospital.service.DoctorService;
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
import java.util.Objects;

/**
 * @author Oleksandr Belichenko
 */
@Controller
@RequestMapping(value = "/doctor")
public class DoctorController {

    final static Logger logger = LoggerFactory.getLogger(DoctorController.class);

    @Autowired
    private DoctorService doctorService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String addDoctor( @RequestParam String name, @RequestParam String secondName,
                           @RequestParam String surname, @RequestParam String specialization, Model model){
        model.addAttribute("doctors", new ArrayList<>());
        doctorService.addDoctor(name, secondName, surname, specialization);
        return "doctors";
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public void updateDoctor(@RequestParam(required = false) String name, @RequestParam(required = false) String secondName,
                             @RequestParam(required = false) String surname, @RequestParam(required = false) String specialization){
        doctorService.updateDoctor(name, secondName, surname, specialization);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void removeDoctor(@RequestParam(required = false) String name, @RequestParam(required = false) String secondName,
                             @RequestParam(required = false) String surname){
        doctorService.removeDoctor(name, secondName, surname);
    }

    @RequestMapping(value = "/getDoctorsByFIO", method = RequestMethod.GET)
    public String getDoctorsByFIO(@RequestParam(required = false) String name, @RequestParam(required = false) String secondName,
                                 @RequestParam(required = false) String surname, Model model){
        if ((Objects.isNull(secondName) || secondName.isEmpty()) && (Objects.isNull(name) || name.isEmpty()) && (Objects.isNull(surname) || surname.isEmpty())){
            model.addAttribute("doctors", doctorService.getAllDoctors());
        } else {
            model.addAttribute("doctors", doctorService.getDoctorsByFIO(name, secondName, surname));
        }
        return "doctors";
    }

    @RequestMapping(value = "/getDoctorsBySpecialization", method = RequestMethod.GET)
    public String getDoctorsBySpecialization(@RequestParam String specialization,  Model model){
        if (null == specialization || "".equals(specialization)){
            model.addAttribute("doctors", doctorService.getAllDoctors());
        }
        else {
            model.addAttribute("doctors", doctorService.getDoctorsBySpecialization(specialization));
        }
        return "doctors";
    }

    @GetMapping("/all")
    public Iterable<Doctor> getAllDoctors(Model model) {
        Iterable<Doctor> doctors = doctorService.getAllDoctors();
        model.addAttribute("doctors", doctors);
        return doctors;
    }


    @GetMapping("/allDocs")
    public String allDocs(Model model) {
        Iterable<Doctor> doctors = doctorService.getAllDoctors();
        model.addAttribute("doctors", doctors);
        return "addAssigment";
    }
}
