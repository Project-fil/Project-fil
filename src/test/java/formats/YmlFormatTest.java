package formats;

import org.bitbucket.app.entity.Person;
import org.bitbucket.app.fomats.impl.YmlFormat;
import org.bitbucket.app.exceptions.WrongFormatException;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class YmlFormatTest {

    private Person person = new Person(1231234213414244531L, "Denys", "Fe", 21, "Kyy");

    private YmlFormat ymlFormat = new YmlFormat();

    private String onePersonYml = "- Person: \n" +
            "    id: 1231234213414244531\n" +
            "    firstName: Denys\n" +
            "    lastName: Fe\n" +
            "    age: 21\n" +
            "    city: Kyy";

    private String twoPersonYml = "- Person: \n" +
            "    id: 1231234213414244531\n" +
            "    firstName: Denys\n" +
            "    lastName: Fe\n" +
            "    age: 21\n" +
            "    city: Kyy\n" +
            "- Person: \n" +
            "    id: 1231234213414244531\n" +
            "    firstName: Denys\n" +
            "    lastName: Fe\n" +
            "    age: 21\n" +
            "    city: Kyy";

    @Test
    public void testToFormatToFormatYML() {

        Person firstPerson = new Person(8970908468289978368L, "Mark-2", "Eduardovich", 23, "Vladivostok");
        Person secondPerson = new Person(11112, "Willy", "Wonka", 47, "Beijing");
        ArrayList<Person> input = new ArrayList<>();
        input.add(firstPerson);
        input.add(secondPerson);

        List<Person> output = ymlFormat.fromFormat(ymlFormat.toFormat(input));

        Assert.assertArrayEquals(input.toArray(), output.toArray());

    }

    @Test
    public void fromFormatOnePerson() {

        ArrayList<Person> personList = new ArrayList<>();
        personList.add(person);
        Assert.assertArrayEquals(personList.toArray(), ymlFormat.fromFormat(onePersonYml).toArray());

    }

    @Test
    public void fromFormatTwoPerson() {

        ArrayList<Person> personList = new ArrayList<>();
        personList.add(person);
        personList.add(person);
        Assert.assertArrayEquals(personList.toArray(), ymlFormat.fromFormat(twoPersonYml).toArray());

    }

    @Test
    public void toFormatOnePerson() {
        ArrayList<Person> personList = new ArrayList<>();
        personList.add(person);
        Assert.assertEquals(onePersonYml, ymlFormat.toFormat(personList).trim());
    }

    @Test
    public void toFormatPeople() {

        ArrayList<Person> personList = new ArrayList<>();
        personList.add(person);
        personList.add(person);
        Assert.assertEquals(twoPersonYml, ymlFormat.toFormat(personList).trim());

    }

    @Test(expected = WrongFormatException.class)
    public void toFormatNullListException() {
        ArrayList<Person> a = null;
        ymlFormat.toFormat(a);
    }
}

