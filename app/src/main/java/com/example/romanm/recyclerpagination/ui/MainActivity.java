package com.example.romanm.recyclerpagination.ui;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.romanm.recyclerpagination.Injection;
import com.example.romanm.recyclerpagination.R;
import com.example.romanm.recyclerpagination.data.Repository;
import com.example.romanm.recyclerpagination.mvp.presenters.MainPresenter;
import com.example.romanm.recyclerpagination.mvp.views.MainView;

public class MainActivity extends MvpAppCompatActivity implements MainView {

    @InjectPresenter
    MainPresenter presenter;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRecyclerView();

    }

    private void initRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    }

    @Override
    public void showMsg() {
        Log.v("DFSDDSDF", "SDFDSDFDS");
    }
}
