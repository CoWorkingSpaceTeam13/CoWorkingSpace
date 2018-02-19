package com.example.nashwaa.e_commerce;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Nashwaa on 12/9/2017.
 */

public class cart_database extends SQLiteOpenHelper{

    private static String DatabaseName="Cart";
    SQLiteDatabase cart;

    public cart_database(Context context) {
        super(context, DatabaseName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Cart (id integer primary key autoincrement,"+" name text , price text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Cart");
        onCreate(db);
    }


}
