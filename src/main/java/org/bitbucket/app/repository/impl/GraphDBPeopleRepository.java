package org.bitbucket.app.repository.impl;

import org.bitbucket.app.entity.Person;
import org.bitbucket.app.repository.IPeopleRepository;
import org.bitbucket.app.utils.JDBCConnectionPool;

import java.sql.*;
import java.util.List;

public class GraphDBPeopleRepository implements IPeopleRepository {

    JDBCConnectionPool connectionPool;

    public GraphDBPeopleRepository(JDBCConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public Person create(Person p) {
        long id = 0;
        Connection connection = this.connectionPool.connection();
        String graphdb = "INSERT into people (first_name, last_name, age, city) values(?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(graphdb, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, p.getFirstName());
            statement.setString(2, p.getLastName());
            statement.setInt(3, p.getAge());
            statement.setString(4, p.getCity());
            int row = statement.executeUpdate();
            if (row != 0) {
                ResultSet resultSet = statement.getGeneratedKeys();
                resultSet.next();
                id = resultSet.getLong(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            this.connectionPool.parking(connection);
        }
        p.setId(id);
        return p;
    }

    @Override
    public List<Person> readAll() {
        return null;
    }

    @Override
    public void update(Person p) {

    }

    @Override
    public void delete(long id) {

    }

}
