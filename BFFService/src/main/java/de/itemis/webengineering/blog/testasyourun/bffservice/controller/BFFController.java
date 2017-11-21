package de.itemis.webengineering.blog.testasyourun.bffservice.controller;

import de.itemis.webengineering.blog.testasyourun.bffservice.config.capitalizeservice.CapitalizeServiceClient;
import de.itemis.webengineering.blog.testasyourun.bffservice.config.userservice.UserServiceClient;
import de.itemis.webengineering.blog.testasyourun.bffservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BFFController
{
    public static final String CREATE_USER_PATH = "/createuser";
    public static final String GET_USERS_BY_LAST_NAME_PATH = "/getusersbylastname";

    public static final String LASTNAME_PARAM = "lastname";
    public static final String FIRSTNAME_PARAM = "firstname";


    @Autowired
    UserServiceClient userServiceClient;

    @Autowired
    CapitalizeServiceClient capitalizeServiceClient;

    @RequestMapping(value = CREATE_USER_PATH, method = RequestMethod.POST)
    public void createUser(@RequestParam(FIRSTNAME_PARAM) final String firstName, @RequestParam(LASTNAME_PARAM) final String lastName)
    {
        this.userServiceClient.createUser(firstName, lastName);
    }

    @RequestMapping(GET_USERS_BY_LAST_NAME_PATH + "/{" + LASTNAME_PARAM + "}")
    public List<User> getUsersBylastName(@PathVariable(LASTNAME_PARAM) final String lastName)
    {
        final List<User> users = this.userServiceClient.getUsersByLastName(lastName);
        for (final User user : users)
        {
            final String capitalizedFirstName = this.capitalizeServiceClient.capitalizeString(user.getFirstName());
            user.setFirstName(capitalizedFirstName);

            final String capitalizedLastName = this.capitalizeServiceClient.capitalizeString(user.getLastName());
            user.setLastName(capitalizedLastName);

        }
        return users;
    }


}
