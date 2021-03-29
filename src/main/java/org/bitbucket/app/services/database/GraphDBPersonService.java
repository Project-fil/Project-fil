package org.bitbucket.app.services.database;

import org.bitbucket.app.entity.Person;
import org.bitbucket.app.repository.IPeopleRepository;
import org.bitbucket.app.services.IPersonService;
import org.bitbucket.app.utils.JDBCConnectionPool;

import java.util.List;

public class GraphDBPersonService implements IPersonService {

    IPeopleRepository repository;

    public GraphDBPersonService(IPeopleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Person create(Person createdPerson) {
        return this.repository.create(createdPerson);
    }

    @Override
    public List<Person> readAll() {
        return this.repository.readAll();
    }

    @Override
    public Person update(Person updatedPerson) {
        this.repository.update(updatedPerson);
        return new Person(updatedPerson);
    }

    @Override
    public void delete(long id) {
        this.repository.delete(id);
    }

}
