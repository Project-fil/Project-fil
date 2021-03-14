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

public class DaoPersonYmlTest {

    private static IPersonService daoYml;

    private static final Person firstPerson = new Person(1111111111111111111L, "firstName1", "lastName1", 11, "city1");

    private static final Person secondPerson = new Person(2222222222222222222L, "firstName2", "lastName2", 22, "city2");

    private static final Person thirdPerson = new Person(3333333333333333333L,  "firstName3", "lastName3", 33, "city3");

    private static final Person updatedPerson = new Person(1111111111111111111L, "updatedFirstName1", "lastName1", 11, "city1");

    private static final Person wrongUpdatedPerson = new Person(4444444444444444444L, "updatedFirstName1", "lastName1", 11, "city1");

    private static final ArrayList<Person> people = new ArrayList<>();

    private static final ArrayList<Person> updatedPeople = new ArrayList<>();

    private static final ArrayList<Person> wrongUpdatedPeople = new ArrayList<>();

    private static final File file = new File("test.yml");

    private static final File notExistingFile = new File("notExisting.yml");

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
        daoYml = FPersonService.chooseService(file);
    }

    @After
    public void after(){
        FileUtils.deleteFile(file);
        exp.clear();
        act.clear();
    }

    @Test
    public void readAllEmpty(){
        act = (ArrayList<Person>) daoYml.readAll();
        Assert.assertEquals(exp, act);
    }

    @Test
    public void createOne(){
        exp.add(firstPerson);
        exp.add(secondPerson);
        exp.add(thirdPerson);
        daoYml.createAll(people);
        daoYml.create(thirdPerson);
        act = (ArrayList<Person>) daoYml.getPeople();
    }

    @Test
    public void createAll(){
        act = (ArrayList<Person>) daoYml.createAll(people);
        exp.addAll(people);
        Assert.assertEquals(exp, act);
    }

    @Test(expected = NullArgumentException.class)
    public void createNull(){
        daoYml.create(null);
    }

    @Test(expected = NullArgumentException.class)
    public void createAllNull(){
        daoYml.createAll(null);
    }

    @Test
    public void readOne(){
        daoYml.createAll(people);
        exp.add(secondPerson);
        act.add(daoYml.read(2222222222222222222L));
        Assert.assertEquals(exp, act);
    }

    @Test(expected = NoSuchIdException.class)
    public void readNotExisting(){
        daoYml.read(5555555555555555555L);
    }

    @Test
    public void updateOne(){
        daoYml.createAll(people);
        exp.add(updatedPerson);
        exp.add(secondPerson);
        daoYml.update(updatedPerson);
        act = (ArrayList<Person>) daoYml.getPeople();
        Assert.assertEquals(exp, act);
    }

    @Test
    public void updateTwo(){
        daoYml.createAll(people);
        exp.add(updatedPerson);
        exp.add(secondPerson);
        act.addAll(daoYml.updateAll(updatedPeople));
        Assert.assertEquals(exp, act);
    }

    @Test(expected = DifferentArraySizesException.class)
    public void updateAllDifferentSizes(){
        daoYml.createAll(people);
        daoYml.create(thirdPerson);
        daoYml.updateAll(updatedPeople);
    }

    @Test(expected = NoSuchIdException.class)
    public void updateAllNoSuchId(){
        daoYml.createAll(people);
        daoYml.updateAll(wrongUpdatedPeople);
    }

    @Test(expected = NoSuchIdException.class)
    public void updateNoSuchId(){
        daoYml.update(wrongUpdatedPerson);
    }

    @Test(expected = NullArgumentException.class)
    public void updateNull(){
        daoYml.update(null);
    }

    @Test(expected = NullArgumentException.class)
    public void updateAllNull(){
        daoYml.updateAll(null);
    }

    @Test
    public void deleteAllRemaining(){
        daoYml.createAll(people);
        daoYml.deleteAll();
        act = (ArrayList<Person>) daoYml.getPeople();
        Assert.assertEquals(exp, act);
    }

    @Test
    public void deleteAllDeleted(){
        daoYml.createAll(people);
        exp.addAll(people);
        act = (ArrayList<Person>) daoYml.deleteAll();
        Assert.assertEquals(exp, act);
    }

    @Test
    public void deleteRemaining(){
        daoYml.createAll(people);
        exp.add(firstPerson);
        daoYml.delete(2222222222222222222L);
        act = (ArrayList<Person>) daoYml.getPeople();
        Assert.assertEquals(exp, act);
    }

    @Test(expected = NoSuchIdException.class)
    public void deleteNotExisting(){
        daoYml.delete(5555555555555555555L);
    }

    @Test
    public void getPeople(){
        daoYml.createAll(people);
        exp.add(firstPerson);
        exp.add(secondPerson);
        act = (ArrayList<Person>) daoYml.getPeople();
        Assert.assertEquals(exp, act);
    }

    @Test(expected = WrongPathException.class)
    public void wrongFile(){
        daoYml = FPersonService.chooseService(notExistingFile);
    }

    @Test
    public void getFile(){
        Assert.assertEquals(file, daoYml.getFile());
    }

}
