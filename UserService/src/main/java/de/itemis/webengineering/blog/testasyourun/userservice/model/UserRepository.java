package de.itemis.webengineering.blog.testasyourun.userservice.model;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;

import java.util.List;

public interface UserRepository extends CassandraRepository<User>
{
    @Query("SELECT * FROM users WHERE lastName = ?0 ALLOW FILTERING")
    List<User> getAllByLastName(final String lastName);
}
