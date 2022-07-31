package com.nvd.qrcode.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Item_Qr.class}, version = 1)
public abstract class QRDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "user.db";
    private static QRDatabase instance;

    public static synchronized QRDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), QRDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }

        return instance;
    }

    public abstract QRDAO userDAO();

}
