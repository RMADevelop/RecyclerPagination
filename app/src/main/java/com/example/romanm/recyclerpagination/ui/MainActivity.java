package com.example.romanm.recyclerpagination.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.romanm.recyclerpagination.Injection;
import com.example.romanm.recyclerpagination.data.Item;
import com.example.romanm.recyclerpagination.R;
import com.example.romanm.recyclerpagination.mvp.presenters.MainPresenter;
import com.example.romanm.recyclerpagination.mvp.views.MainView;
import com.example.romanm.recyclerpagination.ui.adapter.PagAdapter;

import java.util.Collections;
import java.util.List;

public class MainActivity extends MvpAppCompatActivity implements MainView, RecyclerListener {

    @InjectPresenter
    MainPresenter presenter;

    private RecyclerView recyclerView;

    private Button initItemsDbButton;

    private ProgressBar preogress;

    private PagAdapter adapter;

    private LinearLayoutManager layoutManager;

    private RecyclerView.OnScrollListener scrollListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//presenter.initItemsDb();
        presenter.setRepository(Injection.provideRepository(getApplicationContext()));
        presenter.checkDb();
        initButton();
        initProgress();
        initRecyclerView();
        initDataInRecycler();
        recyclerView.setHasFixedSize(true);
    }

    private void initProgress() {
        preogress = (ProgressBar) findViewById(R.id.progressBar);
    }

    private void initButton() {
        initItemsDbButton = (Button) findViewById(R.id.button_initDB);
        initItemsDbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.initItemsDb();
            }
        });
    }

    private void initDataInRecycler() {
        presenter.setList();
    }


    private void initRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        adapter = new PagAdapter(Collections.<Item>emptyList(), Collections.<Item>emptyList(), Collections.<Item>emptyList(), this);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);


        scrollListener = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                presenter.itemRecycler(layoutManager.findLastVisibleItemPosition(), layoutManager.getItemCount());
            }
        };
        recyclerView.addOnScrollListener(scrollListener);


    }

    @Override
    public void showMsg() {
    }

    @Override
    public void setFirstAdapter(List<Item> list) {
        adapter.setFirstList(list);
    }

    @Override
    public void setAllItems(List<Item> list) {
        adapter.addAll(list);
    }

    @Override
    public void showRecycler() {
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRecycler() {
        recyclerView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showProgress() {
        preogress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        preogress.setVisibility(View.INVISIBLE);
    }

    @Override
    public void enableButton() {
        initItemsDbButton.setEnabled(true);
    }

    @Override
    public void disableButton() {
        initItemsDbButton.setEnabled(false);
    }

    @Override
    public void itemBind(int id, int itemCount) {
        presenter.setNumberItem(id);
    }
}
