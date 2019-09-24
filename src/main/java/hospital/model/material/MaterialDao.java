package hospital.model.material;

import hospital.model.common.CommonEntityDao;

import java.util.List;

/**
 * @author Oleksandr Belichenko
 */
public interface MaterialDao extends CommonEntityDao<Long, Material> {
    void addMaterial(Material material);
    void updateMaterial(Material material);
    void removeMaterial(Material material);
    List<Material> getMaterialsByName(String name);
    Double getPrice(String name);
    Material getMaterialByName(String name);
    List<Material> getMaterialsByNames(List<String> names);
}
