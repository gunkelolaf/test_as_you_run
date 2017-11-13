package de.itemis.webengineering.blog.testasyourun.capitalizeservice;

import de.itemis.webengineering.blog.testasyourun.capitalizeservice.controller.CapitalizeController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class ServerMockTest
{
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldCapitalizeSingleCharacter() throws Exception
    {
        final String singleCharacter = "c";
        final String expectedResult = "C";

        requestWithInputAndExpectResult(singleCharacter, expectedResult);
    }

    @Test
    public void shouldCapitalizeInput() throws Exception
    {
        final String inputToCapitalize = "hello";
        final String expectedResult = "Hello";
        requestWithInputAndExpectResult(inputToCapitalize, expectedResult);
    }

    @Test
    public void shouldNotChangeCapitilizedString() throws Exception
    {
        final String capitilizedString = "Uppercase";
        requestWithInputAndExpectResult(capitilizedString, capitilizedString);
    }

    private void requestWithInputAndExpectResult(final String inputToCapitalize, final String expectedResult) throws Exception
    {
        //given
        final String requestPath = CapitalizeController.CAPITALIZE_PATH + "/" + inputToCapitalize;
        //when
        this.mockMvc.perform(get(requestPath))
                    //then
                    .andExpect(status().isOk())
                    .andExpect(content().string(expectedResult));
    }
}
