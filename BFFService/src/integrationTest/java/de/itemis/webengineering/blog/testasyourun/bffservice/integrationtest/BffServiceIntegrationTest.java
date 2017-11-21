package de.itemis.webengineering.blog.testasyourun.bffservice.integrationtest;

import de.itemis.webengineering.blog.testasyourun.bffservice.controller.BFFController;
import de.itemis.webengineering.blog.testasyourun.bffservice.model.User;
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
public class BffServiceIntegrationTest
{
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void readAndWriteUser() throws Exception
    {   //given
        final User alfred = new User("alfred", "pennyworth");

        final String createUserRequest = UriComponentsBuilder.fromUriString(BFFController.CREATE_USER_PATH)
                                                             .queryParam(BFFController.FIRSTNAME_PARAM, alfred.getFirstName())
                                                             .queryParam(BFFController.LASTNAME_PARAM, alfred.getLastName())
                                                             .toUriString();

        final String getUsersRequest = BFFController.GET_USERS_BY_LAST_NAME_PATH + "/" + alfred.getLastName();
        //when
        this.restTemplate.postForObject(createUserRequest, null, String.class);
        final User[] users = this.restTemplate.getForObject(getUsersRequest, new User[]{}.getClass());
        //then
        assertThat(users).hasSize(1);
        assertThat(users[0].getFirstName()).isEqualTo("Alfred");
        assertThat(users[0].getLastName()).isEqualTo("Pennyworth");

    }

    @Test
    public void shouldGetKnownUsers() throws Exception
    {   //given
        final User bruce = new User("Bruce", "Wayne");
        final User martha = new User("Martha", "Wayne");
        final User thomas = new User("Thomas", "Wayne");

        final String getWaynesRequest = BFFController.GET_USERS_BY_LAST_NAME_PATH + "/" + bruce.getLastName().toLowerCase();

        //when
        final User[] users = this.restTemplate.getForObject(getWaynesRequest, new User[]{}.getClass());
        //then
        assertThat(users).hasSize(3);
        assertThat(users).containsExactlyInAnyOrder(bruce, martha, thomas);

    }
}