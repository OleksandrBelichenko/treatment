package hospital.service;

import hospital.model.medication.Medication;
import hospital.model.medication.MedicationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Oleksandr Belichenko
 */
@Service
public class MedicationService {
    @Autowired
    private MedicationDao medicationDao;
    @Autowired
    private GeneralService generalService;

    public void addMedication(String name, String description, Double price){
        Medication medication = new Medication();
        medication.setDescription(description);
        medication.setName(name);
        medication.setPrice(price);
        medicationDao.addMedication(medication);
    }

    public void updateMedication(String name, String newName, String description, Double price){
        Medication medication = getMedicationByName(name);
        if (medication == null){
            addMedication(name, description, price);
            return;
        }

        if (generalService.isValidStringData(name)){
            medication.setName(newName);
        }

        medication.setDescription(description);

        if (generalService.isValidStringData(price)){
            medication.setPrice(price);
        }

        medicationDao.updateMedication(medication);
    }

    public void removeMedication(String name){
        Medication medication = getMedicationByName(name);
        medicationDao.removeMedication(medication);
    }

    public Medication getMedicationByName(String name){
        return medicationDao.getMedicationByName(name);
    }

    public List<Medication> getMedicationsByName(String name){
        return medicationDao.getMedicationsByName(name);
    }

    public Double getPrice(String name){
        Medication medication = getMedicationByName(name);
        return medication.getPrice();
    }

    public List<Medication> getMedicationsByNames(List<String> names){
        return medicationDao.getMedicationsByNames(names);
    }

    public List<Medication> getMedicationsByNames(String names){
        List<String> medicationsList = generalService.getListFromString(names);
        return getMedicationsByNames(medicationsList);
    }

    public Iterable<Medication> getAll(){
        return medicationDao.findAll();
    }
}
