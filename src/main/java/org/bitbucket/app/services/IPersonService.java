package org.bitbucket.app.services;

import org.bitbucket.app.entity.Person;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public interface IPersonService {

    Person create(Person createdPerson);
    List<Person> createAll(ArrayList<Person> createdPeople);

    Person read(long id);
    List<Person> readAll();

    Person update(Person updatedPerson);
    List<Person> updateAll(ArrayList<Person> updatedPeople);

    Person delete(long id);
    List<Person> deleteAll();

    File getFile();

    List<Person> getPeople();

}
