package com.exercise.stupa.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.exercise.stupa.R;

/**
 * Created by anjas on 30/04/17.
 */

public class ListPresentViewHolder extends RecyclerView.ViewHolder{
    ImageView imageView;
    TextView courseView, presentView, dateView, descriptionView;

    public ListPresentViewHolder(View itemView) {
        super(itemView);

        imageView = (ImageView)itemView.findViewById(R.id.imageView);
        dateView = (TextView)itemView.findViewById(R.id.dateView);
        courseView = (TextView)itemView.findViewById(R.id.courseView);
        presentView = (TextView)itemView.findViewById(R.id.presentView);
        descriptionView = (TextView)itemView.findViewById(R.id.descriptionView);
    }


}
