package com.example.romanm.recyclerpagination.mvp.presenters;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.romanm.recyclerpagination.App;
import com.example.romanm.recyclerpagination.data.Item;
import com.example.romanm.recyclerpagination.data.Repository;
import com.example.romanm.recyclerpagination.mvp.views.MainView;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DefaultSubscriber;


@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    @Inject
    Repository repository;

    private static final int LIMIT = 30;
    private int cursor = 0;

    private int numberItem;

    private boolean isLoad = true;

    List<Item> currentLoadedList;


    public MainPresenter() {
        App.getAppComponent().inject(this);
    }

    public void checkDb() {
        Single.fromCallable(new Callable<Item>() {
            @Override
            public Item call() throws Exception {
                return repository.checkDb();
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())


                .subscribeWith(new DisposableSingleObserver<Item>() {
                    @Override
                    public void onSuccess(@NonNull Item item) {
                        if (item == null) {
                            getViewState().hideRecycler();
                            getViewState().enableButton();
                        } else {
                            getViewState().showRecycler();
                            getViewState().initDataInRecycler();
                            getViewState().disableButton();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                    }
                });
    }


    public void initItemsDb() {


        Completable.fromRunnable(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    repository.saveItem(new Item());
                }
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        getViewState().showProgress();
                        getViewState().disableButton();
                    }
                })
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        getViewState().hideProgress();
                        getViewState().showRecycler();

                    }
                })

                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        setList();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });


    }

    public void setList() {

        Single<List<Item>> list1 = repository.getItems(cursor, LIMIT);
        cursor += LIMIT;
        Single<List<Item>> list2 = repository.getItems(cursor, LIMIT);
        cursor += LIMIT;
        Single<List<Item>> list3 = repository.getItems(cursor, LIMIT);
        cursor += LIMIT;

        Flowable<List<Item>> concatList = Single.concat(list1, list2, list3);

        concatList
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DefaultSubscriber<List<Item>>() {
                    @Override
                    public void onNext(List<Item> items) {
                        getViewState().setFirstWithoutNotify(items);
                    }

                    @Override
                    public void onError(Throwable t) {
                        t.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        getViewState().updateAdapter();
                    }
                });


//        repository.getItems(cursor, LIMIT)
//                .subscribeOn(Schedulers.io())
//
//                .flatMap(new Function<List<Item>, SingleSource<List<Item>>>() {
//                    @Override
//                    public SingleSource<List<Item>> apply(@NonNull List<Item> items) throws Exception {
//                        Log.v("HJGJHG", "cursor1  " + cursor);
//
//                        cursor += LIMIT;
//                        Log.v("HJGJHG", "cursor11  " + cursor);
//
//                        getViewState().setFirstWithoutNotify(items);
//                        return repository.getItems(cursor, LIMIT);
//                    }
//                })
//                .flatMap(new Function<List<Item>, SingleSource<List<Item>>>() {
//                    @Override
//                    public SingleSource<List<Item>> apply(@NonNull List<Item> items) throws Exception {
//                        Log.v("HJGJHG", "cursor2  " + cursor);
//
//                        cursor += LIMIT;
//                        Log.v("HJGJHG", "cursor22  " + cursor);
//
//                        getViewState().setFirstWithoutNotify(items);
//                        return repository.getItems(cursor, LIMIT);
//                    }
//                })
//                .doOnEvent(new BiConsumer<List<Item>, Throwable>() {
//                    @Override
//                    public void accept(List<Item> items, Throwable throwable) throws Exception {
//                        Log.v("HJGJHG", "cursor3  " + cursor);
//
//                        cursor += LIMIT;
//                        Log.v("HJGJHG", "cursor33  " + cursor);
//
//                        currentLoadedList = items;
//                        getViewState().setFirstWithoutNotify(items);
//                    }
//                })
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new DisposableSingleObserver<List<Item>>() {
//                    @Override
//                    public void onSuccess(@NonNull List<Item> items) {
//
//                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//                        e.printStackTrace();
//                    }
//                });
    }

    public void itemRecycler(int id, int itemCount) {
        this.numberItem = id;
        int cursorCount = cursor;
        if (getNumberItem() > cursorCount - LIMIT && isLoad) {
            isLoad = false;

            loadItemsNext(cursor, LIMIT);

        }
    }

    public void loadItemsNext(int start, int limit) {

        repository.getItems(start, limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<Item>>() {
                    @Override
                    public void onSuccess(@NonNull List<Item> items) {
                        currentLoadedList = items;
                        getViewState().setFirstAdapter(items);
                        isLoad = true;
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                    }
                });
        cursor += LIMIT;

    }

    public int getNumberItem() {
        return numberItem;
    }

    public void setNumberItem(int numberItem) {
        this.numberItem = numberItem;
    }
}
