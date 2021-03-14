package formats;

import org.bitbucket.app.entity.Person;
import org.bitbucket.app.fomats.BinFormat;
import org.bitbucket.app.utils.exceptions.WrongFormatException;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class BinFormatTest {

    private final BinFormat binFormat = new BinFormat();

    @Test
    public void binFromFormatToFormat(){
        Person firstPerson = new Person(1111111111111111111L, "Mark-2", "Eduardovich", 23, "Vladivostok");
        Person secondPerson = new Person(2222222222222222222L, "Willy", "Wonka", 47, "Beijing");
        ArrayList<Person> input = new ArrayList<>();
        input.add(firstPerson);
        input.add(secondPerson);

        ArrayList<Person> output = binFormat.fromFormat(binFormat.toFormat(input));

        Assert.assertEquals(input, output);
    }

    @Test(expected = WrongFormatException.class)
    public void fromFormatNull(){
        binFormat.fromFormat(null);
    }

    @Test
    public void fromFormatEmpty(){
        ArrayList<Person> exp = new ArrayList<>();
        ArrayList<Person> act = binFormat.fromFormat(new byte[0]);
        Assert.assertEquals(exp, act);
    }

    @Test(expected = WrongFormatException.class)
    public void fromFormatRandomBytes(){
        byte[] someBytes = new byte[5];
        someBytes[0] = 1;
        someBytes[1] = 1;
        someBytes[2] = 3;
        someBytes[3] = 1;
        someBytes[4] = 10;
        binFormat.fromFormat(someBytes);
    }

    @Test(expected = WrongFormatException.class)
    public void toFormatNull(){
        binFormat.toFormat(null);
    }

    @Test
    public void toFormatEmpty(){
        ArrayList<Person> people = new ArrayList<>(0);
        byte[] exp = new byte[0];
        byte[] act = binFormat.toFormat(people);
        Assert.assertArrayEquals(exp, act);
    }
}
