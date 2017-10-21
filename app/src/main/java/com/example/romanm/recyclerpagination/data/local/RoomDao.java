package com.example.romanm.recyclerpagination.data.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.romanm.recyclerpagination.data.Item;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by RomanM on 21.10.2017.
 */
@Dao
public interface RoomDao {
    @Insert
    void saveItem(Item item);

    @Query("SELECT * FROM Item LIMIT :start, :limit")
    Single<List<Item>> getItems(int start, int limit);

    @Query("SELECT * FROM Item WHERE id LIKE 1")
    Item checkDb();

    @Query("SELECT * FROM Item")
    Single<List<Item>> getAll();
}
