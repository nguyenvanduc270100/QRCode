package com.nvd.qrcode.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface QRDAO {

    @Insert
    void insertUser(Item_Qr user);

    @Query("SELECT * FROM user")
    List<Item_Qr> getListUser();

    @Delete
    void deleteItem(Item_Qr user);
}
