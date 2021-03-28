package org.bitbucket.app.repository.impl;

import org.bitbucket.app.entity.Person;
import org.bitbucket.app.repository.IPeopleRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgreSQLPeopleRepository implements IPeopleRepository {

    public PostgreSQLPeopleRepository(){
        try{
            Class.forName("");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    @Override
    public Person create(Person p) {
        try{
            String sql = "insert into people (first_name, last_name, age, city) values(?, ?, ?, ?)";
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost/hw_people_dev.db", "user", "password");
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, p.getFirstName());
            statement.setString(2, p.getLastName());
            statement.setInt(3, p.getAge());
            statement.setString(4, p.getCity());
            int r = statement.executeUpdate();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Person> readAll() {
        List<Person> result = new ArrayList<>();
        try {
            String sql = "select * from people";
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost/hw_people_dev.db", "user", "password");
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Person p = new Person(
                        resultSet.getLong("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getInt("age"),
                        resultSet.getString("city")
                );
                result.add(p);
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public void update(Person p) {

    }

    @Override
    public void delete(long id) {

    }

}
