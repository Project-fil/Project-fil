package services;

import org.bitbucket.app.config.FPersonService;
import org.bitbucket.app.entity.Person;
import org.bitbucket.app.services.IPersonService;
import org.bitbucket.app.utils.FileUtils;
import org.bitbucket.app.utils.exceptions.DifferentArraySizesException;
import org.bitbucket.app.utils.exceptions.NoSuchIdException;
import org.bitbucket.app.utils.exceptions.NullArgumentException;
import org.bitbucket.app.utils.exceptions.WrongPathException;
import org.junit.*;

import java.io.File;
import java.util.ArrayList;

public class DaoPersonJsonTest {

    private static IPersonService daoJson;

    private static final Person firstPerson = new Person(1111111111111111111L, "firstName1", "lastName1", 11, "city1");

    private static final Person secondPerson = new Person(2222222222222222222L, "firstName2", "lastName2", 22, "city2");

    private static final Person thirdPerson = new Person(3333333333333333333L,  "firstName3", "lastName3", 33, "city3");

    private static final Person updatedPerson = new Person(1111111111111111111L, "updatedFirstName1", "lastName1", 11, "city1");

    private static final Person wrongUpdatedPerson = new Person(4444444444444444444L, "updatedFirstName1", "lastName1", 11, "city1");

    private static final ArrayList<Person> people = new ArrayList<>();

    private static final ArrayList<Person> updatedPeople = new ArrayList<>();

    private static final ArrayList<Person> wrongUpdatedPeople = new ArrayList<>();

    private static final File file = new File("test.json");

    private static final File notExistingFile = new File("notExisting.json");

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
        daoJson = FPersonService.chooseService(file);
    }

    @After
    public void after(){
        FileUtils.deleteFile(file);
        exp.clear();
        act.clear();
    }

    @Test
    public void readAllEmpty(){
        act = (ArrayList<Person>) daoJson.readAll();
        Assert.assertEquals(exp, act);
    }

    @Test
    public void createOne(){
        exp.add(firstPerson);
        exp.add(secondPerson);
        exp.add(thirdPerson);
        daoJson.createAll(people);
        daoJson.create(thirdPerson);
        act = (ArrayList<Person>) daoJson.getPeople();
    }

    @Test
    public void createAll(){
        act = (ArrayList<Person>) daoJson.createAll(people);
        exp.addAll(people);
        Assert.assertEquals(exp, act);
    }

    @Test(expected = NullArgumentException.class)
    public void createNull(){
        daoJson.create(null);
    }

    @Test(expected = NullArgumentException.class)
    public void createAllNull(){
        daoJson.createAll(null);
    }

    @Test
    public void readOne(){
        daoJson.createAll(people);
        exp.add(secondPerson);
        act.add(daoJson.read(2222222222222222222L));
        Assert.assertEquals(exp, act);
    }

    @Test(expected = NoSuchIdException.class)
    public void readNotExisting(){
        daoJson.read(5555555555555555555L);
    }

    @Test
    public void updateOne(){
        daoJson.createAll(people);
        exp.add(updatedPerson);
        exp.add(secondPerson);
        daoJson.update(updatedPerson);
        act = (ArrayList<Person>) daoJson.getPeople();
        Assert.assertEquals(exp, act);
    }

    @Test
    public void updateTwo(){
        daoJson.createAll(people);
        exp.add(updatedPerson);
        exp.add(secondPerson);
        act.addAll(daoJson.updateAll(updatedPeople));
        Assert.assertEquals(exp, act);
    }

    @Test(expected = DifferentArraySizesException.class)
    public void updateAllDifferentSizes(){
        daoJson.createAll(people);
        daoJson.create(thirdPerson);
        daoJson.updateAll(updatedPeople);
    }

    @Test(expected = NoSuchIdException.class)
    public void updateAllNoSuchId(){
        daoJson.createAll(people);
        daoJson.updateAll(wrongUpdatedPeople);
    }

    @Test(expected = NoSuchIdException.class)
    public void updateNoSuchId(){
        daoJson.update(wrongUpdatedPerson);
    }

    @Test(expected = NullArgumentException.class)
    public void updateNull(){
        daoJson.update(null);
    }

    @Test(expected = NullArgumentException.class)
    public void updateAllNull(){
        daoJson.updateAll(null);
    }

    @Test
    public void deleteAllRemaining(){
        daoJson.createAll(people);
        daoJson.deleteAll();
        act = (ArrayList<Person>) daoJson.getPeople();
        Assert.assertEquals(exp, act);
    }

    @Test
    public void deleteAllDeleted(){
        daoJson.createAll(people);
        exp.addAll(people);
        act = (ArrayList<Person>) daoJson.deleteAll();
        Assert.assertEquals(exp, act);
    }

    @Test
    public void deleteRemaining(){
        daoJson.createAll(people);
        exp.add(firstPerson);
        daoJson.delete(2222222222222222222L);
        act = (ArrayList<Person>) daoJson.getPeople();
        Assert.assertEquals(exp, act);
    }

    @Test(expected = NoSuchIdException.class)
    public void deleteNotExisting(){
        daoJson.delete(5555555555555555555L);
    }

    @Test
    public void getPeople(){
        daoJson.createAll(people);
        exp.add(firstPerson);
        exp.add(secondPerson);
        act = (ArrayList<Person>) daoJson.getPeople();
        Assert.assertEquals(exp, act);
    }

    @Test(expected = WrongPathException.class)
    public void wrongFile(){
        daoJson = FPersonService.chooseService(notExistingFile);
    }

    @Test
    public void getFile(){
        Assert.assertEquals(file, daoJson.getFile());
    }

}
