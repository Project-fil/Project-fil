package org.bitbucket.app.repository.impl;

import com.datastax.driver.core.*;
import org.bitbucket.app.entity.Person;
import org.bitbucket.app.repository.IPeopleRepository;

import java.util.ArrayList;
import java.util.List;

public class CassandraPeopleRepository implements IPeopleRepository {

    private final Cluster cluster;

    private final Session session;

    public CassandraPeopleRepository(String contactPoint, String user, String password, int port, String keyspace) {
        this.cluster = Cluster.builder()
                .addContactPoint(contactPoint)
                .withCredentials(user, password)
                .withPort(port)
                .build();
        this.session = this.cluster.connect(keyspace);
    }

    @Override
    public Person create(Person p) {
        PreparedStatement statement =
                this.session.prepare("insert into people (id, first_name, last_name, age, city) values (?, ?, ?, ?, ?)");
        this.session.execute(statement.bind(
                p.getId(),
                p.getFirstName(),
                p.getLastName(),
                p.getAge(),
                p.getCity()
        ));
        return new Person(p);
    }

    @Override
    public List<Person> readAll() {
        String query = "select * from people";
        ResultSet resultSet = this.session.execute(query);
        List<Row> rows = resultSet.all();
        List<Person> result = new ArrayList<>();
        for (Row row : rows) {
            long id = row.getLong(0);
            String firstName = row.getString(3);
            String lastName = row.getString(4);
            int age = row.getInt(1);
            String city = row.getString(2);
            result.add(new Person(
                    id, firstName, lastName, age, city
            ));
        }
        return result;
    }

    @Override
    public void update(Person p) {
        PreparedStatement statement =
                this.session.prepare("update people set first_name = ?, last_name = ?, age = ?, city = ? where id = ?");
        this.session.execute(statement.bind(
                p.getFirstName(),
                p.getLastName(),
                p.getAge(),
                p.getCity(),
                p.getId()
        ));
    }

    @Override
    public void delete(long id) {
        PreparedStatement statement =
                this.session.prepare("delete from people where id = ?");
        this.session.execute(statement.bind(id));
    }

}
