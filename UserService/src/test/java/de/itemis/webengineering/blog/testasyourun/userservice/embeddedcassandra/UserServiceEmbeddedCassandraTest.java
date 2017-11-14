package de.itemis.webengineering.blog.testasyourun.userservice.embeddedcassandra;

import de.itemis.webengineering.blog.testasyourun.userservice.controller.UserController;
import de.itemis.webengineering.blog.testasyourun.userservice.model.User;
import de.itemis.webengineering.blog.testasyourun.userservice.model.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestExecutionListeners(mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS, listeners = EmbeddedCassandraTestExecutionListener.class)
public class UserServiceEmbeddedCassandraTest
{
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void shouldCreateUser() throws Exception
    {
        //given
        final User user = new User("Bruce", "Wayne");

        final String request = UriComponentsBuilder.fromUriString(UserController.CREATE_USER_PATH)
              .queryParam(UserController.FIRSTNAME_PARAM, user.getFirstName())
              .queryParam(UserController.LASTNAME_PARAM, user.getLastName())
              .toUriString();
        //when
        this.restTemplate.postForObject(request, null, String.class);
        //then
        final List<User> allUsersWithLastName = this.userRepository.getAllByLastName(user.getLastName());
        assertThat(allUsersWithLastName).hasSize(1);
        assertThat(allUsersWithLastName.get(0)).isEqualTo(user);
    }

    @Test
    public void shouldReadUser() throws Exception
    {
        //given
        final User user = new User("Martha", "Wayne");
        this.userRepository.save(user);
        //when
        final User[] users = this.restTemplate.getForObject(UserController.GET_USERS_BY_LAST_NAME_PATH + "/" + user.getLastName(), new User[] {}.getClass());
        //then
        assertThat(users).hasSize(1);
        assertThat(users[0]).isEqualTo(user);
    }

    @Test
    public void shouldCreateAndReadUser() throws Exception
    {
        //given
        final User user = new User("Thomas", "Wayne");

        final String request = UriComponentsBuilder.fromUriString(UserController.CREATE_USER_PATH)
              .queryParam(UserController.FIRSTNAME_PARAM, user.getFirstName())
              .queryParam(UserController.LASTNAME_PARAM, user.getLastName())
              .toUriString();
        //when
        this.restTemplate.postForObject(request, null, String.class);
        final User[] users = this.restTemplate.getForObject(UserController.GET_USERS_BY_LAST_NAME_PATH + "/" + user.getLastName(), new User[] {}.getClass());
        //then
        assertThat(users).hasSize(1);
        assertThat(users[0]).isEqualTo(user);
    }

}
