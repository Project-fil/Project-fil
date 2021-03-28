package org.bitbucket.app.services.local.file;

import org.bitbucket.app.config.FormatConfig;
import org.bitbucket.app.entity.Person;
import org.bitbucket.app.exceptions.NoSuchIdException;
import org.bitbucket.app.exceptions.NullArgumentException;
import org.bitbucket.app.fomats.BaseFormat;
import org.bitbucket.app.services.IPersonService;
import org.bitbucket.app.utils.FileUtils;

import java.io.File;
import java.util.List;

public class CsvPersonService implements IPersonService {
    
    private final List<Person> people;

    private final BaseFormat format;

    private final File file;

    public CsvPersonService(File file) {
        this.file = file;
        this.format = FormatConfig.csvFormat();
        this.people = format.fromFormat(FileUtils.readFile(file));
    }

    @Override
    public Person create(Person createdPerson) {
        if(createdPerson == null){
            throw new NullArgumentException("Null argument exception.");
        }
        this.people.add(createdPerson);
        FileUtils.writeToFile(file, format.toFormat(this.people));
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
                FileUtils.writeToFile(file, format.toFormat(this.people));
                return updatedPerson;
            }
        }
        throw new NoSuchIdException("There is no person with such ID.");
    }


    @Override
    public void delete(long id) {
        for(Person person : this.people){
            if(person.getId() == id){
                this.people.remove(person);
                FileUtils.writeToFile(file, format.toFormat(this.people));
            }
        }
        throw new NoSuchIdException("There is no person with such ID.");
    }

}
