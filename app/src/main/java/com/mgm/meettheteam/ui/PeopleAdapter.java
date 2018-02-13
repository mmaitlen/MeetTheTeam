package com.mgm.meettheteam.ui;

import com.mgm.meettheteam.R;
import com.mgm.meettheteam.databinding.ListrowPersonBinding;
import com.mgm.meettheteam.model.Person;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * List adapter for list of people.
 *
 * Created by michaelmaitlen on 2/12/18.
 */
public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.ViewHolder> {

    interface ItemClickHandler {
        void personClicked(Person person);
    }

    private Person[] list;
    private ItemClickHandler clickHandler;

    PeopleAdapter(Person[] list, ItemClickHandler clickHandler) {
        this.list = list;
        this.clickHandler = clickHandler;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vw = LayoutInflater.from(parent.getContext()).inflate(R.layout.listrow_person, parent, false);
        return new ViewHolder(vw);
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        Person person = list[position];
        vh.populate(person, clickHandler);
    }

    @Override
    public int getItemCount() {
        return list.length;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private ListrowPersonBinding binding;

        ViewHolder(View vw) {
            super(vw);
            binding = DataBindingUtil.bind(vw);
        }

        private void populate(final Person person, final ItemClickHandler clickHandler) {
            binding.setPerson(person);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickHandler.personClicked(person);
                }
            });
        }
    }
}