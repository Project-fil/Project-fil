package org.bitbucket.app.utils.sort;

import org.bitbucket.app.entity.Person;

import java.util.Comparator;

public class SortCity implements Comparator<Person> {

    public int compare(Person a, Person b) {

        return a.getCity().compareTo(b.getCity());
    }
}