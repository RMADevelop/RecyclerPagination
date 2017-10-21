package com.example.romanm.recyclerpagination.mvp.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.romanm.recyclerpagination.mvp.views.MainView;


@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {
    public void msg() {
        getViewState().showMsg();
    }
}
