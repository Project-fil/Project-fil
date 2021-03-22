package formats;

import org.bitbucket.app.entity.Person;
import org.bitbucket.app.fomats.impl.XmlFormat;
import org.bitbucket.app.exceptions.WrongFormatException;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class XmlFormatTest {

    final Person person = new Person(1231234213414244531L,"Denys","Fe",21,"Kyy");
    final XmlFormat xmlFormat = new XmlFormat();

    final String onePersonXml  = "<person>\n" +
            "\t\t<id>1231234213414244531</id>\n" +
            "\t\t<firstName>Denys</firstName>\n" +
            "\t\t<lastName>Fe</lastName>\n" +
            "\t\t<age>21</age>\n" +
            "\t\t<city>Kyy</city>\n" +
            "\t</person>";

    final String twoPersonXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<people>\n" +
            "\t<person>\n" +
            "\t\t<id>1231234213414244531</id>\n" +
            "\t\t<firstName>Denys</firstName>\n" +
            "\t\t<lastName>Fe</lastName>\n" +
            "\t\t<age>21</age>\n" +
            "\t\t<city>Kyy</city>\n" +
            "\t</person>\n" +
            "\t<person>\n" +
            "\t\t<id>1231234213414244531</id>\n" +
            "\t\t<firstName>Denys</firstName>\n" +
            "\t\t<lastName>Fe</lastName>\n" +
            "\t\t<age>21</age>\n" +
            "\t\t<city>Kyy</city>\n" +
            "\t</person>\n" +
            "</people>";

    @Test
    public void fromFormatOnePerson() {

        ArrayList<Person> personList = new ArrayList<>();
        personList.add(person);
        Assert.assertArrayEquals(personList.toArray(),xmlFormat.fromFormat(onePersonXml).toArray());

    }

    @Test
    public void fromFormatTwoPerson(){

        ArrayList<Person> personList = new ArrayList<>();
        personList.add(person);
        personList.add(person);
        Assert.assertArrayEquals(personList.toArray(),xmlFormat.fromFormat(twoPersonXml).toArray());

    }

    @Test
    public void toFormatOnePerson() {
        Assert.assertEquals(onePersonXml,xmlFormat.toFormat(person).trim());
    }

    @Test
    public void toFormatPeople() {

        ArrayList<Person> personList = new ArrayList<>();
        personList.add(person);
        personList.add(person);
        Assert.assertEquals(twoPersonXml,xmlFormat.toFormat(personList).trim());

    }

    @Test(expected = WrongFormatException.class)
    public void toFormatNullPersonException(){
        Person a = null;
        xmlFormat.toFormat(a);
    }

    @Test(expected = WrongFormatException.class)
    public void toFormatNullListException(){
        ArrayList<Person> a = null;
        xmlFormat.toFormat(a);
    }
}