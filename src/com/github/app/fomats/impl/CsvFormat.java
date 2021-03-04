package com.github.app.fomats.impl;

import com.github.app.fomats.BaseFormat;
import com.github.app.models.Person;

import java.util.ArrayList;

public class CsvFormat implements BaseFormat {

    @Override
    public ArrayList<Person> fromFormat(String file) {
        return null;
    }

    @Override
    public String toFormat(ArrayList<Person> person) {
        return null;
    }

}
