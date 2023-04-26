package com.example.projekt_2;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "phones_table")
public class Phone {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String producent;

    private String model;

    private int android_version;

    private String webpage;

    public Phone(String producent, String model, int android_version, String webpage) {
        this.producent = producent;
        this.model = model;
        this.android_version = android_version;
        this.webpage = webpage;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getProducent() {
        return producent;
    }

    public String getModel() {
        return model;
    }

    public int getAndroid_version() {
        return android_version;
    }

    public String getWebpage() {
        return webpage;
    }

}
