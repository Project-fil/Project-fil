package org.bitbucket.app.entity;

import org.bitbucket.app.models.PersonColumn;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.Comparator;
import java.util.Objects;

public class Person implements Serializable, Cloneable{

    private static final long serialVersionUID = 3209363645813174245L;

    @PersonColumn(name = "ID")
    private long id;

    @PersonColumn(name = "First name")
    private String firstName;

    @PersonColumn(name = "Last name")
    private String lastName;

    @PersonColumn(name = "Age")
    private int age;

    @PersonColumn(name = "City")
    private String city;

    public Person(long id, String firstName, String lastName, int age, String city) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.city = city;
    }

    public Person(String firstName, String lastName, int age, String city) {
        SecureRandom secureRandom = new SecureRandom();
        this.id = Math.abs(secureRandom.nextLong());
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.city = city;
    }

    public Person(Person another){
        this.id = another.id;
        this.firstName = another.firstName;
        this.lastName = another.lastName;
        this.age = another.age;
        this.city = another.city;
    }

    public Person() {
        this.id = 0;
        this.firstName = "firstName";
        this.lastName = "lastName";
        this.age = 0;
        this.city = "city";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id && age == person.age && Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName) && Objects.equals(city, person.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, age, city);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", city='" + city + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getCity() {
        return city;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setCity(String city) {
        this.city = city;
    }

}
