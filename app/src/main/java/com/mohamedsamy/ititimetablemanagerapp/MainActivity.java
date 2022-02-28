package com.mohamedsamy.ititimetablemanagerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button login;
    Button reg;
    Intent nextPage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = findViewById(R.id.login);
        reg = findViewById(R.id.reg);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextPage = new Intent(MainActivity.this , Login.class);
                startActivity(nextPage);
            }
        });
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextPage = new Intent(MainActivity.this , Register.class);
                startActivity(nextPage);
            }
        });

    }
}