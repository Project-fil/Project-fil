package org.bitbucket.app.utils;

import org.bitbucket.app.entity.Person;
import org.bitbucket.app.utils.sort.*;

import java.util.*;

public class PeopleUtils {
    public static List<Person> sortById(List<Person> people) {
        Collections.sort(people, new SortId());
        return people;
    }

    public static List<Person> sortByFirstName(List<Person> people){
Collections.sort(people, new SortFirstName());
        return people;
    }

    public static List<Person> sortByLastName(List<Person> people){
        Collections.sort(people, new SortLastName());
        return people;
    }

    public static List<Person> sortByAge(List<Person> people){
        Collections.sort(people, new SortAge());
        return people;
    }

    public static List<Person> sortByCity(List<Person> people){
        Collections.sort(people, new SortCity());
        return people;
    }
}
