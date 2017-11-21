package de.itemis.webengineering.blog.testasyourun.bffservice.config.userservice;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "userservice")
public class UserServiceProperties
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
