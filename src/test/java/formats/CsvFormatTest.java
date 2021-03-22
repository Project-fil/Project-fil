package formats;

import org.bitbucket.app.entity.Person;
import org.bitbucket.app.fomats.impl.CsvFormat;
import org.bitbucket.app.exceptions.WrongFormatException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

public class CsvFormatTest {

    private final CsvFormat format = new CsvFormat();

    private static final Person firstPerson = new Person(1111111111111111111L, "firstName1", "lastName1", 11, "city1");

    private static final Person secondPerson = new Person(2222222222222222222L, "firstName2", "lastName2", 22, "city2");

    private static final ArrayList<Person> emptyList = new ArrayList<>(0);

    private static final ArrayList<Person> onePersonList = new ArrayList<>(1);

    private static final ArrayList<Person> people = new ArrayList<>(0);

    private static final String head = "id,firstName,lastName,age,city\n";

    @BeforeClass
    public static void setUp(){
        people.add(firstPerson);
        people.add(secondPerson);
        onePersonList.add(firstPerson);
    }

    @Test
    public void toFormatTwo(){
        String expected = head.concat("1111111111111111111, firstName1, lastName1, 11, city1\n" +
                "2222222222222222222, firstName2, lastName2, 22, city2\n");
        Assert.assertEquals(expected, format.toFormat(people));
    }

    @Test
    public void fromFormatTwo(){
        String serialized = head.concat("1111111111111111111, firstName1, lastName1, 11, city1\n" +
                "2222222222222222222, firstName2, lastName2, 22, city2\n");
        Assert.assertEquals(people, format.fromFormat(serialized));
    }

    @Test
    public void toFormatOne(){
        String expected = head.concat("1111111111111111111, firstName1, lastName1, 11, city1\n");
        Assert.assertEquals(expected, format.toFormat(onePersonList));
    }

    @Test
    public void fromFormatOne(){
        String serialized = head.concat("1111111111111111111, firstName1, lastName1, 11, city1\n");
        Assert.assertEquals(onePersonList, format.fromFormat(serialized));
    }

    @Test
    public void toFormatEmpty(){
        Assert.assertEquals(head, format.toFormat(emptyList));
    }

    @Test
    public void fromFormatEmpty(){
        Assert.assertEquals(emptyList, format.fromFormat(head));
    }

    @Test(expected = WrongFormatException.class)
    public void toFormatNull(){
        ArrayList<Person> people = null;
        format.toFormat(people); }

    @Test(expected = WrongFormatException.class)
    public void fromFormatNull(){
        format.fromFormat(null); }
}