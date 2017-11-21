package de.itemis.webengineering.blog.testasyourun.bffservice.config.capitalizeservice;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "capitalizeservice")
public class CapitalizeServiceProperties
{
    private String host;

    private int port;

    String getHost()
    {
        return this.host;
    }

    public void setHost(final String host)
    {
        this.host = host;
    }

    int getPort()
    {
        return this.port;
    }

    public void setPort(final int port)
    {
        this.port = port;
    }
}
