package org.bitbucket.test.formats;

import org.bitbucket.app.entity.Person;
import org.bitbucket.app.fomats.BinFormat;
import org.bitbucket.app.fomats.impl.JsonFormat;
import org.bitbucket.app.utils.exceptions.WrongFormatException;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;


public class JsonFormatTest {

    private final JsonFormat jsonFormat = new JsonFormat();

    @Test
    public void fromFormatEmpty() {
        ArrayList<Person> exp = new ArrayList<>();
        ArrayList<Person> act = jsonFormat.fromFormat(String.valueOf(new ArrayList<Person>()));
        Assert.assertEquals(exp, act);
    }

}
