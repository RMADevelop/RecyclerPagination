package com.example.romanm.recyclerpagination.data.local;

import com.example.romanm.recyclerpagination.data.Item;
import com.example.romanm.recyclerpagination.data.DataSource;

import java.util.List;

import io.reactivex.Single;

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

    @Override
    public Single<List<Item>> getItems(int start, int limit) {
        return dao.getItems(start,limit);
    }

    @Override
    public Single<List<Item>> getAll() {
        return dao.getAll();
    }

    @Override
    public Item checkDb() {
        return dao.checkDb();
    }
}
