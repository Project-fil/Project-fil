package org.bitbucket.app.utils.sort;

import org.bitbucket.app.entity.Person;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;

public class SortId implements Comparator<Person> {

    @Override
    public int compare(Person o1, Person o2) {
            if (o1.getId() > o2.getId()) {
                return 1;
            } else if (o1.getId() < o2.getId()) {
                return -1;
            } else {
                return 0;
            }
    }
}
