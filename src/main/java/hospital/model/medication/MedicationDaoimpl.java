package hospital.model.medication;

import hospital.model.common.CommonEntityDaoImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Oleksandr Belichenko
 */
@Repository
public class MedicationDaoimpl extends CommonEntityDaoImpl<Long, Medication> implements MedicationDao {
    protected MedicationDaoimpl(){super(Medication.class);}

    @Override
    public void addMedication(Medication medication) {
        saveOrUpdate(medication);
    }

    @Override
    public void updateMedication(Medication medication) {
        saveOrUpdate(medication);
    }

    @Override
    public void removeMedication(Medication medication) {
        delete(medication.getId());
    }

    @Override
    public List<Medication> getMedicationsByName(String name) {
        return findAllByLikeAttributeCriteria("name", name);
    }

    @Override
    public Double getPrice(String name) {
        return getMedicationsByName(name).stream().findFirst().orElse(null).getPrice();
    }

    @Override
    public Medication getMedicationByName(String name){
        return findBy("name", name).orNull();
    }

    @Override
    public List<Medication> getMedicationsByNames(List<String> names) {
        return findAllByInValues("name", names);
    }
}
