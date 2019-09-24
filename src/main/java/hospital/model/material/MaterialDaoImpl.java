package hospital.model.material;

import hospital.model.common.CommonEntityDaoImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Oleksandr Belichenko
 */
@Repository
public class MaterialDaoImpl extends CommonEntityDaoImpl<Long, Material> implements MaterialDao {

    protected MaterialDaoImpl(){super(Material.class);}

    @Override
    public void addMaterial(Material material) {
        saveOrUpdate(material);
    }

    @Override
    public void updateMaterial(Material material) {
        saveOrUpdate(material);
    }

    @Override
    public void removeMaterial(Material material) {
        delete(material.getId());
    }

    @Override
    public List<Material> getMaterialsByName(String name) {
        return findAllByLikeAttributeCriteria("name", name);
    }

    @Override
    public Double getPrice(String name) {
        return getMaterialsByName(name).stream().findFirst().orElse(null).getPrice();
    }

    @Override
    public Material getMaterialByName(String name){
        return findBy("name", name).orNull();
    }

    @Override
    public List<Material> getMaterialsByNames(List<String> names){
        return findAllByInValues("name", names);
    }
}
