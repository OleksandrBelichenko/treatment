package hospital.controller;


import hospital.model.medication.Medication;
import hospital.service.MedicationService;
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
@RequestMapping(value = "/medication")
public class MedicationController {

    @Autowired
    private MedicationService medicationService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String addMedication(@RequestParam String name, @RequestParam String description, @RequestParam Double price, Model model){
        model.addAttribute("medications", new ArrayList<>());
        medicationService.addMedication(name, description, price);
        return "main";
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public void updateMedication(@RequestParam String name, @RequestParam(required = false) String newName,
                               @RequestParam(required = false) String description, @RequestParam(required = false) Double price){
        medicationService.updateMedication(name, newName, description, price);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void removeMedication(@RequestParam String name){
        medicationService.removeMedication(name);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public Medication getMedicationByName(@RequestParam String name){
        return medicationService.getMedicationByName(name);
    }

    @RequestMapping(value = "/getMedicationsByName", method = RequestMethod.GET)
    public List<Medication> getMedicationsByName(@RequestParam String name){
        return medicationService.getMedicationsByName(name);
    }

    @RequestMapping(value = "/getPrice", method = RequestMethod.GET)
    public Double getPrice(@RequestParam String name){
        return medicationService.getPrice(name);
    }

    @RequestMapping(value = "/getMaterialsByNames", method = RequestMethod.GET)
    public List<Medication> getMedicationsByNames(@RequestParam String names){
        return medicationService.getMedicationsByNames(names);
    }

    @GetMapping("/all")
    public Iterable<Medication> getMedications(Model model) {
        Iterable<Medication> medications = medicationService.getAll();
        model.addAttribute("medications", medications);
        return medications;
    }

    @GetMapping("/allMedications")
    public String allMedications(Model model) {
        Iterable<Medication> medications = medicationService.getAll();
        model.addAttribute("medications", medications);
        return "addAssigment";
    }
}


