package com.example.ya_.myapplication;

import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/* 1.0 All set
 * 1.1 App Name changed from "My Application" to "To Do List"
 *     editScreen EditText intial Text set to last task name
 * 1.2 Back Button won't crashed the apps
 * 1.3 Due Date Added
 *
 */

public class MainActivity extends AppCompatActivity {

    public ArrayList<ToDo> ToDoListContent = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        readFromFile();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                Intent getNewNameIntent = new Intent(view.getContext(), addScreen.class);
                final int result = 2;

                startActivityForResult(getNewNameIntent, result);
            }
        });

       displayList();

    }

    protected void displayList() {
        /*
        ToDoListContent.sort(new Comparator<ToDo>() {
            @Override
            public int compare(ToDo o1, ToDo o2) {
                if (o1.dueDate.before(o2.dueDate)) {
                    return -1;
                } else if (o1.dueDate.after(o2.dueDate)) {
                    return 1;
                } else {
                    return 0;
                }
            }

            @Override
            public boolean equals(Object obj) {
                return false;
            }
        });*/

        /**********SORTING ToDoList ************/
        for (int i=0; i<ToDoListContent.size(); i++) {
            for (int j=i+1; j<ToDoListContent.size(); j++) {
                if (ToDoListContent.get(i).dueDate.after(ToDoListContent.get(j).dueDate)) {
                    Collections.swap(ToDoListContent,i,j);
                }
            }
        }

        /*********Viewing************/
        ListAdapter theAdapter = new taskAdapter(this, ToDoListContent);

        ListView theListView = (ListView) findViewById(R.id.ToDoListView);

        theListView.setAdapter(theAdapter);

        theListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long id) {

                /************* MENGGUNAKAN DIALOG FRAGMENT ***********/

                DialogFragment newFragment = editDialog.newInstance(position,ToDoListContent.get(position));
                newFragment.show(getFragmentManager(),"dialog");
                displayList();

                /************* MENGGUNAKAN DIALOG BIASA **************/
                /*
                AlertDialog.Builder theDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                theDialogBuilder.setMessage("Apa yang ingin Anda lakukan dengan to do '"+ToDoListContent.get(position)+"' ?");
                theDialogBuilder.setPositiveButton("UBAH", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent getNewNameIntent = new Intent(MainActivity.this, editScreen.class);
                        final int result = 1;
                        getNewNameIntent.putExtra("index of ToDoList",position);

                        startActivityForResult(getNewNameIntent, result);

                    }
                });
                theDialogBuilder.setNegativeButton("HAPUS",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ToDoListContent.remove(position);
                        displayList();

                    }
                });

                AlertDialog theDialog = theDialogBuilder.create();
                theDialog.show();
                */
            }
        });

        saveIntoFile();
    }

    private void readFromFile() {
        String filename = "todolistfile.txt";

        try {
            FileInputStream ins = new FileInputStream(new File(getFilesDir(), filename));
            BufferedReader br = new BufferedReader(new InputStreamReader(ins));

            String todo;

            while((todo = br.readLine()) != null) {
                ToDo newToDo = new ToDo(todo,br.readLine());
                ToDoListContent.add(newToDo);
            }
            br.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void saveIntoFile() {
        String filename = "todolistfile.txt";

        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(new File(getFilesDir(), filename)));
            for (ToDo todo: ToDoListContent) {
                pw.println(todo.name);
                pw.println(todo.dueDateString);
            }
            pw.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && data != null) {
            // Edit a Task
            int index = data.getExtras().getInt("Index of ToDoList");

            ToDo newToDo = new ToDo(data.getExtras().getString("New Name"),data.getExtras().getString("New Due Date"));
            ToDoListContent.set(index,newToDo);

        } else if (requestCode == 2 && data != null) {
            // Add a new Task
            ToDo newToDo = new ToDo(data.getExtras().getString("New Task Name"),data.getExtras().getString("New Task Due Date"));
            ToDoListContent.add(newToDo);

        } else if (requestCode == 3 && data != null) {
            //delete a Task
            ToDoListContent.remove(data.getExtras().getInt("Index of ToDoList"));
        }

        displayList();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.about_page) {
            DialogFragment Fragment = new AboutDialog();
            Fragment.show(getFragmentManager(),"theDialog");

            return true;
        } else if (id == R.id.exit_the_app) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
