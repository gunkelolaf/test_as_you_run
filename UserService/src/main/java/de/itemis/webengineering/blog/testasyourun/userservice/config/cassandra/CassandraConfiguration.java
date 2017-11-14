package de.itemis.webengineering.blog.testasyourun.userservice.config.cassandra;

import com.datastax.driver.core.SocketOptions;
import com.google.common.collect.ImmutableList;
import de.itemis.webengineering.blog.testasyourun.userservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cassandra.core.keyspace.CreateKeyspaceSpecification;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.config.java.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import java.util.List;

@Configuration
@EnableCassandraRepositories
public class CassandraConfiguration extends AbstractCassandraConfiguration
{
    @Autowired
    private CassandraProperties cassandraProperties;

    @Override
    protected String getKeyspaceName()
    {
        final String keyspace = this.cassandraProperties.getKeyspace();
        return keyspace;
    }

    @Override
    protected List<CreateKeyspaceSpecification> getKeyspaceCreations()
    {
        final String keyspace = this.cassandraProperties.getKeyspace();
        final CreateKeyspaceSpecification createKeyspaceSpecification = new CreateKeyspaceSpecification(keyspace).ifNotExists(true);

        final List<CreateKeyspaceSpecification> createKeyspaceSpecifications = ImmutableList.of(createKeyspaceSpecification);

        return createKeyspaceSpecifications;
    }

    @Override
    public SchemaAction getSchemaAction()
    {
        return SchemaAction.CREATE_IF_NOT_EXISTS;
    }

    @Override
    protected String getContactPoints()
    {
        final String host = this.cassandraProperties.getHost();
        return host;
    }

    @Override
    protected SocketOptions getSocketOptions()
    {
        return new SocketOptions().setConnectTimeoutMillis(60000).setReuseAddress(true);
    }

    @Override
    protected int getPort()
    {
        final int port = this.cassandraProperties.getPort();
        return port;
    }

    @Override
    public String[] getEntityBasePackages()
    {
        return ImmutableList.of(User.class.getPackage().getName()).toArray(new String[] {});
    }
}

