package org.bitbucket.app.repository.impl;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bitbucket.app.entity.Person;
import org.bitbucket.app.repository.IPeopleRepository;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class MongoDBPeopleRepository implements IPeopleRepository {

    private final String connectionString;

    private final MongoClient client;

    private final MongoDatabase database;

    private final MongoCollection<Document> collection;

    public MongoDBPeopleRepository(String host, int port, String user, String password, String databaseName) {

        this.connectionString = "mongodb://" + user + ":" + password + "@" + host + ":" + port;

        this.client = new MongoClient(new MongoClientURI(connectionString));
        this.database = client.getDatabase(databaseName);
        this.collection = this.database.getCollection("people");

    }

    @Override
    public Person create(Person p) {
        Document person = new Document("id", new ObjectId());
        person.append("id", p.getId())
                .append("first_name", p.getFirstName())
                .append("last_name", p.getLastName())
                .append("age", p.getAge())
                .append("city", p.getCity());
        this.collection.insertOne(person);
        return new Person(p);
    }

    @Override
    public List<Person> readAll() {
        List<Person> result = new ArrayList<>();
        try(MongoCursor<Document> cursor = this.collection.find().iterator()){
            while (cursor.hasNext()){
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
        Bson filter = Filters.eq("id", p.getId());
        this.collection.updateOne(filter, Updates.set("first_name", p.getFirstName()));
        this.collection.updateOne(filter, Updates.set("last_name", p.getLastName()));
        this.collection.updateOne(filter, Updates.set("age", p.getAge()));
        this.collection.updateOne(filter, Updates.set("city",p.getCity()));
    }

    @Override
    public void delete(long id) {
        this.collection.deleteOne(Filters.eq("id", id));
    }

}
