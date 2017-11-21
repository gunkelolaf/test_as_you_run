package de.itemis.webengineering.blog.testasyourun.bffservice.config.capitalizeservice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CapitalizeServiceClient
{
    public static final String CAPITALIZE_PATH = "/capitalize";

    private final String servicePath;
    private final RestTemplate restTemplate;

    @Autowired
    public CapitalizeServiceClient(final CapitalizeServiceProperties properties)
    {
        this.servicePath = "http://" + properties.getHost() + ":" + properties.getPort();
        this.restTemplate = new RestTemplate();
    }

    public String capitalizeString(final String toCapitalize)
    {

        final String requestPath = this.servicePath + CAPITALIZE_PATH + "/" + toCapitalize;

        final String response = this.restTemplate.getForObject(requestPath, String.class);
        return response;
    }

}
