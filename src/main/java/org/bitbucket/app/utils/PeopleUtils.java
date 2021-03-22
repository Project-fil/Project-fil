package org.bitbucket.app.utils;

import org.bitbucket.app.entity.Person;
import org.bitbucket.app.utils.sort.*;

import java.util.*;

public class PeopleUtils {

    public static List<Person> sortById(List<Person> people) {
        people.sort(new SortId());
        return people;
    }

    public static List<Person> sortByFirstName(List<Person> people){
        people.sort(new SortFirstName());
        return people;
    }

    public static List<Person> sortByLastName(List<Person> people){
        people.sort(new SortLastName());
        return people;
    }

    public static List<Person> sortByAge(List<Person> people){
        people.sort(new SortAge());
        return people;
    }

    public static List<Person> sortByCity(List<Person> people){
        people.sort(new SortCity());
        return people;
    }

}
