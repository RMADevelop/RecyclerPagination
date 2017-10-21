package com.example.romanm.recyclerpagination;

import android.content.Context;

import com.example.romanm.recyclerpagination.data.Repository;
import com.example.romanm.recyclerpagination.data.local.LocalDataRoom;
import com.example.romanm.recyclerpagination.data.local.LocalSource;

/**
 * Created by Roma on 09.09.2017.
 */

public class Injection {
 public static Repository provideRepository(Context context){
     LocalDataRoom db = LocalDataRoom.getInstance(context.getApplicationContext());
     return Repository.getInstance(new LocalSource(db.getDAO()));
 }
}
