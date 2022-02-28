package com.mohamedsamy.ititimetablemanagerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

public class Login extends AppCompatActivity {
    DBhelper dbms = new DBhelper(this , "data.db" , null , 2);

    EditText username;
    EditText password;
    Button login;
    Intent outIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.regName);
        password = findViewById(R.id.Password);
        login = findViewById(R.id.btnReg);
        login.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("Range")
            @Override
            public void onClick(View view) {
                Cursor res = dbms.getUser(username.getText().toString(), password.getText().toString());
                if(login.getText().length() == 0  || password.getText().length() == 0 ) {
                    Snackbar snackbar = Snackbar
                            .make(view, "Please insert user name and password", Snackbar.LENGTH_LONG);
                    snackbar.show();
                    return;
                }
                try {
                    res.moveToFirst();
                    if (res.isAfterLast() || res == null) {
                        Snackbar snackbar = Snackbar
                                .make(view, "User Not Found", Snackbar.LENGTH_LONG);
                        snackbar.show();
                    } else {
                        System.out.println("Found, User :" + res.getString(res.getColumnIndex(DBhelper.COL_USER))
                                + "\n Pass : " + res.getString(res.getColumnIndex(DBhelper.COL_PASS)));
                        outIntent = new Intent(Login.this, Tasks.class);
                        outIntent.putExtra("UID", res.getString(res.getColumnIndex(DBhelper.COL_USER)));
                        startActivity(outIntent);
                    }
                } catch (NullPointerException e) {
                    Snackbar snackbar = Snackbar
                            .make(view, "User Not Found", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
            }
        });
    }
}