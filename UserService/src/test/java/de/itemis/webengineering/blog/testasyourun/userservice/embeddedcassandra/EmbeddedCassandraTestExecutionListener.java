package de.itemis.webengineering.blog.testasyourun.userservice.embeddedcassandra;

import org.apache.thrift.transport.TTransportException;
import org.cassandraunit.utils.EmbeddedCassandraServerHelper;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

import java.io.IOException;

public class EmbeddedCassandraTestExecutionListener extends AbstractTestExecutionListener
{
    private static final String CASSANDRA_KEYSPACE = "test";

    @Override
    public void beforeTestClass(final TestContext testContext) throws Exception
    {
        startCassandraEmbedded();
        setSystemProperties();
    }

    private void setSystemProperties()
    {
        System.setProperty("cassandra.host", EmbeddedCassandraServerHelper.getHost());
        System.setProperty("cassandra.port", String.valueOf(EmbeddedCassandraServerHelper.getNativeTransportPort()));
        System.setProperty("cassandra.keyspace", CASSANDRA_KEYSPACE);
    }

    private void startCassandraEmbedded() throws InterruptedException, IOException, TTransportException
    {
        EmbeddedCassandraServerHelper.startEmbeddedCassandra();
    }

    @Override
    public void afterTestMethod(final TestContext testContext) throws Exception
    {
        cleanCassandra();
    }

    public void cleanCassandra()
    {
        EmbeddedCassandraServerHelper.cleanDataEmbeddedCassandra(CASSANDRA_KEYSPACE);
    }
}
