package com.example.projekt_2;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PhoneDao {

    @Insert
    void insert(Phone phone);

    @Update
    void update(Phone phone);

    @Delete
    void delete(Phone phone);

    @Query("DELETE FROM phones_table")
    void deleteAllPhones();

    @Query("SELECT * FROM phones_table ORDER BY producent ASC")
    LiveData<List<Phone>> getAllPhones();
}
