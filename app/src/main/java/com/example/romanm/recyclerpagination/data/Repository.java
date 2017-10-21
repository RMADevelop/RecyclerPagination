package com.example.romanm.recyclerpagination.data;

import com.example.romanm.recyclerpagination.data.local.LocalSource;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by RomanM on 21.10.2017.
 */

public class Repository implements DataSource {
    LocalSource local;


    private static Repository INSTANCE;

    private Repository(LocalSource local) {
        this.local = local;
    }

    public static Repository getInstance(LocalSource local) {
        if (INSTANCE == null)
            INSTANCE = new Repository(local);
        return INSTANCE;
    }

    @Override
    public void saveItem(Item item) {
        local.saveItem(item);
    }

    @Override
    public Single<List<Item>> getItems(int start, int limit) {
        return local.getItems(start,limit);
    }

    @Override
    public Single<List<Item>> getAll() {
        return local.getAll();
    }

    @Override
    public Item checkDb() {
        return local.checkDb();
    }
}
