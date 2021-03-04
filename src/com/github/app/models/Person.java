package com.github.app.models;

import java.io.Serializable;
import java.util.Objects;

public class Person implements Serializable, Cloneable {

    private static final long serialVersionUID = 3209363645813174245L;

    private long id;

    private String firstName;

    private String lastName;

    private int age;

    private String city;

    public Person(long id, String firstName, String lastName, int age, String city) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.city = city;
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
        return  "id: \t\t"    + id         + "\n" +
                "firstName: \t" + firstName  + "\n" +
                "lastName: \t"  + lastName   + "\n" +
                "age: \t\t"     + age        + "\n" +
                "city: \t\t"    + city;
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
