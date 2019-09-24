package hospital.controller;

import hospital.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Oleksandr Belichenko
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/addUser")
    public String addUser(
            @RequestParam String username,
            @RequestParam String password
    ) {
        userService.addUser(password, username);
        return "login";
    }

    @PostMapping("/editUser")
    public String editUser(
            @RequestParam String username,
            @RequestParam String newUsername,
            @RequestParam String password
    ) {
        logger.info(username);
        logger.info(newUsername);
        logger.info(password);
        userService.editUser(password, username, newUsername);
        return "login";
    }


}
