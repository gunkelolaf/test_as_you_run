package de.itemis.webengineering.blog.testasyourun.userservice.config.cassandra;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "cassandra")
public class CassandraProperties
{
    private String host;
    private int port;
    private String keyspace;

    public String getHost()
    {
        return this.host;
    }

    public void setHost(final String host)
    {
        this.host = host;
    }

    public int getPort()
    {
        return this.port;
    }

    public void setPort(final int port)
    {
        this.port = port;
    }

    public String getKeyspace()
    {
        return this.keyspace;
    }

    public void setKeyspace(final String keyspace)
    {
        this.keyspace = keyspace;
    }
}
