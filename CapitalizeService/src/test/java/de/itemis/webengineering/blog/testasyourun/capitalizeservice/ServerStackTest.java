package de.itemis.webengineering.blog.testasyourun.capitalizeservice;

import de.itemis.webengineering.blog.testasyourun.capitalizeservice.controller.CapitalizeController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ServerStackTest
{
    @Autowired
    private TestRestTemplate restTemplate;

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
        final String response = this.restTemplate.getForObject(requestPath, String.class);
        //then
        assertThat(response).isEqualTo(expectedResult);
    }
}