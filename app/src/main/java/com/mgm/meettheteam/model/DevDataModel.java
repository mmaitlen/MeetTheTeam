package com.mgm.meettheteam.model;

/**
 * Simple data model implementation used for quicker development.
 *
 * Created by michaelmaitlen on 2/12/18.
 */
public class DevDataModel extends DefaultDataModel {

    private Person[] people;
    private Person p1;
    private Person p2;

    public DevDataModel() {

        p1 = new Person("Jimmy", "Hendrix");
        p1.id = 1;
        p1.avatar = "https://d1qb2nb5cznatu.cloudfront.net/users/114349-large?1462395805";
        p2 = new Person("Pink", "Floyd");
        p2.id = 2;
        p2.avatar = "https://d1qb2nb5cznatu.cloudfront.net/users/278491-large?1462222766";

        people = new Person[2];
        people[0] = p1;
        people[1] = p2;
    }

    @Override
    protected Person[] fetchAllPeople() {
        return people;
    }

    @Override
    protected Person fetchPerson(int id) {
        if (id == 1) {
            return p1;
        }
        return p2;
    }
}
