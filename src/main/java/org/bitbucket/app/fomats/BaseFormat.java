package org.bitbucket.app.fomats;

import org.bitbucket.app.entity.Person;

import java.util.ArrayList;
import java.util.List;

public interface BaseFormat {

    List<Person> fromFormat(String file);

    String toFormat(List<Person> person);

}
