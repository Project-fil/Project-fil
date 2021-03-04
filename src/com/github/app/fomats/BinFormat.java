package com.github.app.fomats;

import com.github.app.models.Person;
import com.github.app.utils.exceptions.WrongFormatException;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class BinFormat {

    public ArrayList<Person> fromFormat(byte[] serializedPeople){
        if(serializedPeople == null){
            throw new WrongFormatException("Given byte array is empty");
        }
        if(Arrays.equals(serializedPeople, new byte[0])){
            return new ArrayList<>();
        }

        ArrayList<Person> people;

        try(ByteArrayInputStream bais = new ByteArrayInputStream(serializedPeople);
            ObjectInputStream ois = new ObjectInputStream(bais)) {
            people = cast(ois.readObject());
        }
        catch (IOException e) {
            throw new WrongFormatException("Failed to convert byte array to person list", e);
        }
        catch (ClassNotFoundException e){
            throw new WrongFormatException("Failed to read an object.");
        }
        return people;
    }

    public byte[] toFormat(ArrayList<Person> people){

        if(people == null){
            throw new WrongFormatException("People array is null.");
        }
        if(people.size() == 0){
            return new byte[0];
        }

        byte[] stream;

        try(ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos)){
            oos.writeObject(people);
            stream = baos.toByteArray();
        }
        catch (IOException e) {
            throw new WrongFormatException("Failed to convert people array to byte array");
        }
        return stream;
    }

    @SuppressWarnings("unchecked")
    private ArrayList<Person> cast(Object o){
        return (ArrayList<Person>) o;
    }

}
