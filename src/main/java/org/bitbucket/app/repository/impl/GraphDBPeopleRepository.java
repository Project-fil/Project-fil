package org.bitbucket.app.repository.impl;

import org.bitbucket.app.entity.Person;
import org.bitbucket.app.repository.IPeopleRepository;
import org.bitbucket.app.utils.JDBCConnectionPool;

import java.util.List;

public class GraphDBPeopleRepository implements IPeopleRepository {

    JDBCConnectionPool connectionPool;

    public GraphDBPeopleRepository(JDBCConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public Person create(Person p) {
        return null;
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
