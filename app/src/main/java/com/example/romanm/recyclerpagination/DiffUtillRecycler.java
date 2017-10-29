package com.example.romanm.recyclerpagination;

import android.support.v7.util.DiffUtil;
import android.util.Log;

import com.example.romanm.recyclerpagination.data.Item;

import java.util.List;

/**
 * Created by RomanM on 21.10.2017.
 */

public class DiffUtillRecycler extends DiffUtil.Callback {

    private final List<Item> oldList;
    private final List<Item> newList;

    public DiffUtillRecycler(List<Item> oldList, List<Item> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition) == newList.get(newItemPosition);
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        Log.v("ASDSAD","sizeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");

        return oldList.get(oldItemPosition).getId() == newList.get(newItemPosition).getId();
    }
}
