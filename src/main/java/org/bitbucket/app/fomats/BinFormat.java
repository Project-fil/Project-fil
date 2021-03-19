package org.bitbucket.app.fomats;

import org.bitbucket.app.entity.Person;
import org.bitbucket.app.exceptions.WrongFormatException;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BinFormat {

    public List<Person> fromFormat(byte[] serializedPeople){

        if(serializedPeople == null){
            throw new WrongFormatException("Given byte array is empty");
        }
        if(Arrays.equals(serializedPeople, new byte[0])){
            return new ArrayList<>();
        }

        List<Person> people;

        try(ByteArrayInputStream byteInputStream = new ByteArrayInputStream(serializedPeople);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteInputStream)) {
            people = cast(objectInputStream.readObject());
        } catch (IOException e) {
            throw new WrongFormatException("Failed to convert byte array to person list");
        } catch (ClassNotFoundException e){
            throw new WrongFormatException("Failed to read an object.");
        }
        return people;

    }

    public byte[] toFormat(List<Person> people){

        if(people == null){
            throw new WrongFormatException("People array is null.");
        }
        if(people.size() == 0){
            return new byte[0];
        }
        byte[] stream;

        try(ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteOutputStream)){
            objectOutputStream.writeObject(people);
            stream = byteOutputStream.toByteArray();
        } catch (IOException e) {
            throw new WrongFormatException("Failed to convert people array to byte array");
        }
        return stream;

    }

    @SuppressWarnings("unchecked")
    private List<Person> cast(Object o){
        return (ArrayList<Person>) o;
    }

}
