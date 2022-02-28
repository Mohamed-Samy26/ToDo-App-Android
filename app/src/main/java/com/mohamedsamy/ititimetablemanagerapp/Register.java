package com.mohamedsamy.ititimetablemanagerapp;
import androidx.appcompat.app.AppCompatActivity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.google.android.material.snackbar.Snackbar;

public class Register extends AppCompatActivity {

    DBhelper dbms = new DBhelper(this , "data.db" , null , 2);

    EditText username;
    EditText password;
    EditText password2;
    Button reg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username = findViewById(R.id.regName);
        password = findViewById(R.id.Password);
        password2 = findViewById(R.id.Password2);
        reg = findViewById(R.id.btnReg);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(password.getText().length() == 0 && username.getText().length() == 0){
                    Snackbar snackbar = Snackbar
                            .make(view, "Please enter username and password", Snackbar.LENGTH_LONG);
                    snackbar.show();
                    return;
                }
                if ( password.getText().toString().equals(password2.getText().toString())){
                    try {
                        dbms.addUser(username.getText().toString(), password.getText().toString());
                        System.out.println("Added !!!!!!!!!!!!!!!!!!!");
                        finish();
                    }
                    catch (Exception e){
                        System.out.println("Already there");
                    }
                }
                else {
                    Snackbar snackbar = Snackbar
                            .make(view, "Passwords Don't match", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
            }
        });
    }
}