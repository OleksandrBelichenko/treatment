package hospital.model.work;

import hospital.model.assigments.Assigments;
import hospital.model.common.CommonEntityDaoImpl;
import org.springframework.stereotype.Repository;

/**
 * @author Oleksandr Belichenko
 */
@Repository
public class WorkDaoImpl extends CommonEntityDaoImpl<Long, Work> implements WorkDao {
    protected WorkDaoImpl() {
        super(Work.class);
    }

    @Override
    public void addWork(Work work) {
        saveOrUpdate(work);
    }

    @Override
    public void removeWork(Work work) {
        delete(work.getId());
    }

    @Override
    public Work getWorkByAssigments(Assigments assigments) {
        return findBy("assigments", assigments).orNull();
    }
}
