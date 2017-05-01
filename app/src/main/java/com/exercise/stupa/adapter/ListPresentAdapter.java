package com.exercise.stupa.adapter;

import android.content.Context;
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
    Context context;
    ArrayList<Present> items;

    public ListPresentAdapter(Context context, ArrayList<Present> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public ListPresentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_present, null);
        ListPresentViewHolder view = new ListPresentViewHolder(layoutView);
        return view;
    }

    @Override
    public void onBindViewHolder(ListPresentViewHolder holder, int position) {
        holder.presentView.setText(items.get(position).getPresent());
        holder.courseView.setText(items.get(position).getCourse());
        holder.dateView.setText(items.get(position).getCreated_at());
        String presence = items.get(position).getPresent();
        String description;
        switch (presence){
            case "Presence":
                holder.imageView.setImageResource(R.drawable.circle_green);
                description = "Student attend the course.";
                break;
            case "Sick":
                holder.imageView.setImageResource(R.drawable.circle_yellow);
                description = "Student is sick.";
                break;
            case "Permission":
                holder.imageView.setImageResource(R.drawable.circle_grey);
                description = "Student has another business.";
                break;
            case "Unknown":
                holder.imageView.setImageResource(R.drawable.circle_red);
                description = "Without any permission.";
                break;
            default:
                holder.imageView.setImageResource(R.drawable.circle_green);
                description = "Student attend the course.";
                break;
        }
        holder.descriptionView.setText(description);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
