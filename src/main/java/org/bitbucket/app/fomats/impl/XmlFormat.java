package org.bitbucket.app.fomats.impl;

import org.bitbucket.app.fomats.BaseFormat;
import org.bitbucket.app.entity.Person;
import org.bitbucket.app.exceptions.WrongFormatException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XmlFormat implements BaseFormat {

    @Override
    public List<Person> fromFormat(String file) {
        ArrayList<Person> personList = new ArrayList<>();
        Pattern pattern = Pattern.compile("<person>.*?<id>(.*?)</id>.*?<firstName>(.*?)</firstName>.*?<lastName>(.*?)</lastName>.*?<age>(.*?)</age>.*?<city>(.*?)</city>.*?</person>",
                Pattern.MULTILINE | Pattern.DOTALL);
        Matcher matcher = pattern.matcher(file);
        while (matcher.find()) {
            personList.add(new Person(Long.parseLong(matcher.group(1)), matcher.group(2), matcher.group(3), Integer.parseInt(matcher.group(4)), matcher.group(5)));
        }
        return personList;
    }

    @Override
    public String toFormat(List<Person> people) throws WrongFormatException {
        if(people == null) {throw new WrongFormatException("Null person");}

        StringBuilder result = new StringBuilder();
        result.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n")
                .append("<people>\n");
        for (Person person : people) {
            result.append(toFormat(person))
                    .append("\n");
        }
        result.append("</people>");
        return result.toString();
    }

    public String toFormat(Person person) throws WrongFormatException{

        if(person == null) {throw new WrongFormatException("Null person");}

        return String.format("\t<person>\n\t\t<id>%d</id>\n\t\t<firstName>%s</firstName>\n\t\t<lastName>%s</lastName>\n\t\t<age>%s</age>\n\t\t<city>%s</city>\n\t</person>",
                person.getId(),
                person.getFirstName(),
                person.getLastName(),
                person.getAge(),
                person.getCity()
        );
    }

}
