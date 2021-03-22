package org.bitbucket.app.fomats.impl;

import org.bitbucket.app.fomats.BaseFormat;
import org.bitbucket.app.entity.Person;
import org.bitbucket.app.exceptions.WrongFormatException;

import java.util.ArrayList;
import java.util.List;

public class CsvFormat implements BaseFormat {

    @Override
    public List<Person> fromFormat(String file) {
        if (file == null) {
            throw new WrongFormatException("File input is null.");
        }
        file = file.replaceAll("\r", "");
        List<Person> people = new ArrayList<>();
        String[] subStr;
        String[] insideSubStr;
        String insideDelimiter = ", ";
        String fileDelimiter = "\n";
        subStr = file.split(fileDelimiter);
        long id_person = 0;
        String firstName_person = "";
        String lastName_person = "";
        int age_person = 0;
        String city_person = "";
        for (int i = 1; i < subStr.length; i++) {
            insideSubStr = subStr[i].split(insideDelimiter);
            try {
                for (int j = 0; j < insideSubStr.length; j++) {
                    switch (j) {
                        case (0):
                            id_person = Long.parseLong(insideSubStr[j]);
                            break;
                        case (1):
                            firstName_person = insideSubStr[j];
                            break;
                        case (2):
                            lastName_person = insideSubStr[j];
                            break;
                        case (3):
                            age_person = Integer.parseInt(insideSubStr[j]);
                            break;
                        case (4):
                            city_person = insideSubStr[j];
                            break;
                        default:
                            break;
                    }
                }
                Person csvPerson = new Person(id_person, firstName_person, lastName_person, age_person, city_person);
                people.add(csvPerson);
            } catch (NumberFormatException e) {
                throw new WrongFormatException("Wrong csv-format.");
            }
        }
        return people;
    }

    @Override
    public String toFormat(List<Person> people) {
        if (people == null) {
            throw new WrongFormatException("List is null.");
        }
        StringBuilder stream = new StringBuilder();
        String header = ("id,firstName,lastName,age,city\n");
        stream.append(header);
        for (Person person : people) {
            stream.append(person.getId()).append(", ");
            stream.append(person.getFirstName()).append(", ");
            stream.append(person.getLastName()).append(", ");
            stream.append(person.getAge()).append(", ");
            stream.append(person.getCity()).append("\n");
        }
        return stream.toString();
    }

}
