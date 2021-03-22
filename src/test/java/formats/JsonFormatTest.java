package formats;

import org.junit.Assert;
import org.junit.Test;
import org.bitbucket.app.fomats.impl.JsonFormat;
import org.bitbucket.app.entity.Person;
import org.bitbucket.app.exceptions.WrongFormatException;

import java.util.ArrayList;
import java.util.List;

public class JsonFormatTest {

    private final JsonFormat jf = new JsonFormat();
    private final String onePerson = "{\"id\":2001203,\"firstName\":\"Denys\",\"lastName\":\"Fedorovych\",\"age\":18,\"city\":\"Kyiv\"}";
    private final String twoPer = "[\n{\"id\":2001203,\"firstName\":\"Denys\",\"lastName\":\"Fedorovych\",\"age\":18,\"city\":\"Kyiv\"}, \"{\"id\":2001203,\"firstName\":\"Denys\",\"lastName\":\"Fedororvych\",\"age\":18,\"city\":\"Kyiv\"}\n]";
    private final String personWithHeight = "{\"id\":2001203,\"firstName\":\"Denys\",\"lastName\":\"Fedorovych\",\"height\":18,\"city\":\"Kyiv\"}";
    private final long id_expected = 2001203;

    @Test
    public void fromFormatTest() {

        List<Person> list = jf.fromFormat(onePerson);
        Assert.assertEquals(id_expected, list.get(0).getId());
        list = jf.fromFormat(twoPer);
        Assert.assertEquals(id_expected, list.get(0).getId());
        Assert.assertEquals(id_expected, list.get(1).getId());

    }

    @Test
    public void fromFormatToFormatTwoPeopleEquality() {

        List<Person> list = new ArrayList<>();
        list.add(new Person(10000, "Denys", "Fedorovych", 18, "Kyiv"));
        list.add(jf.fromFormat(onePerson).get(0));
        Assert.assertArrayEquals(list.toArray(), jf.fromFormat(jf.toFormat(list)).toArray());

    }

    @Test
    public void fromFormatToFormatOnePersonEquality() {

        List<Person> list = new ArrayList<>();
        list.add(new Person(10000, "Denys", "Fedorovych", 18, "Kyiv"));
        Assert.assertArrayEquals(list.toArray(), jf.fromFormat(jf.toFormat(list)).toArray());

    }

    @Test
    public void fromFormatBlankTest() {

        List<Person> list = new ArrayList<>();
        Assert.assertArrayEquals(list.toArray(), jf.fromFormat("{   }").toArray());
        Assert.assertArrayEquals(list.toArray(), jf.fromFormat("{\n}").toArray());
        Assert.assertArrayEquals(list.toArray(), jf.fromFormat("{   \n\t\n   }").toArray());

    }

    @Test(expected = WrongFormatException.class)
    public void fromFormatPointsExceptionTest() {
        jf.fromFormat("{\"id\"=2001203,\"firstName\":\"Denys\",\"lastName\":\"Fedorovych\",\"age\":18,\"city\":\"Kyiv\"}");
    }

    @Test(expected = WrongFormatException.class)
    public void fromFormatWrongArgumentExceptionTest() {
        jf.fromFormat("{\"id\"=2001203,\"firstName\":\"Denys\",\"surname\":\"Fedorovych\",\"age\":18,\"city\":\"Kyiv\"}");
    }

    @Test
    public void toFormatEmptyListTest() {
        Assert.assertEquals("{}", jf.toFormat(new ArrayList<>()));
    }

    @Test(expected = WrongFormatException.class)
    public void toFormatNullArgument() {

        ArrayList<Person> list = null;
        jf.toFormat(list);

    }

    @Test(expected = WrongFormatException.class)
    public void toFormatNullException() {

        Person a = null;
        jf.toFormat(a);

    }

    @Test(expected = WrongFormatException.class)
    public void wrongParameterException() {
        jf.fromFormat(personWithHeight);
    }


}