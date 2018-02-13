package com.mgm.meettheteam.model;

/**
 * Defines the Person model object.
 *
 * Created by michaelmaitlen on 2/12/18.
 */
public class Person {

    public int id;
    public String avatar;
    public String bio;
    public String firstName;
    public String lastName;
    public String title;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
