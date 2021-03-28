package org.bitbucket.app.repository;

import org.bitbucket.app.entity.Person;

import java.util.List;

public interface IPeopleRepository {

    Person create(Person p);

    List<Person> readAll();

    void update(Person p);

    void delete(long id);

}
