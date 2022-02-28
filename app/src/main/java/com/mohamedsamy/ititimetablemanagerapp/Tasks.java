package com.mohamedsamy.ititimetablemanagerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class Tasks extends AppCompatActivity {

    private RecyclerView r1;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private Button btn;
    private Intent addTask;

    TextView noData;
    DBhelper dbms = new DBhelper(this , "data.db" , null , 2);
    @SuppressLint("Range")
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);
        noData = findViewById(R.id.noData);
        Intent inIntent = getIntent();
        btn = findViewById(R.id.addTask);
        Cursor tasks = dbms.getTask(inIntent.getStringExtra("UID"));

        ArrayList<Item> arr = new ArrayList<Item>();

        try {
            tasks.moveToFirst();
            while (!tasks.isAfterLast()){
                arr.add(new Item(inIntent.getStringExtra("UID"),
                        tasks.getString(tasks.getColumnIndex(DBhelper.COL_TNAME)) ,
                        tasks.getString(tasks.getColumnIndex(DBhelper.COL_TDESC)),
                        tasks.getString(tasks.getColumnIndex(DBhelper.COL_TIME))));
                tasks.moveToNext();
            }
        }
         catch (NullPointerException e) {
             System.out.println("Errorrrrrr");
        }
        r1 = findViewById(R.id.r1);
        layoutManager = new LinearLayoutManager(this);
        r1.setLayoutManager(layoutManager);
        adapter = new ItemAdapter(arr);
        if (!arr.isEmpty()) {
            r1.setAdapter(adapter);
            noData.setWidth(0);
            noData.setHeight(0);
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTask = new Intent(Tasks.this , AddTask.class);
                addTask.putExtra("UID" , inIntent.getStringExtra("UID") );
                startActivity(addTask);
            }
        });
        if (tasks == null || arr.isEmpty()){
            System.out.println("Empty tasks");
        }
        else {
            ArrayList<Item> items = new ArrayList<Item>();
            while (!tasks.isAfterLast()){
                items.add(new Item(inIntent.getStringExtra("UID"),
                        tasks.getString(tasks.getColumnIndex(DBhelper.COL_UID)) ,
                        tasks.getString(tasks.getColumnIndex(DBhelper.COL_UID))  ,
                        tasks.getString(tasks.getColumnIndex(DBhelper.COL_TIME))));
            }
            r1.setHasFixedSize(false);
        }
    }
    protected void onResume() {
        super.onResume();

    }
}