package org.bitbucket.app.repository;

import org.bitbucket.app.entity.Person;

import java.io.File;
import java.util.ArrayList;

public interface ICrud {

    Person create(Person createdPerson);
    ArrayList<Person> createAll(ArrayList<Person> createdPeople);

    Person read(long id);
    ArrayList<Person> readAll();

    Person update(Person updatedPerson);
    ArrayList<Person> updateAll(ArrayList<Person> updatedPeople);

    Person delete(long id);
    ArrayList<Person> deleteAll();

    File getFile();

    ArrayList<Person> getPeople();

}
