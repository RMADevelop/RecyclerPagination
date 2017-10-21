package com.example.romanm.recyclerpagination.data.local;

import com.example.romanm.recyclerpagination.Item;
import com.example.romanm.recyclerpagination.data.DataSource;

/**
 * Created by RomanM on 21.10.2017.
 */

public class LocalSource implements DataSource{

    RoomDao dao;


    public LocalSource(RoomDao dao) {
        this.dao = dao;
    }


    @Override
    public void saveItem(Item item) {
        dao.saveItem(item);
    }
}
