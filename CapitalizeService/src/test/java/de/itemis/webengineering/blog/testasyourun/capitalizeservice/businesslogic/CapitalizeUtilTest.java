package de.itemis.webengineering.blog.testasyourun.capitalizeservice.businesslogic;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CapitalizeUtilTest
{
    @Test
    public void shouldCapitalizeSingleCharacter()
    {
        //given
        final String singleCharacter = "c";
        //when
        final String result = CapitalizeUtil.capitalize(singleCharacter);
        //then
        assertThat(result).isEqualTo(singleCharacter.toUpperCase());

    }

    @Test
    public void shouldCapitalizeWord()
    {
        //given
        final String wordToCapitalize = "hello";
        //when
        final String result = CapitalizeUtil.capitalize(wordToCapitalize);
        //then
        final String expectedResult = "Hello";
        assertThat(result).isEqualTo(expectedResult);

    }
    @Test
    public void shouldNotChangeCapitalizedString()
    {
        //given
        final String capitalizedString = "HELLO";
        //when
        final String result = CapitalizeUtil.capitalize(capitalizedString);
        //then
        assertThat(result).isEqualTo(capitalizedString);

    }

    @Test
    public void shouldReturnEmptyString()
    {
        //given
        final String capitalizedString = "";
        //when
        final String result = CapitalizeUtil.capitalize(capitalizedString);
        //then
        assertThat(result).isEqualTo(capitalizedString);

    }

    @Test
    public void shouldNotAcceptNullAsInput()
    {
        assertThatThrownBy(() -> CapitalizeUtil.capitalize(null)).isInstanceOf(IllegalArgumentException.class);
    }

}