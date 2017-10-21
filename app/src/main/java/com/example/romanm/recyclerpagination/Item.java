package com.example.romanm.recyclerpagination;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by RomanM on 21.10.2017.
 */
@Entity
public class Item {

    @PrimaryKey(autoGenerate = true)
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
