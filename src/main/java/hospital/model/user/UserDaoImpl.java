package hospital.model.user;

import com.google.common.base.Optional;
import hospital.model.common.CommonEntityDaoImpl;
import org.springframework.stereotype.Repository;



/**
 * @author Oleksandr Belichenko
 */
@Repository
public class UserDaoImpl extends CommonEntityDaoImpl<Long, User> implements UserDao {
    protected UserDaoImpl() {
        super(User.class);
    }

    @Override
    public Optional<User> getUser(String username) {
        return findBy("username", username);
    }

    @Override
    public void addUser(User user) {
        saveOrUpdate(user);
    }

    @Override
    public void removeUser(User user) {
        delete(user.getId());
    }

    @Override
    public void editUser(User user) {
        saveOrUpdate(user);
    }

}
