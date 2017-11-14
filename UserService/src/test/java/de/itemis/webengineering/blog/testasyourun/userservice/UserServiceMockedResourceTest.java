package de.itemis.webengineering.blog.testasyourun.userservice;

import de.itemis.webengineering.blog.testasyourun.userservice.controller.UserController;
import de.itemis.webengineering.blog.testasyourun.userservice.model.User;
import de.itemis.webengineering.blog.testasyourun.userservice.model.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserServiceMockedResourceTest
{
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    UserRepository userRepositoryMock;

    @Test
    public void shouldSaveUserToCassandra() throws Exception
    {
        //given
        final User catwoman = new User("selina", "kyle");
        final MockHttpServletRequestBuilder createUserRequest = post(UserController.CREATE_USER_PATH)
              .param(UserController.FIRSTNAME_PARAM, catwoman.getFirstName())
              .param(UserController.LASTNAME_PARAM, catwoman.getLastName());

        //when
        this.mockMvc.perform(createUserRequest);
        //then
        verify(this.userRepositoryMock).save(catwoman);
    }

    @Test
    public void shouldReadUsersByLastName() throws Exception
    {
        //given
        final String lastName = "kyle";
        final MockHttpServletRequestBuilder getKylesRequest = get(UserController.GET_USERS_BY_LAST_NAME_PATH + "/" + lastName);

        //when
        this.mockMvc.perform(getKylesRequest);
        //then
        verify(this.userRepositoryMock).getAllByLastName(lastName);
    }
}