package com.example.romanm.recyclerpagination.data.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;

import com.example.romanm.recyclerpagination.Item;

/**
 * Created by RomanM on 21.10.2017.
 */
@Dao
public interface RoomDao {
    @Insert
    void saveItem(Item item);
}
