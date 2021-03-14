package org.bitbucket.app.fomats;

import org.bitbucket.app.entity.Person;

import java.util.ArrayList;

public interface BaseFormat {

    ArrayList<Person> fromFormat(String file);

    String toFormat(ArrayList<Person> person);

}
