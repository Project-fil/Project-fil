package org.bitbucket.app.repository.locale;

import org.bitbucket.app.config.formats_config.FMCsv;
import org.bitbucket.app.entity.Person;
import org.bitbucket.app.fomats.BaseFormat;
import org.bitbucket.app.repository.ICrud;
import org.bitbucket.app.utils.FileUtils;
import org.bitbucket.app.utils.exceptions.DifferentArraySizesException;
import org.bitbucket.app.utils.exceptions.NoSuchIdException;
import org.bitbucket.app.utils.exceptions.NullArgumentException;
import org.bitbucket.app.utils.exceptions.WrongFormatException;

import java.io.File;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class DaoPersonCsv implements ICrud {
    
    private final ArrayList<Person> people;

    private final BaseFormat format;

    private final String path;

    public DaoPersonCsv(String path) {
        this.path = path;
        this.format = FMCsv.csvFormat();
        this.people = format.fromFormat(FileUtils.readFile(new File(path)));
    }

    @Override
    public Person create(Person createdPerson) {
        if(createdPerson == null){
            throw new NullArgumentException("Null argument exception.");
        }
        this.people.add(createdPerson);
        FileUtils.writeToFile(new File(path), format.toFormat(this.people));
        return createdPerson;
    }

    @Override
    public ArrayList<Person> createAll(ArrayList<Person> createdPeople) {
        if(this.people == null){
            throw new NullArgumentException("Null argument exception.");
        }
        this.people.addAll(createdPeople);
        FileUtils.writeToFile(new File(path), format.toFormat(this.people));
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
                FileUtils.writeToFile(new File(path), format.toFormat(this.people));
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
        FileUtils.writeToFile(new File(path), format.toFormat(this.people));
        return this.people;
    }

    @Override
    public Person delete(long id) {
        for(Person person : this.people){
            if(person.getId() == id){
                this.people.remove(person);
                FileUtils.writeToFile(new File(path), format.toFormat(this.people));
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
