package hospital.model.work;

import hospital.model.assigments.Assigments;
import hospital.model.common.CommonEntityDao;

/**
 * @author Oleksandr Belichenko
 */
public interface WorkDao extends CommonEntityDao<Long, Work> {
    void addWork(Work work);
    void removeWork(Work work);
    Work getWorkByAssigments(Assigments assigments);
}