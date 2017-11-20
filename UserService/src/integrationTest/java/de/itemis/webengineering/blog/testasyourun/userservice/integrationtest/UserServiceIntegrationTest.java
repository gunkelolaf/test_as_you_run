package de.itemis.webengineering.blog.testasyourun.userservice.integrationtest;

import de.itemis.webengineering.blog.testasyourun.userservice.controller.UserController;
import de.itemis.webengineering.blog.testasyourun.userservice.model.User;
import de.itemis.webengineering.blog.testasyourun.userservice.model.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserServiceIntegrationTest
{
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    UserRepository userRepository;

    @Test
    public void shouldCreateAndReadUser() throws Exception
    {   //given
        final User alfred = new User("alfred", "pennyworth");

        final String createUserRequest = UriComponentsBuilder.fromUriString(UserController.CREATE_USER_PATH)
              .queryParam(UserController.FIRSTNAME_PARAM, alfred.getFirstName())
              .queryParam(UserController.LASTNAME_PARAM, alfred.getLastName())
              .toUriString();

        final String getUsersRequest = UserController.GET_USERS_BY_LAST_NAME_PATH + "/" + alfred.getLastName();
        //when
        this.restTemplate.postForObject(createUserRequest, null, String.class);
        final User[] users = this.restTemplate.getForObject(getUsersRequest, new User[] {}.getClass());
        //then
        assertThat(users).hasSize(1);
        assertThat(users[0]).isEqualTo(alfred);
    }

    @Test
    public void shouldGetKnownUsers() throws Exception
    {   //given
        final User bruce = new User("bruce", "wayne");
        final User martha = new User("martha", "wayne");
        final User thomas = new User("thomas", "wayne");

        //when
        final String getWaynesRequest = UserController.GET_USERS_BY_LAST_NAME_PATH + "/" + bruce.getLastName();
        final User[] users = this.restTemplate.getForObject(getWaynesRequest, new User[]{}.getClass());
        //then
        assertThat(users).hasSize(3);
        assertThat(users).containsExactlyInAnyOrder(bruce, martha, thomas);

    }
}
