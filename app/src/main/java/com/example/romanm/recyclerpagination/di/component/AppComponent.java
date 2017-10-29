package com.example.romanm.recyclerpagination.di.component;

import com.example.romanm.recyclerpagination.di.modules.ContextModule;
import com.example.romanm.recyclerpagination.di.modules.RepositoryModule;
import com.example.romanm.recyclerpagination.mvp.presenters.MainPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by RomanM on 29.10.2017.
 */
@Singleton
@Component(modules = {ContextModule.class, RepositoryModule.class})
public interface AppComponent {

    void inject(MainPresenter mainPresenter);
}
