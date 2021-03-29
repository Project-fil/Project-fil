package org.bitbucket.app.repository.impl;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bitbucket.app.entity.Person;
import org.bitbucket.app.repository.IPeopleRepository;
import org.bitbucket.app.utils.JDBCConnectionPool;
import org.bson.Document;

import java.sql.*;
import java.util.*;

public class MongoDBPeopleRepository implements IPeopleRepository {

    JDBCConnectionPool connectionPool;

    public MongoDBPeopleRepository(JDBCConnectionPool connectionPool){
        this.connectionPool = connectionPool;
    }


    @Override
    public Person create(Person p) {
        return null;
    }

    @Override
    public List<Person> readAll() {
        char[] password = {'p', 'a', 's', 's', 'w', 'o', 'r', 'd'};
        ServerAddress address = new ServerAddress("localhost", 27017);
        MongoCredential credential = MongoCredential.createCredential("mongo", "people", password);
        MongoClientOptions options = MongoClientOptions.builder().sslEnabled(true).build();
        MongoClient client = new MongoClient(address, Collections.singletonList(credential), options);
        MongoDatabase database = client.getDatabase("people");
        MongoCollection<Document> collection = database.getCollection("people");
        FindIterable<Document> iterDoc = collection.find();
        Iterator iterator = iterDoc.iterator();
        List<Person> result = new ArrayList<>();
        while (iterator.hasNext()){
            result.add((Person) iterator.next());
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
            this.connectionPool .parking(connection);
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
