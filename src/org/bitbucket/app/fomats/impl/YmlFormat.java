package org.bitbucket.app.fomats.impl;

import org.bitbucket.app.fomats.BaseFormat;
import org.bitbucket.app.entity.Person;
import org.bitbucket.app.utils.exceptions.WrongFormatException;

import java.util.ArrayList;
import java.util.List;

public class YmlFormat implements BaseFormat {

    @Override
    public ArrayList<Person> fromFormat(String file) {

        ArrayList<Person> people = new ArrayList<>();

        long id_person = 0;
        String firstName_person = "";
        String lastName_person = "";
        int age_person = 0;
        String city_person = "";

        String[] parsedData;
        String[] parsedPerson;
        file = file.replaceAll("\r", "");
        file = file.replaceAll(" ", "");

        parsedPerson = file.split("\n");
        int j = parsedPerson.length;
        for (int i = 0; i < j; i++) {
            parsedData = parsedPerson[i].split(":");

            int parserCode = (i + 1) % 6;
            switch (parserCode) {
                case (1):
                    continue;
                case (2):
                    id_person = Long.parseLong(parsedData[1]);
                    continue;
                case (3):
                    firstName_person = parsedData[1];
                    continue;
                case (4):
                    lastName_person = parsedData[1];
                    continue;
                case (5):
                    age_person = Integer.parseInt(parsedData[1]);
                    continue;
                case (0):
                    city_person = parsedData[1];
                    break;
                default:
                    break;
            }
            Person ymlPerson = new Person(id_person, firstName_person, lastName_person, age_person, city_person);
            people.add(ymlPerson);
        }
        return people;

    }

    @Override
    public String toFormat(ArrayList<Person> people) {

        if (people == null) {
            throw new WrongFormatException("Null input list.");
        }
        StringBuilder stream = new StringBuilder();
        for (Person person : people) {
            stream.append("- Person: \n");
            stream.append("    id: ").append(person.getId()).append("\n");
            stream.append("    firstName: ").append(person.getFirstName()).append("\n");
            stream.append("    lastName: ").append(person.getLastName()).append("\n");
            stream.append("    age: ").append(person.getAge()).append("\n");
            stream.append("    city: ").append(person.getCity()).append("\n");
        }
        return stream.toString();
    }

}
