package de.itemis.webengineering.blog.testasyourun.userservice.model;

import com.datastax.driver.core.utils.UUIDs;
import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

import java.util.UUID;

@Table(value = "users")
public class User
{
    @PrimaryKeyColumn(name = "id", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
    private UUID id = UUIDs.timeBased();

    @PrimaryKeyColumn(name = "lastName", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String lastName;

    private String firstName;

    public User(final String firstName, final String lastName)
    {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    protected User()
    {

    }

    public UUID getId()
    {
        return this.id;
    }

    public void setId(final UUID id)
    {
        this.id = id;
    }

    public String getLastName()
    {
        return this.lastName;
    }

    public void setLastName(final String lastName)
    {
        this.lastName = lastName;
    }

    public String getFirstName()
    {
        return this.firstName;
    }

    public void setFirstName(final String firstName)
    {
        this.firstName = firstName;
    }

    @Override
    public boolean equals(final Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final User other = (User) obj;
        return this.firstName.equals(other.firstName) && this.lastName.equals(other.lastName);
    }
}
