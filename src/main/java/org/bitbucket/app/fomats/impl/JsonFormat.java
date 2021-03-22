package org.bitbucket.app.fomats.impl;

import org.bitbucket.app.entity.Person;
import org.bitbucket.app.fomats.BaseFormat;
import org.bitbucket.app.utils.PatternMatcher;
import org.bitbucket.app.exceptions.WrongFormatException;

import java.util.ArrayList;
import java.util.List;

public class JsonFormat implements BaseFormat {

    @Override
    public List<Person> fromFormat(String file) throws WrongFormatException {

        List<Person> personList = new ArrayList<>();
        if (!PatternMatcher.isBlank(file)) {
            List<String> personJsonList = PatternMatcher.jsonToPersonList(file);
            try {
                for (String each : personJsonList) {
                    personList.add(this.toPerson(each));
                }
            } catch (WrongFormatException wfe) {
                throw new WrongFormatException(wfe.getMessage());
            }
        }
        return personList;
    }

    private Person toPerson(String one) throws WrongFormatException {

        String[] param = PatternMatcher.removeAllJsonSyntax(one).split(",");

        long id = 0;
        String firstName = "";
        String lastName = "";
        int age = 0;
        String city = "";

        for (String each : param) {
            int i = each.indexOf(':');
            if (i == -1) {
                throw new WrongFormatException("Wrong format.");
            }
            switch (each.substring(0, i)) {
                case "id":
                    id = Long.parseLong(each.substring(i + 1));
                    break;
                case "firstName":
                    firstName = each.substring(i + 1);
                    break;
                case "lastName":
                    lastName = each.substring(i + 1);
                    break;
                case "age":
                    age = Integer.parseInt(each.substring(i + 1));
                    break;
                case "city":
                    city = each.substring(i + 1);
                    break;
                default:
                    throw new WrongFormatException("Wrong parameter of person.");
            }
        }

        return new Person(id, firstName, lastName, age, city);
    }

    @Override
    public String toFormat(List<Person> people) throws WrongFormatException {

        if (people == null) {
            throw new WrongFormatException("Null argument.");
        }

        if (people.size() == 0) {
            return "{}";
        }

        StringBuilder result = new StringBuilder();
        if (people.size() == 1) {
            result.append("{\"person\":");
            result.append(toFormat(people.get(0)));
            result.append("}");
        } else {
            result.append("{\"people\": [\n");
            for (Person each : people) {
                result.append(toFormat(each));
                result.append(",\n");
            }
            result.deleteCharAt(result.length() - 2);
            result.append("]}");
        }

        return result.toString();
    }

    public String toFormat(Person person) throws WrongFormatException {

        if (person == null) {
            throw new WrongFormatException("Null person.");
        }

        return String.format("{\n \t\"id\": %d,\n\t\"firstName\": \"%s\",\n\t\"lastName\": \"%s\",\n\t\"age\": %d,\n\t\"city\": \"%s\"\n}",
                person.getId(),
                person.getFirstName(),
                person.getLastName(),
                person.getAge(),
                person.getCity()
        );
    }

}
