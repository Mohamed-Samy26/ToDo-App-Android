package com.mohamedsamy.ititimetablemanagerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class Tasks extends AppCompatActivity {

    private RecyclerView r1;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private Button btn;
    private Intent addTask;
    private Cursor tasks;
    private boolean frst = false;
    private  ArrayList<Item> arr;
    Runnable del = () -> { };


    TextView noData;
    DBhelper dbms ;
    @SuppressLint("Range")
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);
        dbms = new DBhelper(this , "data.db" , null , 2);
        noData = findViewById(R.id.noData);
        Intent inIntent = getIntent();
        btn = findViewById(R.id.addTask);
        tasks = dbms.getTask(inIntent.getStringExtra("UID"));
        arr = new ArrayList<Item>();
        Runnable x = () ->
        {
            try {
                arr.clear();
                tasks.moveToFirst();
                while (!tasks.isAfterLast()) {
                    arr.add(new Item(inIntent.getStringExtra("UID"),
                            tasks.getString(tasks.getColumnIndex(DBhelper.COL_TNAME)),
                            tasks.getString(tasks.getColumnIndex(DBhelper.COL_TDESC)),
                            tasks.getString(tasks.getColumnIndex(DBhelper.COL_TIME))));
                    tasks.moveToNext();
                }
                adapter.notifyDataSetChanged();
            } catch (NullPointerException e) {
                System.out.println("Errorrrrrr");
            }
            r1 = findViewById(R.id.r1);
            layoutManager = new LinearLayoutManager(this);
            r1.setLayoutManager(layoutManager);
            adapter = new ItemAdapter(arr, dbms, del);
            if (!arr.isEmpty()) {
                r1.setAdapter(adapter);
                noData.setWidth(0);
                noData.setHeight(0);
            }
        };
        del = x;
        x.run();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTask = new Intent(Tasks.this , AddTask.class);
                addTask.putExtra("UID" , inIntent.getStringExtra("UID") );
                startActivity(addTask);
                adapter.notifyDataSetChanged();
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
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onPause() {
        super.onPause();
        dbms.close();
    }

    @SuppressLint("Range")
    protected void onResume() {
        super.onResume();
        System.out.println("Resuming");
        if(frst) {
            arr.clear();
            adapter.notifyDataSetChanged();
            recreate();
            adapter.notifyDataSetChanged();
        }
        else frst = true;
//        noData = findViewById(R.id.noData);
//        Intent inIntent = getIntent();
//        btn = findViewById(R.id.addTask);
//        tasks = dbms.getTask(inIntent.getStringExtra("UID"));
//        arr = new ArrayList<Item>();
//
//
//        Runnable x = () ->
//        {
//            try {
//                tasks.moveToFirst();
//                arr.clear();
//                while (!tasks.isAfterLast()) {
//                    arr.add(new Item(inIntent.getStringExtra("UID"),
//                            tasks.getString(tasks.getColumnIndex(DBhelper.COL_TNAME)),
//                            tasks.getString(tasks.getColumnIndex(DBhelper.COL_TDESC)),
//                            tasks.getString(tasks.getColumnIndex(DBhelper.COL_TIME))));
//                    tasks.moveToNext();
//                }
//            } catch (NullPointerException e) {
//                System.out.println("Errorrrrrr");
//            }
//            r1 = findViewById(R.id.r1);
//            layoutManager = new LinearLayoutManager(this);
//            r1.setLayoutManager(layoutManager);
//            adapter = new ItemAdapter(arr, dbms, add);
//            if (!arr.isEmpty()) {
//                r1.setAdapter(adapter);
//                noData.setWidth(0);
//                noData.setHeight(0);
//            }
//        };
//        add = x;
//        x.run();
//
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                addTask = new Intent(Tasks.this , AddTask.class);
//                addTask.putExtra("UID" , inIntent.getStringExtra("UID") );
//                startActivity(addTask);
//                adapter.notifyDataSetChanged();
//            }
//        });
//        if (tasks == null || arr.isEmpty()){
//            System.out.println("Empty tasks");
//        }
//        else {
//            ArrayList<Item> items = new ArrayList<Item>();
//            while (!tasks.isAfterLast()){
//                items.add(new Item(inIntent.getStringExtra("UID"),
//                        tasks.getString(tasks.getColumnIndex(DBhelper.COL_UID)) ,
//                        tasks.getString(tasks.getColumnIndex(DBhelper.COL_UID))  ,
//                        tasks.getString(tasks.getColumnIndex(DBhelper.COL_TIME))));
//            }
//            r1.setHasFixedSize(false);
//        }
//        adapter.notifyDataSetChanged();
    }
}