package com.example.romanm.recyclerpagination.di.modules;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.romanm.recyclerpagination.data.Repository;
import com.example.romanm.recyclerpagination.data.local.LocalDataRoom;
import com.example.romanm.recyclerpagination.data.local.LocalSource;
import com.example.romanm.recyclerpagination.data.local.RoomDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by RomanM on 29.10.2017.
 */
@Module
public class RepositoryModule {

    @Singleton
    @Provides
    public LocalDataRoom provideLocalDataRoom(Context context) {
        return Room.databaseBuilder(context.getApplicationContext(),LocalDataRoom.class,"database").build();
    }

    @Singleton
    @Provides
    public RoomDao provideDao(LocalDataRoom localDataRoom) {
        return localDataRoom.getDAO();
    }

    @Singleton
    @Provides
    public LocalSource provideLocalSource(RoomDao dao) {
        return new LocalSource(dao);
    }

    @Singleton
    @Provides
    public Repository provideRepository(LocalSource localSource) {
        return new Repository(localSource);
    }
}
