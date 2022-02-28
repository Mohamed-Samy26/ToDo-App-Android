package com.mohamedsamy.ititimetablemanagerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

public class AddTask extends AppCompatActivity {

    private EditText name;
    private EditText di;
    private EditText time;
    private Button add;
    private Button cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        DBhelper dbms = new DBhelper(this , "data.db" , null , 2);

        add = findViewById(R.id.btnaddTask);
        Intent in = getIntent();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = findViewById(R.id.taskname);
                di = findViewById(R.id.taskdesc);
                time = findViewById(R.id.taskDate);
                try {
                    dbms.addTask(in.getStringExtra("UID"),
                             name.getText().toString(), di.getText().toString() , time.getText().toString() );
                    finish();
                }
                catch (NullPointerException e){
                    Snackbar snackbar = Snackbar
                            .make(view, "Please insert all data", Snackbar.LENGTH_SHORT);
                    snackbar.show();                }
            }
        });
        cancel = findViewById(R.id.btnCancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}