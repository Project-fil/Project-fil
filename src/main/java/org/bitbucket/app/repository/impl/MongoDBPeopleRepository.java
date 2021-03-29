package org.bitbucket.app.repository.impl;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bitbucket.app.entity.Person;
import org.bitbucket.app.repository.IPeopleRepository;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class MongoDBPeopleRepository implements IPeopleRepository {

    private final String connectionString;

    private final String database;

    public MongoDBPeopleRepository(String host, int port, String user, char[] password, String database) {
        this.connectionString = "mongodb://"
                + user + ":" + String.valueOf(password) + "@" +
                host + ":" + port;
        this.database = database;
    }

    @Override
    public Person create(Person p) {
        System.out.println(connectionString);
        MongoClient mongoClient = new MongoClient(
                new MongoClientURI(connectionString)
        );
        MongoDatabase database = mongoClient.getDatabase(this.database);
        MongoCollection<Document> collection = database.getCollection("people");
        Document person = new Document("id", new ObjectId());
        person.append("id", p.getId())
                .append("first_name", p.getFirstName())
                .append("last_name", p.getLastName())
                .append("age", p.getAge())
                .append("city", p.getCity());
        collection.insertOne(person);
        return new Person(p);
    }

    @Override
    public List<Person> readAll() {
        MongoClient mongoClient = new MongoClient(
                new MongoClientURI(connectionString)
        );
        MongoDatabase database = mongoClient.getDatabase(this.database);
        MongoCollection<Document> collection = database.getCollection("people");
        List<Person> result = new ArrayList<>();
        try (MongoCursor<Document> cursor = collection.find().iterator()) {
            while (cursor.hasNext()) {
                Object[] collectionEntry = cursor.next().values().toArray();
                long id = Long.parseLong(collectionEntry[1].toString());
                String firstName = collectionEntry[2].toString();
                String lastName = collectionEntry[3].toString();
                int age = Integer.parseInt(collectionEntry[4].toString());
                String city = collectionEntry[5].toString();
                result.add(new Person(id, firstName, lastName, age, city));
            }
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
