package org.bitbucket.app.services.local.file;

import org.bitbucket.app.config.FormatConfig;
import org.bitbucket.app.entity.Person;
import org.bitbucket.app.exceptions.NoSuchIdException;
import org.bitbucket.app.exceptions.NullArgumentException;
import org.bitbucket.app.fomats.BinFormat;
import org.bitbucket.app.services.IPersonService;
import org.bitbucket.app.utils.FileUtils;

import java.io.File;
import java.util.List;

public class BinPersonService implements IPersonService {

    private final List<Person> people;

    private final BinFormat format;

    private final File file;

    public BinPersonService(File file) {
        this.file = file;
        this.format = FormatConfig.binFormat();
        this.people = format.fromFormat(FileUtils.readBinFile(file));
    }

    @Override
    public Person create(Person createdPerson) {
        if(createdPerson == null){
            throw new NullArgumentException("Null argument exception.");
        }
        this.people.add(createdPerson);
        FileUtils.writeToBinFile(file, format.toFormat(this.people));
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
                FileUtils.writeToBinFile(file, format.toFormat(this.people));
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
                FileUtils.writeToBinFile(file, format.toFormat(this.people));
                return person;
            }
        }
        throw new NoSuchIdException("There is no person with such ID.");
    }

}
