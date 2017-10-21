package com.example.romanm.recyclerpagination.data;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by RomanM on 21.10.2017.
 */

public interface DataSource {

    void saveItem(Item item);

    Single<List<Item>> getItems(int start, int limit);

    Single<List<Item>> getAll();

    Item checkDb();

}
