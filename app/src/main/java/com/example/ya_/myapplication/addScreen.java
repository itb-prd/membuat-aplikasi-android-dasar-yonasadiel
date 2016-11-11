package com.example.ya_.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.EditText;
import android.app.DialogFragment;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by YA_ on 11/10/2016.
 */

public class addScreen extends AppCompatActivity{
    Calendar cal = Calendar.getInstance();
    Date newDate = new Date();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.add_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    public void onSendNewTask(View view) {

        EditText newTaskET = (EditText) findViewById(R.id.add_todo_list);

        String newTask = String.valueOf(newTaskET.getText());
        String ous = "";

        SimpleDateFormat formatter = new SimpleDateFormat("EEEE, dd/MM/yyyy HH:mm", Locale.ENGLISH);
        try {
            ous = formatter.format(newDate);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Intent goBack = new Intent();
        goBack.putExtra("New Task Name",newTask);
        goBack.putExtra("New Task Due Date",ous);
        setResult(RESULT_OK, goBack);

        finish();

    }

    public void getDate(View view) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getFragmentManager(), "timePicker");

        newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");

    }

    public void displayDate() {
        newDate = cal.getTime();
        TextView dateResult = (TextView) findViewById(R.id.datePicker);
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.ENGLISH);
        dateResult.setText(df.format(newDate));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            cal.set(Calendar.YEAR, data.getExtras().getInt("tahun"));
            cal.set(Calendar.MONTH, data.getExtras().getInt("bulan"));
            cal.set(Calendar.DAY_OF_MONTH, data.getExtras().getInt("hari"));
            displayDate();

        } else if (requestCode == 2) {
            cal.set(Calendar.HOUR_OF_DAY, data.getExtras().getInt("jam"));
            cal.set(Calendar.MINUTE, data.getExtras().getInt("menit"));
            displayDate();

        }
    }
}

