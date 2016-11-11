package com.example.ya_.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by YA_ on 11/11/2016.
 */

public class dateScreen extends AppCompatActivity {
    Intent lastIntent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lastIntent = getIntent();

        int tahun = lastIntent.getExtras().getInt("tahun");
        int bulan = lastIntent.getExtras().getInt("bulan");
        int hari = lastIntent.getExtras().getInt("hari");

        Intent goBack = new Intent();
        goBack.putExtra("tahun",tahun);
        goBack.putExtra("hari",hari);
        goBack.putExtra("bulan",bulan);
        setResult(RESULT_OK, goBack);

        finish();
    }
}
