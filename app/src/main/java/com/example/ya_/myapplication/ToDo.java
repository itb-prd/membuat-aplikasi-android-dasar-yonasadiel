package com.example.ya_.myapplication;

import android.widget.SimpleAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by YA_ on 11/11/2016.
 */

public class ToDo {
    String name;
    Date dueDate;
    String dueDateString;

    public ToDo(String name, String dueDateString) {
        this.name = name;
        this.dueDate = getDate(dueDateString);
        this.dueDateString = getString(dueDate);
    }

    public Date getDate(String ins) {
        Date oud = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("EEEE, dd/MM/yyyy HH:mm", Locale.ENGLISH);
        try {
            oud = formatter.parse(ins);
        } catch (ParseException e){
            e.printStackTrace();
        }
        return oud;
    }

    public String getString(Date ind) {
        String ous = "";

        SimpleDateFormat formatter = new SimpleDateFormat("EEEE, dd/MM/yyyy HH:mm", Locale.ENGLISH);
        try {
            ous = formatter.format(ind);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ous;
    }

}
