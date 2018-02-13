package com.mgm.meettheteam.ui;

import com.mgm.meettheteam.App;
import com.mgm.meettheteam.R;
import com.mgm.meettheteam.model.Person;
import com.mgm.meettheteam.util.AbstractSubscriber;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Fragment responsible for displaying list of people and reacting when user clicks on list row.
 *
 * Created by michaelmaitlen on 2/12/18.
 */
public class PersonListFragment extends Fragment {

    private static final String TAG = PersonListFragment.class.getSimpleName();

    private TextView txvwErrMsg;

    interface ListHandler {
        void handleItemClicked(Person person);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View vw = inflater.inflate(R.layout.fragment_list, container, false);

        txvwErrMsg = vw.findViewById(R.id.errmsg);

        final RecyclerView listvw = vw.findViewById(R.id.listvw);

        listvw.setHasFixedSize(true);
        listvw.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        listvw.setLayoutManager(new LinearLayoutManager(getActivity()));

        App.instance().getDataModel().fetchAllPeople(new AbstractSubscriber<Person[]>() {
            @Override
            public void onNext(Person[] people) {

                PeopleAdapter adapter = new PeopleAdapter(people, new PeopleAdapter.ItemClickHandler() {
                    @Override
                    public void personClicked(Person person) {
                        listHandler.handleItemClicked(person);
                    }
                });
                listvw.setAdapter(adapter);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "Error occurred!!", e);
                txvwErrMsg.setVisibility(View.VISIBLE);
            }
        });

        return vw;
    }

    private ListHandler listHandler;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listHandler = (ListHandler)context;
    }
}
