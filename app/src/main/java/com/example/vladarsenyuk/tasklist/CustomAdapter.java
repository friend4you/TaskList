package com.example.vladarsenyuk.tasklist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by VladArsnyuk on 26.10.2016.
 */


class CustomAdapter extends ArrayAdapter<Task>{

    public CustomAdapter(Context context, ArrayList<Task> tasks) {
        super(context, R.layout.list_item, tasks);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater customInflater = LayoutInflater.from(getContext());
        View taskView = customInflater.inflate(R.layout.list_item, parent, false);

        Task t = getItem(position);
        TextView titleTextView = (TextView) taskView.findViewById(R.id.taskName);
        TextView infoTextView = (TextView) taskView.findViewById(R.id.taskInfo);
        TextView dateTextView = (TextView) taskView.findViewById(R.id.createTime);
        RatingBar priorityRatingBar = (RatingBar) taskView.findViewById(R.id.priority);
        ImageView imageView = (ImageView) taskView.findViewById(R.id.imageView);

        titleTextView.setText(t.getName());
        infoTextView.setText(t.getInfo());
        dateTextView.setText(t.getDateS());
        priorityRatingBar.setRating(t.getPriority());
        imageView.setImageResource(t.getImg());

        return taskView;
    }

}
