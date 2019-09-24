package hospital.service;

import hospital.model.user.User;
import hospital.model.user.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * @author Oleksandr Belichenko
 */
@Service
public class UserService {

    final static Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserDao userDao;
    @Autowired
    private GeneralService generalService;

    public void addUser(String password, String username) {
        User user = new User();
        user.setPassword(password);
        user.setUsername(username);
        log.info(user.toString());
        userDao.addUser(user);
    }


    public void removeUser(String username) {
        userDao.removeUser(getUser(username));
    }


    public void editUser(String password, String username, String newUsername) {
        User user = getUser(username);
        log.info(user.toString());
        if (generalService.isValidStringData(password)){
            user.setPassword(password);
        }

        if (generalService.isValidStringData(newUsername)){
            user.setUsername(newUsername);
        }
        log.info("2 " + user.toString());
        userDao.editUser(user);
    }

    public boolean isUser(String password, String username){
        User user = getUser(username);

        if (user != null && user.getPassword().equals(password)){
            return true;
        }
        return false;
    }

    public User getUser(String username) {
        return userDao.getUser(username).orNull();
    }
}
