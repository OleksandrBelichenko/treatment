package hospital.model.common;

import com.google.common.base.Optional;

import java.io.Serializable;
import java.util.List;

/**
 * @author Oleksandr Belichenko
 */
public interface CommonEntityDao <P extends Serializable, T extends CommonEntity<P>> {

    /**
     * Find entity by id
     *
     * @param id
     * @return absent if none found
     */
    Optional<T> findById(P id);

    /**
     * Find by id or throw exception if none found
     *
     * @param id
     * @return entity
     */
    T findByIdExpected(P id);

    /**
     * Returns first found entities.
     *
     * @param attribute
     * @param value
     * @return Optional
     */
    Optional<T> findBy(String attribute, Object value);

    /**
     * Returns first found entity or throw exception is none found.
     *
     * @param attribute
     * @param value
     * @return Optional
     */
    T findByExpected(String attribute, Object value);

    /**
     * Search for entities which have attributes with a given value
     * Note! nested properties are supported, e.g. foo.bar.pro
     *
     * @param field
     * @param value
     * @return found entities or empty List if nothing found
     */
    List<T> findAllBy(String field, Object value);

    /**
     * Returns all entities from DB.
     *
     * @return empty List if table is empty.
     */
    List<T> findAll();

    /**
     * Returns all entities from DB by like value.
     *
     * @param field
     * @param value
     * @return empty List if table is empty.
     */
    List<T> findAllByLikeAttributeCriteria(String field, String value);

    /**
     * Find all entities by ids.
     *
     * @param ids
     * @return
     */
    List<T> findAll(List<P> ids);

    /**
     * @param entity
     * @return updated or created entity
     * @see org.hibernate.Session#saveOrUpdate(Object)
     */
    T saveOrUpdate(T entity);

    /**
     * @param entities
     * @return
     */
    List<T> saveOrUpdate(List<T> entities);

    /**
     * @param id
     */
    void delete(P id);

    /**
     * @param field
     * @param value
     * @return number of deleted entities
     */
    int deleteBy(String field, Object value);

    /**
     * @param entity
     */
    void delete(T entity);

    /**
     * Delete all given entities
     *
     * @param entities
     * @return not deleted entities
     */
    List<T> delete(List<T> entities);

    /**
     * Delete all entities from DB.
     */
    void deleteAll();

    /**
     * Checks if entity with given id exists
     *
     * @param id
     * @return true - if exists
     */
    boolean exists(P id);

    /**
     * Returns class of entity
     *
     * @return
     */
    Class<T> getEntityClass();

    /**
     * Search for entities which have attribute in specified values
     * @param field
     * @return
     */
    List<T> findAllByInValues(String field, List<?> value);

}
