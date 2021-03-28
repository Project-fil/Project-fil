package org.bitbucket.app.services.database;

import org.bitbucket.app.entity.Person;
import org.bitbucket.app.services.IPersonService;

import java.util.List;

public class RedisPersonService implements IPersonService {

    @Override
    public Person create(Person createdPerson) {
        return null;
    }

    @Override
    public List<Person> readAll() {
        return null;
    }

    @Override
    public Person update(Person updatedPerson) {
        return null;
    }

    @Override
    public void delete(long id) {
    }

}
