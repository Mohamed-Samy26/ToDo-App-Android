package com.mohamedsamy.ititimetablemanagerapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class DBhelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "data.db";
    public static final String TABLE_NAME = "users";
    public static final String COL_USER = "user";
    public static final String COL_PASS = "pass";
    public static final String TABLE_NAME2 = "tasks";
    public static final String COL_UID = "uid";
    public static final String COL_TNAME = "name";
    public static final String COL_TDESC = "di";
    public static final String COL_TIME = "time";


    public DBhelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table users " +
                        "(user text primary key,pass text)"
        );
        System.out.println("Users db created");
        db.execSQL(
                "create table tasks " +
                        "(uid text ,name text , di text , time text)"
        );
        System.out.println("Tasks db created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS tasks");
        onCreate(db);
    }
    public void addUser(String name ,String pass){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("user", name);
        contentValues.put("pass", pass);
        db.insert("users", null, contentValues);
        System.out.println("Added Successfully");
    }

    public Cursor getUser(String name ,String pass) {
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            return db.rawQuery( "select * from users where user = "+name+" and pass = "+pass+"", null );

        }
        catch (Exception e){
            System.out.println("Not Found");
            return null;
        }
    }
    public void addTask(String uid , String name ,String desc, String time){
        SQLiteDatabase db = this.getWritableDatabase();
        if (uid.length() == 0){uid = " ";};
        if (name.length() == 0){name = " ";};
        if (desc.length() == 0){desc = " ";};
        if (time.length() == 0){time = " ";};
        ContentValues contentValues = new ContentValues();
        contentValues.put("uid", uid);
        contentValues.put("name", name);
        contentValues.put("di", desc);
        contentValues.put("time", time);
        try {
            db.insert("tasks", null, contentValues);
        }
        catch (SQLiteException  e){
            System.out.println(e.toString() + "/n" + e.getStackTrace() + "/n" + e.getMessage());
        }
//        db.execSQL("INSERT INTO tasks(name,uid,di,time) VALUES ('21','12','12','12')");
        System.out.println("Added task Successfully");
    }
    public void delTask(String uid , String name ,String desc, String time){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME2 , COL_UID +"="+ uid+
                " and "+COL_TDESC +"="+ desc+" and "+
                COL_TNAME +"="+ name +" and "+COL_TIME +"="+ time, null );
    }
    public Cursor getTask(String uid) {
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            return db.rawQuery( "select * from tasks where uid = "+uid, null );
        }
        catch (Exception e){
            System.out.println("Tasks Not Found");
            return null;
        }
    }
}
