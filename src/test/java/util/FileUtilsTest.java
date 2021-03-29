package util;


import org.bitbucket.app.entity.Person;
import org.bitbucket.app.fomats.impl.CsvFormat;
import org.bitbucket.app.utils.FileUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.util.ArrayList;

import static org.bitbucket.app.utils.FileUtils.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FileUtilsTest {

    File file1;

    Person firstPerson = new Person(1111111111111111111L, "firstName1", "lastName1", 11, "city1");

    Person secondPerson = new Person(2222222222222222222L, "firstName2", "lastName2", 22, "city2");

    String head = "id,firstName,lastName,age,city\n";

    ArrayList<Person> people = new ArrayList<>();

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Before
    public void setUp() {
        try {
            file1 = folder.newFile("test.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void fileCreateExist() {
        createFile(file1);
        assertTrue(file1.exists());
    }

    @Test
    public void createFileIsFile() {
        createFile(file1);
        assertTrue(file1.isFile());
    }

    @Test
    public void createFileIsAbs() {
        createFile(file1);
        assertTrue(file1.isAbsolute());
    }

    @Test
    public void createFileIs() {
        createFile(file1);
        assertTrue(file1.getAbsolutePath().endsWith("test.csv"));
    }

    @Test
    public void writeFileLength() throws IOException {
        BufferedWriter bw1 = new BufferedWriter(new FileWriter(file1));
        bw1.write("content for file1");
        bw1.close();
        assertEquals(file1.length(), 17L);
    }

    @Test
    public void writeFile() throws IOException {
        BufferedWriter bw1 = new BufferedWriter(new FileWriter(file1));
        bw1.write("content for file1");
        bw1.close();
        boolean act = file1 != null;
        assertTrue(act);
    }

    @Test
    public void writeFilePerson() {
        CsvFormat format = new CsvFormat();
        String prototype = head.concat("1111111111111111111, firstName1, lastName1, 11, city1\n");
        people.add(firstPerson);
        assertEquals(prototype, format.toFormat(people));

    }

    @Test
    public void writeFileTwoPerson() {
        CsvFormat format = new CsvFormat();
        String prototype = head.concat("1111111111111111111, firstName1, lastName1, 11, city1\n" +
                "2222222222222222222, firstName2, lastName2, 22, city2\n");
        people.add(firstPerson);
        people.add(secondPerson);
        assertEquals(prototype, format.toFormat(people));
    }

    @Test
    public void readFileEx() {
        readFile(file1);
        assertTrue(file1.exists());
    }

    @Test
    public void readFileNull() throws IOException {
        BufferedWriter bw1 = new BufferedWriter(new FileWriter(file1));
        bw1.write("content for file1");
        bw1.close();
        BufferedReader br = new BufferedReader(new FileReader(file1));
        boolean line = br.readLine() != null;
        assertTrue(line);
    }

    @Test
    public void readFileContent() throws IOException {
        BufferedWriter bw1 = new BufferedWriter(new FileWriter(file1));
        bw1.write("content for file1");
        bw1.close();
        readFile(file1);
        boolean act = !String.valueOf(file1).equals("");
        assertTrue(act);
    }

    @Test
    public void readFileOne() throws IOException {
        BufferedWriter bw1 = new BufferedWriter(new FileWriter(file1));
        bw1.write("1111111111111111111, firstName1, lastName1, 11, city1\n");
        bw1.close();
        String exp = readFile(file1);
        String act = "1111111111111111111, firstName1, lastName1, 11, city1";
        assertEquals(exp, act);
    }

    @Test
    public void deleteFile() throws IOException {
        BufferedWriter bw1 = new BufferedWriter(new FileWriter(file1));
        bw1.write("1111111111111111111, firstName1, lastName1, 11, city1\n");
        bw1.close();
        boolean act = FileUtils.deleteFile(file1);
        assertTrue(act);
    }

    @Test
    public void deleteFileAll(){
        FileUtils.deleteFile(file1);
        Assert.assertFalse(file1.exists());
    }

    @Test
    public void readBinFile(){
        FileUtils.readBinFile(file1);
        assertTrue(file1.exists());
    }

    @Test
    public void writeToBinFile(){
        byte[] data = new byte[]{};
        FileUtils.writeToBinFile(file1, data);
        assertTrue(file1.exists());
    }

}