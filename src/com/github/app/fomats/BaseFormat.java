package com.github.app.fomats;

import com.github.app.models.Person;

import java.util.ArrayList;

public interface BaseFormat {

    ArrayList<Person> fromFormat(String file);

    String toFormat(ArrayList<Person> person);

}
