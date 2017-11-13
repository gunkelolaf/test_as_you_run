package de.itemis.webengineering.blog.testasyourun.capitalizeservice.controller;


import de.itemis.webengineering.blog.testasyourun.capitalizeservice.businesslogic.CapitalizeUtil;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CapitalizeController
{

    public static final String CAPITALIZE_PATH = "/capitalize";

    @RequestMapping(CAPITALIZE_PATH + "/{stringToCapitalize}")
    public String capitalizeString(@PathVariable final String stringToCapitalize)
    {
        return CapitalizeUtil.capitalize(stringToCapitalize);
    }
}
