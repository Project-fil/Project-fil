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

public class DaoPersonCsvTest {

    private static ICrud daoCsv;

    private static final Person firstPerson = new Person(1111111111111111111L, "firstName1", "lastName1", 11, "city1");

    private static final Person secondPerson = new Person(2222222222222222222L, "firstName2", "lastName2", 22, "city2");

    private static final Person thirdPerson = new Person(3333333333333333333L,  "firstName3", "lastName3", 33, "city3");

    private static final Person updatedPerson = new Person(1111111111111111111L, "updatedFirstName1", "lastName1", 11, "city1");

    private static final Person wrongUpdatedPerson = new Person(4444444444444444444L, "updatedFirstName1", "lastName1", 11, "city1");

    private static final ArrayList<Person> people = new ArrayList<>();

    private static final ArrayList<Person> updatedPeople = new ArrayList<>();

    private static final ArrayList<Person> wrongUpdatedPeople = new ArrayList<>();

    private static final File file = new File("test.csv");

    private static final File notExistingFile = new File("notExisting.csv");

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
        daoCsv = FDaoPerson.chooseDao(file);
    }

    @After
    public void after(){
        FileUtils.deleteFile(file);
        exp.clear();
        act.clear();
    }

    @Test
    public void readAllEmpty(){
        act = daoCsv.readAll();
        Assert.assertEquals(exp, act);
    }

    @Test
    public void createOne(){
        exp.add(firstPerson);
        exp.add(secondPerson);
        exp.add(thirdPerson);
        daoCsv.createAll(people);
        daoCsv.create(thirdPerson);
        act = daoCsv.getPeople();
    }

    @Test
    public void createAll(){
        act = daoCsv.createAll(people);
        exp.addAll(people);
        Assert.assertEquals(exp, act);
    }

    @Test(expected = NullArgumentException.class)
    public void createNull(){
        daoCsv.create(null);
    }

    @Test(expected = NullArgumentException.class)
    public void createAllNull(){
        daoCsv.createAll(null);
    }

    @Test
    public void readOne(){
        daoCsv.createAll(people);
        exp.add(secondPerson);
        act.add(daoCsv.read(2222222222222222222L));
        Assert.assertEquals(exp, act);
    }

    @Test(expected = NoSuchIdException.class)
    public void readNotExisting(){
        daoCsv.read(5555555555555555555L);
    }

    @Test
    public void updateOne(){
        daoCsv.createAll(people);
        exp.add(updatedPerson);
        exp.add(secondPerson);
        daoCsv.update(updatedPerson);
        act = daoCsv.getPeople();
        Assert.assertEquals(exp, act);
    }

    @Test
    public void updateTwo(){
        daoCsv.createAll(people);
        exp.add(updatedPerson);
        exp.add(secondPerson);
        act.addAll(daoCsv.updateAll(updatedPeople));
        Assert.assertEquals(exp, act);
    }

    @Test(expected = DifferentArraySizesException.class)
    public void updateAllDifferentSizes(){
        daoCsv.createAll(people);
        daoCsv.create(thirdPerson);
        daoCsv.updateAll(updatedPeople);
    }

    @Test(expected = NoSuchIdException.class)
    public void updateAllNoSuchId(){
        daoCsv.createAll(people);
        daoCsv.updateAll(wrongUpdatedPeople);
    }

    @Test(expected = NoSuchIdException.class)
    public void updateNoSuchId(){
        daoCsv.update(wrongUpdatedPerson);
    }

    @Test(expected = NullArgumentException.class)
    public void updateNull(){
        daoCsv.update(null);
    }

    @Test(expected = NullArgumentException.class)
    public void updateAllNull(){
        daoCsv.updateAll(null);
    }

    @Test
    public void deleteAllRemaining(){
        daoCsv.createAll(people);
        daoCsv.deleteAll();
        act = daoCsv.getPeople();
        Assert.assertEquals(exp, act);
    }

    @Test
    public void deleteAllDeleted(){
        daoCsv.createAll(people);
        exp.addAll(people);
        act = daoCsv.deleteAll();
        Assert.assertEquals(exp, act);
    }

    @Test
    public void deleteRemaining(){
        daoCsv.createAll(people);
        exp.add(firstPerson);
        daoCsv.delete(2222222222222222222L);
        act = daoCsv.getPeople();
        Assert.assertEquals(exp, act);
    }

    @Test(expected = NoSuchIdException.class)
    public void deleteNotExisting(){
        daoCsv.delete(5555555555555555555L);
    }

    @Test
    public void getPeople(){
        daoCsv.createAll(people);
        exp.add(firstPerson);
        exp.add(secondPerson);
        act = daoCsv.getPeople();
        Assert.assertEquals(exp, act);
    }

    @Test(expected = WrongPathException.class)
    public void wrongFile(){
        daoCsv = FDaoPerson.chooseDao(notExistingFile);
    }

    @Test
    public void getFile(){
        Assert.assertEquals(file, daoCsv.getFile());
    }
    
}

