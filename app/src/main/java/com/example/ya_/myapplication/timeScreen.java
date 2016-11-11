package com.example.ya_.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by YA_ on 11/11/2016.
 */

public class timeScreen extends AppCompatActivity {
    Intent lastIntent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lastIntent = getIntent();

        int jam = lastIntent.getExtras().getInt("jam");
        int menit = lastIntent.getExtras().getInt("menit");

        Intent goBack = new Intent();
        goBack.putExtra("jam",jam);
        goBack.putExtra("menit",menit);
        setResult(RESULT_OK, goBack);

        finish();
    }
}