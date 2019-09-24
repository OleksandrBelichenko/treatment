package hospital.model.medication;

import hospital.model.common.CommonEntityDao;

import java.util.List;

/**
 * @author Oleksandr Belichenko
 */
public interface MedicationDao extends CommonEntityDao<Long, Medication> {
    void addMedication(Medication medication);
    void updateMedication(Medication medication);
    void removeMedication(Medication medication);
    List<Medication> getMedicationsByName(String name);
    Double getPrice(String name);
    Medication getMedicationByName(String name);
    List<Medication> getMedicationsByNames(List<String> names);
}