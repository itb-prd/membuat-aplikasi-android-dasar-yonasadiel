package com.example.ya_.myapplication;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by YA_ on 11/10/2016.
 */

public class editScreen extends AppCompatActivity{
    Calendar cal = Calendar.getInstance();
    Date newDate = new Date();

    Intent lastIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.edit_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lastIntent = getIntent();

        EditText newNameET = (EditText) findViewById(R.id.new_name_list);
        newNameET.setText(lastIntent.getExtras().getString("Name of ToDoList"));

        TextView dateTV = (TextView) findViewById(R.id.datePicker);
        dateTV.setText(lastIntent.getExtras().getString("Date of ToDoList"));

    }

    public void onSendNewTaskName(View view) {

        EditText newNameET = (EditText) findViewById(R.id.new_name_list);
        String newName = String.valueOf(newNameET.getText());

        int index = lastIntent.getExtras().getInt("Index of ToDoList");
        String ous = "";

        SimpleDateFormat formatter = new SimpleDateFormat("EEEE, dd/MM/yyyy HH:mm", Locale.ENGLISH);
        try {
            ous = formatter.format(newDate);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Intent goBack = new Intent();
        goBack.putExtra("New Name",newName);
        goBack.putExtra("New Due Date",ous);
        goBack.putExtra("Index of ToDoList",index);
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
