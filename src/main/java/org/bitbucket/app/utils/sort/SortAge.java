package org.bitbucket.app.utils.sort;

import org.bitbucket.app.entity.Person;

import java.util.Comparator;

public class SortAge implements Comparator<Person> {

    public int compare(Person o1, Person o2) {
        if (o1.getAge() > o2.getAge()) {
            return 1;
        } else if (o1.getAge() < o2.getAge()) {
            return -1;
        } else {
            return 0;
        }
    }
}