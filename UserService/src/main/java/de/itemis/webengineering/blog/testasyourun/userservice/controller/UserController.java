package de.itemis.webengineering.blog.testasyourun.userservice.controller;

import de.itemis.webengineering.blog.testasyourun.userservice.model.User;
import de.itemis.webengineering.blog.testasyourun.userservice.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController
{
    public static final String CREATE_USER_PATH = "/createuser";
    public static final String GET_USERS_BY_LAST_NAME_PATH = "/getusersbylastname";

    public static final String LASTNAME_PARAM = "lastname";
    public static final String FIRSTNAME_PARAM = "firstname";

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = CREATE_USER_PATH, method = RequestMethod.POST)
    public void createUser(@RequestParam(FIRSTNAME_PARAM) final String firstName, @RequestParam(LASTNAME_PARAM) final String lastName)
    {
        final User user = new User(firstName, lastName);
        this.userRepository.save(user);
    }

    @RequestMapping(GET_USERS_BY_LAST_NAME_PATH + "/{" + LASTNAME_PARAM + "}")
    public List<User> getUsersBylastName(@PathVariable(LASTNAME_PARAM) final String lastName)
    {
        final List<User> allByLastName = this.userRepository.getAllByLastName(lastName);
        return allByLastName;
    }
}
