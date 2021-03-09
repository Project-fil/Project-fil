package org.bitbucket.test.dao;

import org.bitbucket.app.config.FDaoPerson;
import org.bitbucket.app.entity.Person;
import org.bitbucket.app.repository.ICrud;
import org.bitbucket.app.utils.FileUtils;
import org.bitbucket.app.utils.exceptions.DifferentArraySizesException;
import org.bitbucket.app.utils.exceptions.NoSuchIdException;
import org.bitbucket.app.utils.exceptions.NullArgumentException;
import org.bitbucket.app.utils.exceptions.WrongPathException;
import org.junit.*;

import java.io.File;
import java.util.ArrayList;

public class DaoPersonXmlTest {

    private static ICrud daoXml;

    private static final Person firstPerson = new Person(1111111111111111111L, "firstName1", "lastName1", 11, "city1");

    private static final Person secondPerson = new Person(2222222222222222222L, "firstName2", "lastName2", 22, "city2");

    private static final Person thirdPerson = new Person(3333333333333333333L,  "firstName3", "lastName3", 33, "city3");

    private static final Person updatedPerson = new Person(1111111111111111111L, "updatedFirstName1", "lastName1", 11, "city1");

    private static final Person wrongUpdatedPerson = new Person(4444444444444444444L, "updatedFirstName1", "lastName1", 11, "city1");

    private static final ArrayList<Person> people = new ArrayList<>();

    private static final ArrayList<Person> updatedPeople = new ArrayList<>();

    private static final ArrayList<Person> wrongUpdatedPeople = new ArrayList<>();

    private static final File file = new File("test.xml");

    private static final File notExistingFile = new File("notExisting.xml");

    private static ArrayList<Person> exp = new ArrayList<>();

    private static ArrayList<Person> act = new ArrayList<>();

    @BeforeClass
    public static void setUp(){
        people.add(firstPerson);
        people.add(secondPerson);
        updatedPeople.add(updatedPerson);
        updatedPeople.add(secondPerson);
        wrongUpdatedPeople.add(thirdPerson);
        wrongUpdatedPeople.add(firstPerson);
    }

    @Before
    public void before(){
        FileUtils.createFile(file);
        daoXml = FDaoPerson.chooseDao(file);
    }

    @After
    public void after(){
        FileUtils.deleteFile(file);
        exp.clear();
        act.clear();
    }

    @Test
    public void readAllEmpty(){
        act = daoXml.readAll();
        Assert.assertEquals(exp, act);
    }

    @Test
    public void createOne(){
        exp.add(firstPerson);
        exp.add(secondPerson);
        exp.add(thirdPerson);
        daoXml.createAll(people);
        daoXml.create(thirdPerson);
        act = daoXml.getPeople();
    }

    @Test
    public void createAll(){
        act = daoXml.createAll(people);
        exp.addAll(people);
        Assert.assertEquals(exp, act);
    }

    @Test(expected = NullArgumentException.class)
    public void createNull(){
        daoXml.create(null);
    }

    @Test(expected = NullArgumentException.class)
    public void createAllNull(){
        daoXml.createAll(null);
    }

    @Test
    public void readOne(){
        daoXml.createAll(people);
        exp.add(secondPerson);
        act.add(daoXml.read(2222222222222222222L));
        Assert.assertEquals(exp, act);
    }

    @Test(expected = NoSuchIdException.class)
    public void readNotExisting(){
        daoXml.read(5555555555555555555L);
    }

    @Test
    public void updateOne(){
        daoXml.createAll(people);
        exp.add(updatedPerson);
        exp.add(secondPerson);
        daoXml.update(updatedPerson);
        act = daoXml.getPeople();
        Assert.assertEquals(exp, act);
    }

    @Test
    public void updateTwo(){
        daoXml.createAll(people);
        exp.add(updatedPerson);
        exp.add(secondPerson);
        act.addAll(daoXml.updateAll(updatedPeople));
        Assert.assertEquals(exp, act);
    }

    @Test(expected = DifferentArraySizesException.class)
    public void updateAllDifferentSizes(){
        daoXml.createAll(people);
        daoXml.create(thirdPerson);
        daoXml.updateAll(updatedPeople);
    }

    @Test(expected = NoSuchIdException.class)
    public void updateAllNoSuchId(){
        daoXml.createAll(people);
        daoXml.updateAll(wrongUpdatedPeople);
    }

    @Test(expected = NoSuchIdException.class)
    public void updateNoSuchId(){
        daoXml.update(wrongUpdatedPerson);
    }

    @Test(expected = NullArgumentException.class)
    public void updateNull(){
        daoXml.update(null);
    }

    @Test(expected = NullArgumentException.class)
    public void updateAllNull(){
        daoXml.updateAll(null);
    }

    @Test
    public void deleteAllRemaining(){
        daoXml.createAll(people);
        daoXml.deleteAll();
        act = daoXml.getPeople();
        Assert.assertEquals(exp, act);
    }

    @Test
    public void deleteAllDeleted(){
        daoXml.createAll(people);
        exp.addAll(people);
        act = daoXml.deleteAll();
        Assert.assertEquals(exp, act);
    }

    @Test
    public void deleteRemaining(){
        daoXml.createAll(people);
        exp.add(firstPerson);
        daoXml.delete(2222222222222222222L);
        act = daoXml.getPeople();
        Assert.assertEquals(exp, act);
    }

    @Test(expected = NoSuchIdException.class)
    public void deleteNotExisting(){
        daoXml.delete(5555555555555555555L);
    }

    @Test
    public void getPeople(){
        daoXml.createAll(people);
        exp.add(firstPerson);
        exp.add(secondPerson);
        act = daoXml.getPeople();
        Assert.assertEquals(exp, act);
    }

    @Test(expected = WrongPathException.class)
    public void wrongFile(){
        daoXml = FDaoPerson.chooseDao(notExistingFile);
    }

    @Test
    public void getFile(){
        Assert.assertEquals(file, daoXml.getFile());
    }

}
