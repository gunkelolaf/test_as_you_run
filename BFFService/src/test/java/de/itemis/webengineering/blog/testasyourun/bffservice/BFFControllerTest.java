package de.itemis.webengineering.blog.testasyourun.bffservice;

import com.google.common.collect.ImmutableList;
import de.itemis.webengineering.blog.testasyourun.bffservice.config.capitalizeservice.CapitalizeServiceClient;
import de.itemis.webengineering.blog.testasyourun.bffservice.config.userservice.UserServiceClient;
import de.itemis.webengineering.blog.testasyourun.bffservice.controller.BFFController;
import de.itemis.webengineering.blog.testasyourun.bffservice.model.User;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@RunWith(SpringRunner.class)
@WebMvcTest(BFFController.class)
public class BFFControllerTest
{
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    CapitalizeServiceClient capitalizeServiceClient;

    @MockBean
    UserServiceClient userServiceClient;

    @Test
    public void shouldCallUserServiceToSaveUsers() throws Exception
    {
        //given
        final String firstName = "john";
        final String lastName = "doe";

        final MockHttpServletRequestBuilder createUserJohnDoe = post(BFFController.CREATE_USER_PATH)
              .param(BFFController.FIRSTNAME_PARAM, firstName)
              .param(BFFController.LASTNAME_PARAM, lastName);
        //when
        this.mockMvc.perform(createUserJohnDoe);
        //then
        verify(this.userServiceClient).createUser(firstName, lastName);
    }

    @Test
    public void shouldCallUserServiceToAskForUser() throws Exception
    {
        //given
        final String lastName = "doe";

        final MockHttpServletRequestBuilder requestBuilder = get(BFFController.GET_USERS_BY_LAST_NAME_PATH + "/" + lastName);
        //when
        this.mockMvc.perform(requestBuilder);
        //then
        verify(this.userServiceClient).getUsersByLastName(lastName);
    }

    @Test
    public void shouldCallCapitalizeServiceWhenAskedForUsers() throws Exception
    {
        //given
        final String firstName = "john";
        final String lastName = "doe";

        when(this.userServiceClient.getUsersByLastName(lastName)).thenReturn(ImmutableList.of(new User(firstName, lastName)));

        final MockHttpServletRequestBuilder getUsersWithLastNameDoe = get(BFFController.GET_USERS_BY_LAST_NAME_PATH + "/" + lastName);
        //when
        this.mockMvc.perform(getUsersWithLastNameDoe);
        //then

        verify(this.capitalizeServiceClient).capitalizeString(firstName);
        verify(this.capitalizeServiceClient).capitalizeString(lastName);

    }

    @After
    public void resetMocks()
    {
        Mockito.reset(this.capitalizeServiceClient, this.userServiceClient);
    }

}
