package com.example.ya_.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by YA_ on 11/11/2016.
 */

class taskAdapter extends ArrayAdapter<ToDo>{
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater theInflater = LayoutInflater.from(getContext());
        View theView = theInflater.inflate(R.layout.row_layout,parent,false);

        ToDo task = getItem(position);

        TextView theTextViewName = (TextView) theView.findViewById(R.id.task_name);
        TextView theTextViewDate = (TextView) theView.findViewById(R.id.task_date);

        theTextViewName.setText(task.name);
        theTextViewDate.setText(task.dueDateString);

        return theView;
    }

    public taskAdapter(Context context, ArrayList<ToDo> values) {
        super(context, R.layout.row_layout, values);
    }
}
