package org.bitbucket.app.services;

import org.bitbucket.app.entity.Person;
import org.bitbucket.app.exceptions.NoSuchIdException;
import org.bitbucket.app.exceptions.NullArgumentException;

import java.util.ArrayList;
import java.util.List;

public class PersonMockService implements IPersonService {

    public List<Person> people = new ArrayList<>();

    @Override
    public Person create(Person createdPerson) {
        if(createdPerson == null){
            throw new NullArgumentException("Null argument exception.");
        }
        this.people.add(createdPerson);
        return createdPerson;
    }
    @Override
    public List<Person> readAll() {
        return this.people;
    }

    @Override
    public Person update(Person updatedPerson) {
        if(updatedPerson == null) {
            throw new NullArgumentException("Null argument exception.");
        }
        for(int i = 0; i < this.people.size(); i++){
            if(this.people.get(i).getId() == updatedPerson.getId()){
                this.people.set(i, updatedPerson);
                return updatedPerson;
            }
        }
        throw new NoSuchIdException("There is no person with such ID.");
    }

    @Override
    public Person delete(long id) {
        for(Person person : this.people){
            if(person.getId() == id){
                this.people.remove(person);
                return person;
            }
        }
        throw new NoSuchIdException("There is no person with such ID.");
    }
}
