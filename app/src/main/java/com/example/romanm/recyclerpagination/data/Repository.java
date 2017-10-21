package com.example.romanm.recyclerpagination.data;

import com.example.romanm.recyclerpagination.Item;
import com.example.romanm.recyclerpagination.data.local.LocalSource;

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
}
