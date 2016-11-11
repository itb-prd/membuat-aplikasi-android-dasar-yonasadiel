package com.example.ya_.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by YA_ on 11/9/2016.
 */

public class AboutDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder theDialog = new AlertDialog.Builder(getActivity());

        theDialog.setTitle("About");
        theDialog.setMessage("To Do List v. 1.3\nMade by YA_");
        theDialog.setPositiveButton("OK", null);

        /*return super.onCreateDialog(savedInstanceState);*/
        return theDialog.create();
    }
}
