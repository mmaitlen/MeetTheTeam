package com.mgm.meettheteam.model;

import com.google.gson.Gson;

import com.mgm.meettheteam.App;
import com.mgm.meettheteam.R;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Default data model for list of people
 *
 * Created by michaelmaitlen on 2/12/18.
 */
public class DefaultDataModel implements DataModel {

    private HashMap dataMap;

    private void loadData() throws UnsupportedEncodingException {

        InputStream in = App.instance().getApplicationContext().getResources().openRawResource(R.raw.team);
        Reader reader = new InputStreamReader(in, "UTF-8");
        Gson gson = new Gson();
        Person[] people = gson.fromJson(reader, Person[].class);

        dataMap = new HashMap(people.length);

        for(Person person: people) {
            dataMap.put(person.id, person);
        }
    }

    private Map<Integer, Person> getDataMap() throws UnsupportedEncodingException {
        if (dataMap == null) {
            loadData();
        }
        return dataMap;
    }

    protected Person[] fetchAllPeople() throws UnsupportedEncodingException {
        Person[] list = getDataMap().values().toArray(new Person[dataMap.size()]);
        Arrays.sort(list, new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                String n1 = p1.lastName + p1.firstName;
                String n2 = p2.lastName + p2.firstName;
                return n1.compareTo(n2);
            }
        });
        return list;
    }

    protected Person fetchPerson(int id) throws UnsupportedEncodingException {
        return getDataMap().get(id);
    }

    @Override
    public void fetchAllPeople(Subscriber<Person[]> subscriber) {
        execute(new Callable<Person[]>() {
            public Person[] call() throws Exception {
                return fetchAllPeople();
            }
        }, subscriber);
    }

    @Override
    public void fetchPerson(final int id, Subscriber<Person> subscriber) {
        execute(new Callable<Person>() {
            public Person call() throws Exception {
                return fetchPerson(id);
            }
        }, subscriber);
    }


    private void execute(Callable call, Subscriber sub) {
        Observable.
                fromCallable(call).
                subscribeOn(Schedulers.computation()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(sub);
    }
}
