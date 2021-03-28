package org.bitbucket.app.repository.impl;

import org.bitbucket.app.entity.Person;
import org.bitbucket.app.repository.IPeopleRepository;
import org.bitbucket.app.utils.JDBCConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgreSQLPeopleRepository implements IPeopleRepository {

    private JDBCConnectionPool connectionPool;

    public PostgreSQLPeopleRepository(JDBCConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public Person create(Person p) {
        long id = 0;
        Connection connection = this.connectionPool.connection();
        String sql = "insert into people (first_name, last_name, age, city) values(?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
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
        List<Person> result = new ArrayList<>();
        Connection connection = this.connectionPool.connection();
        String sql = "select * from people";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Person p = new Person(
                        resultSet.getLong("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getInt("age"),
                        resultSet.getString("city")
                );
                result.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            this.connectionPool.parking(connection);
        }
        return result;
    }

    @Override
    public void update(Person p) {
        Connection connection = this.connectionPool.connection();
        String sql = "update people set first_name = ?, last_name = ?, age = ?, city = ? where id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, p.getFirstName());
            statement.setString(2, p.getLastName());
            statement.setInt(3, p.getAge());
            statement.setString(4, p.getCity());
            statement.setLong(5, p.getId());
            int row = statement.executeUpdate();
            if (row != 0) {
                ResultSet resultSet = statement.getGeneratedKeys();
                resultSet.next();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            this.connectionPool.parking(connection);
        }
    }

    @Override
    public void delete(long id) {
        Connection connection = this.connectionPool.connection();
        String sql = "delete from people where id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            this.connectionPool.parking(connection);
        }
    }

}
