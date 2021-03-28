package org.bitbucket.app.services.database;

import org.bitbucket.app.entity.Person;
import org.bitbucket.app.repository.IPeopleRepository;
import org.bitbucket.app.services.IPersonService;

import java.util.List;

public class PostgreSqlPersonService implements IPersonService {

    IPeopleRepository repository;

    public PostgreSqlPersonService(IPeopleRepository repository) {
        this.repository = repository;
    }
    @Override
    public Person create(Person createdPerson) {return this.repository.create(createdPerson);
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
    public void delete(long id) { this.repository.delete(id);
    }

}
