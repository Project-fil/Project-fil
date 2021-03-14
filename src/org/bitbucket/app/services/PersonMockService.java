package org.bitbucket.app.services;

import org.bitbucket.app.entity.Person;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PersonMockService implements IPeopleService{

    public List<Person> people = new ArrayList<>();

    @Override
    public Person create(Person createdPerson) {
        return null;
    }

    @Override
    public List<Person> createAll(ArrayList<Person> createdPeople) {
        return null;
    }

    @Override
    public Person read(long id) {
        return null;
    }

    @Override
    public List<Person> readAll() {
        this.people.add(new Person(1234567890987654321L, "Porco", "Rosso", 34, "Venice"));
        this.people.add(new Person(1234567890987654321L, "Porco", "Rosso", 34, "Venice"));
        this.people.add(new Person(1234567890987654321L, "Porco", "Rosso", 34, "Venice"));
        this.people.add(new Person(1234567890987654321L, "Porco", "Rosso", 34, "Venice"));
        this.people.add(new Person(1234567890987654321L, "Porco", "Rosso", 34, "Venice"));
        this.people.add(new Person(1234567890987654321L, "Porco", "Rosso", 34, "Venice"));
        this.people.add(new Person(1234567890987654321L, "Porco", "Rosso", 34, "Venice"));
        this.people.add(new Person(1234567890987654321L, "Porco", "Rosso", 34, "Venice"));
        this.people.add(new Person(1234567890987654321L, "Porco", "Rosso", 34, "Venice"));
        this.people.add(new Person(1234567890987654321L, "Porco", "Rosso", 34, "Venice"));
        this.people.add(new Person(1234567890987654321L, "Porco", "Rosso", 34, "Venice"));
        this.people.add(new Person(1234567890987654321L, "Porco", "Rosso", 34, "Venice"));
        this.people.add(new Person(1234567890987654321L, "Porco", "Rosso", 34, "Venice"));
        return this.people;
    }

    @Override
    public Person update(Person updatedPerson) {
        return null;
    }

    @Override
    public List<Person> updateAll(ArrayList<Person> updatedPeople) {
        return null;
    }

    @Override
    public Person delete(long id) {
        return null;
    }

    @Override
    public List<Person> deleteAll() {
        return null;
    }

    @Override
    public File getFile() {
        return null;
    }

    @Override
    public List<Person> getPeople() {
        return null;
    }
}
