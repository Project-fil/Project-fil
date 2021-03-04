package com.github.app.dao;

import com.github.app.models.Person;

import java.util.ArrayList;

public interface IDaoPerson {

    Person create(Person createdPerson);
    ArrayList<Person> createAll(ArrayList<Person> createdPeople);

    Person read(long id);
    ArrayList<Person> readAll();

    Person update(Person updatedPerson);
    ArrayList<Person> updateAll(ArrayList<Person> updatedPeople);

    Person delete(long id);
    ArrayList<Person> deleteAll();

    String getPath();

    ArrayList<Person> getPeople();

}
