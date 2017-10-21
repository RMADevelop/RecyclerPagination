package com.example.romanm.recyclerpagination.mvp.views;

import com.arellomobile.mvp.MvpView;
import com.example.romanm.recyclerpagination.data.Item;

import java.util.List;

/**
 * Created by RomanM on 21.10.2017.
 */

public interface MainView extends MvpView {
    public void showMsg();

    void setFirstAdapter(List<Item> list);

    void setAllItems(List<Item> list);

    void showRecycler();

    void hideRecycler();

    void showProgress();

    void hideProgress();

    void enableButton();

    void disableButton();
}
