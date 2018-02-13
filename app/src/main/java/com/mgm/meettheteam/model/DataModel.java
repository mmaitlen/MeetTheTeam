package com.mgm.meettheteam.model;

import rx.Subscriber;

/**
 * Interface defining the contract for fetching data
 *
 * Created by michaelmaitlen on 2/12/18.
 */
public interface DataModel {

    void fetchAllPeople(Subscriber<Person[]> subscriber);
    void fetchPerson(int id, Subscriber<Person> subscriber);
}
