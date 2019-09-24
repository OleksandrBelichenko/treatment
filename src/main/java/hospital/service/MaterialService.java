package hospital.service;

import hospital.model.material.Material;
import hospital.model.material.MaterialDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Oleksandr Belichenko
 */
@Service
public class MaterialService {

    @Autowired
    private MaterialDao materialDao;
    @Autowired
    private GeneralService generalService;

    public void addMaterial(String name, String description, Double price){
        Material material = new Material();
        material.setDescription(description);
        material.setName(name);
        material.setPrice(price);
        materialDao.addMaterial(material);
    }

    public void updateMaterial(String name, String newName, String description, Double price){
        Material material = getMaterialByName(name);
        if (material == null){
            addMaterial(name, description, price);
            return;
        }

        if (generalService.isValidStringData(name)){
            material.setName(newName);
        }

        material.setDescription(description);

        if (generalService.isValidStringData(price)){
            material.setPrice(price);
        }

        materialDao.updateMaterial(material);
    }

    public void removeMaterial(String name){
        Material material = getMaterialByName(name);
        materialDao.removeMaterial(material);
    }

    public Material getMaterialByName(String name){
        return materialDao.getMaterialByName(name);
    }

    public List<Material> getMaterialsByName(String name){
        return materialDao.getMaterialsByName(name);
    }

    public Double getPrice(String name){
        Material material = getMaterialByName(name);
        return material.getPrice();
    }

    public List<Material> getMaterialsByNames(List<String> names){
        return materialDao.getMaterialsByNames(names);
    }

    public List<Material> getMaterialsByNames(String names){
        List<String> materialsList = generalService.getListFromString(names);
        return getMaterialsByNames(materialsList);
    }

    public Iterable<Material> getAll(){
        return materialDao.findAll();
    }
}
