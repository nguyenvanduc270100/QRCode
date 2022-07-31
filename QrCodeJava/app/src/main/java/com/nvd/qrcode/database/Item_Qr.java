package com.nvd.qrcode.database;

import android.graphics.Bitmap;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.io.Serializable;

@Entity(tableName = "user")
public class Item_Qr implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    String name;



    public Item_Qr(String name) {
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
