package com.mgm.meettheteam.ui;

import com.mgm.meettheteam.App;
import com.mgm.meettheteam.databinding.FragmentPersonDetailBinding;
import com.mgm.meettheteam.model.Person;
import com.mgm.meettheteam.util.AbstractSubscriber;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Fragment responsible for displaying single person
 *
 * Created by michaelmaitlen on 2/12/18.
 */
public class PersonDetailFragment extends Fragment {

    private static final String ARG_PERSONAL_ID = "personal_id";

    private int personId;
    private FragmentPersonDetailBinding binding;

    public static PersonDetailFragment newInstance(int personId) {
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_PERSONAL_ID, personId);
        PersonDetailFragment fragment = new PersonDetailFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        personId = getArguments().getInt(ARG_PERSONAL_ID, 0);
    }

        @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        binding = FragmentPersonDetailBinding.inflate(inflater, container, false);

        App.instance().getDataModel().fetchPerson(personId,
                new AbstractSubscriber<Person>() {
                    @Override
                    public void onNext(Person person) {
                        binding.setPerson(person);
                    }
                });

        return binding.getRoot();
    }
}
