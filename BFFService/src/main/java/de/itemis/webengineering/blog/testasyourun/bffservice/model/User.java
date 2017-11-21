package de.itemis.webengineering.blog.testasyourun.bffservice.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User
{

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
