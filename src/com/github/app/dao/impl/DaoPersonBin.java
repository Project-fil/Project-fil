package com.github.app.dao.impl;

import com.github.app.config.factories.FMBin;
import com.github.app.dao.IDaoPerson;
import com.github.app.fomats.BinFormat;
import com.github.app.models.Person;
import com.github.app.utils.exceptions.DifferentArraySizesException;
import com.github.app.utils.exceptions.NoSuchIdException;
import com.github.app.utils.exceptions.NullArgumentException;
import com.github.app.utils.exceptions.WrongFormatException;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class DaoPersonBin implements IDaoPerson {

    private final ArrayList<Person> people;

    private final BinFormat format;

    private final String path;

    public DaoPersonBin(String path) {
        this.path = path;
        this.format = FMBin.binFormat();
        try {
            this.people = format.fromFormat(FileUtils.readFileToByteArray(new File(path)));
        } catch (IOException e){
            throw new WrongFormatException("Failed to read a file.");
        }
    }

    @Override
    public Person create(Person createdPerson) {
        if(createdPerson == null){
            throw new NullArgumentException("Null argument exception.");
        }
        this.people.add(createdPerson);
        try {
            FileUtils.writeByteArrayToFile(new File(path), format.toFormat(this.people));
        } catch (IOException e){
            throw new WrongFormatException("Failed to write a file.");
        }
        return createdPerson;
    }

    @Override
    public ArrayList<Person> createAll(ArrayList<Person> createdPeople) {
        if(this.people == null){
            throw new NullArgumentException("Null argument exception.");
        }
        this.people.addAll(createdPeople);
        try {
            FileUtils.writeByteArrayToFile(new File(path), format.toFormat(this.people));
        } catch (IOException e){
            throw new WrongFormatException("Failed to write a file.");
        }
        return this.people;
    }

    @Override
    public Person read(long id) {
        for(Person person : this.people){
            if(person.getId() == id){
                return person;
            }
        }
        throw new NoSuchIdException("There is no person with such ID.");
    }

    @Override
    public ArrayList<Person> readAll() {
        return this.people;
    }

    @Override
    public Person update(Person updatedPerson) {
        if(updatedPerson == null) {
            throw new WrongFormatException("Null argument exception.");
        }
        for(Person person : this.people){
            if(person.getId() == updatedPerson.getId()){
                person = updatedPerson;
                try {
                    FileUtils.writeByteArrayToFile(new File(path), format.toFormat(this.people));
                } catch (IOException e){
                    throw new WrongFormatException("Failed to write a file.");
                }
                return person;
            }
        }
        throw new NoSuchIdException("There is no person with such ID.");
    }

    @Override
    public ArrayList<Person> updateAll(ArrayList<Person> updatedPeople) {
        if(updatedPeople == null){
            throw new NullArgumentException("Null argument exception");
        }
        if(updatedPeople.size() != this.people.size()){
            throw new DifferentArraySizesException("Sizes of arrays are not same.");
        }
        for(int i = 0; i < this.people.size(); i++){
            if(this.people.get(i).getId() != updatedPeople.get(i).getId()){
                throw new NoSuchIdException("Id's are not same.");
            }
            this.people.set(i, updatedPeople.get(i));
        }
        try {
            FileUtils.writeByteArrayToFile(new File(path), format.toFormat(this.people));
        } catch (IOException e){
            throw new WrongFormatException("Failed to write a file.");
        }
        return this.people;
    }

    @Override
    public Person delete(long id) {
        for(Person person : this.people){
            if(person.getId() == id){
                this.people.remove(person);
                try {
                    FileUtils.writeByteArrayToFile(new File(path), format.toFormat(this.people));
                } catch (IOException e){
                    throw new WrongFormatException("Failed to write a file.");
                }
                return person;
            }
        }
        throw  new NoSuchElementException("There is no person with such ID.");
    }

    @Override
    @SuppressWarnings("unchecked")
    public ArrayList<Person> deleteAll() {
        ArrayList<Person> temp = (ArrayList<Person>) this.people.clone();
        this.people.clear();
        return temp;
    }

    @Override
    public String getPath() {
        return this.path;
    }

    @Override
    public ArrayList<Person> getPeople() {
        return this.people;
    }

}
