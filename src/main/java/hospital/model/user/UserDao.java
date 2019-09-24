package hospital.model.user;

import com.google.common.base.Optional;
import hospital.model.common.CommonEntityDao;

/**
 * @author Oleksandr Belichenko
 */
public interface UserDao extends CommonEntityDao<Long, User> {
    Optional<User> getUser(String username);
    void addUser(User user);
    void removeUser(User user);
    void editUser(User user);
}
