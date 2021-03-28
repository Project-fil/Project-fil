package org.bitbucket.app.services;

import org.bitbucket.app.entity.Person;

import java.util.List;

public interface IPersonService {

    Person create(Person createdPerson);

    List<Person> readAll();

    Person update(Person updatedPerson);

    void delete(long id);

}
