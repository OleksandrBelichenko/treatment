package hospital.controller;


import hospital.model.material.Material;
import hospital.service.MaterialService;
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
@RequestMapping(value = "/material")
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String addMaterial(@RequestParam String name, @RequestParam String description, @RequestParam Double price, Model model){
        model.addAttribute("materials", new ArrayList<>());
        materialService.addMaterial(name, description, price);
        return "main";
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public void updateMaterial(@RequestParam String name, @RequestParam(required = false) String newName,
                              @RequestParam(required = false) String description, @RequestParam(required = false) Double price){
        materialService.updateMaterial(name, newName, description, price);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void removeMaterial(@RequestParam String name){
        materialService.removeMaterial(name);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public Material getMaterialByName(@RequestParam String name){
        return materialService.getMaterialByName(name);
    }

    @RequestMapping(value = "/getMaterialsByName", method = RequestMethod.GET)
    public List<Material> getMaterialsByName(@RequestParam String name){
        return materialService.getMaterialsByName(name);
    }

    @RequestMapping(value = "/getPrice", method = RequestMethod.GET)
    public Double getPrice(@RequestParam String name){
        return materialService.getPrice(name);
    }

    @RequestMapping(value = "/getMaterialsByNames", method = RequestMethod.GET)
    public List<Material> getMaterialsByNames(@RequestParam String names){
        return materialService.getMaterialsByNames(names);
    }

    @GetMapping("/all")
    public String getMaterials(Model model){
        model.addAttribute("materials", materialService.getAll());
        return "addAssigment";
    }
}
