package com.mohamedsamy.ititimetablemanagerapp;

public class Item {
    private String uid;
    private String name;
    private String di;
    private String time;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Item(String uid , String name, String di, String time) {
        this.uid = uid;
        this.name = name;
        this.di = di;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDi() {
        return di;
    }

    public void setDi(String di) {
        this.di = di;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
