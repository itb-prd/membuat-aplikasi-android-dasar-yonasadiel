package com.example.ya_.myapplication;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.widget.DatePicker;
import android.app.Activity;

import java.util.Calendar;

/**
 * Created by YA_ on 11/11/2016.
 */

public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener{
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Use the current date as the default date in the picker
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Intent goBack = new Intent(getActivity(),dateScreen.class);

        final int result = 1;
        goBack.putExtra("tahun",year);
        goBack.putExtra("bulan",month);
        goBack.putExtra("hari",dayOfMonth);

        getActivity().startActivityForResult(goBack, result);
    }
}
