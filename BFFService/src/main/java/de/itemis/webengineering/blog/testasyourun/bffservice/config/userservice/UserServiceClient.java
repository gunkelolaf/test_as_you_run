package de.itemis.webengineering.blog.testasyourun.bffservice.config.userservice;

import de.itemis.webengineering.blog.testasyourun.bffservice.model.User;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

@Component
public class UserServiceClient
{
    public static final String CREATE_USER_PATH = "/createuser";
    public static final String GET_USERS_BY_LAST_NAME_PATH = "/getusersbylastname";

    public static final String LASTNAME_PARAM = "lastname";
    public static final String FIRSTNAME_PARAM = "firstname";


    private final UserServiceProperties userServiceProperties;
    private final String userservicePath;
    private final RestTemplate restTemplate;

    UserServiceClient(final UserServiceProperties userServiceProperties)
    {
        this.userServiceProperties = userServiceProperties;
        this.userservicePath = "http://" + userServiceProperties.getHost() + ":" + userServiceProperties.getPort();
        this.restTemplate = new RestTemplate();
    }


    public void createUser(final String firstName, final String lastName)
    {
        final String createUserRequest = UriComponentsBuilder.fromUriString(this.userservicePath + CREATE_USER_PATH)
              .queryParam(FIRSTNAME_PARAM, firstName)
              .queryParam(LASTNAME_PARAM, lastName)
              .toUriString();
        this.restTemplate.postForObject(createUserRequest, null, String.class);
    }

    public List<User> getUsersByLastName(final String lastName)
    {
        final String url = this.userservicePath + "/" + GET_USERS_BY_LAST_NAME_PATH + "/" + lastName;

        final User[] usersFromRequest = this.restTemplate.getForObject(url, new User[]{}.getClass());

        final List<User> users = Arrays.asList(usersFromRequest);
        return users;
    }


}
