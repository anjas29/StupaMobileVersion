package com.exercise.stupa.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.exercise.stupa.R;
import com.exercise.stupa.object.Present;

import java.util.ArrayList;

/**
 * Created by anjas on 30/04/17.
 */

public class ListPresentAdapter extends RecyclerView.Adapter<ListPresentViewHolder> {
    ArrayList<Present> items;

    @Override
    public ListPresentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_present, null);
        ListPresentViewHolder view = new ListPresentViewHolder(layoutView);
        return view;
    }

    @Override
    public void onBindViewHolder(ListPresentViewHolder holder, int position) {
        holder.imageView.setImageResource(R.drawable.circle_green);
        holder.descriptionView.setText(items.get(position).getPresent());
        holder.presentView.setText(items.get(position).getPresent());
        holder.courseView.setText(items.get(position).getCourse());
        holder.dateView.setText(items.get(position).getCreated_at());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
