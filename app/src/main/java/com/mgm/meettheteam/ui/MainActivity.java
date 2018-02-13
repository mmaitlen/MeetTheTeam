package com.mgm.meettheteam.ui;

import com.mgm.meettheteam.R;
import com.mgm.meettheteam.model.Person;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * Main activity for application
 *
 * Created by michaelmaitlen on 2/12/18.
 */
public class MainActivity extends AppCompatActivity implements PersonListFragment.ListHandler {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addFragment(new PersonListFragment());
    }

    public void addFragment(Fragment fragment) {
        Fragment tmp = getSupportFragmentManager().findFragmentByTag(fragment.getClass().getSimpleName());
        if (tmp == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.root, fragment, fragment.getClass().getSimpleName())
                    .commit();
        }
    }

    public void replaceFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.root, fragment, fragment.getClass().getSimpleName())
                .addToBackStack(fragment.getClass().getSimpleName())
                .commit();
    }

    @Override
    public void handleItemClicked(Person person) {
        replaceFragment(PersonDetailFragment.newInstance(person.id));
    }
}
