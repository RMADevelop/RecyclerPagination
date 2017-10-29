package com.example.romanm.recyclerpagination.di.modules;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by RomanM on 29.10.2017.
 */
@Module
public class ContextModule {
    private Context context;

    public ContextModule(Context context) {
        this.context= context;
    }

    @Provides
    @Singleton
    public Context provideApplication() {
        return context;
    }
}
