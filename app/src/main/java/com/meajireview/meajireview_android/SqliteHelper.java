package com.meajireview.meajireview_android;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by songm on 2016-11-14.
 */

public class SqliteHelper extends SQLiteOpenHelper {
    public SqliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String category = "create table Category(id integer primary key autoincrement not null, category_name text not null);";

        String shop = "create table Shop(id integer primary key autoincrement not null, name text," +
                                       " phone text, delivery integer, open text, category_name text, FOREIGN KEY(category_name) REFERENCES Category(category_name));";
        String menu = "create table Menu(name text, price integer, shop_id integer, FOREIGN KEY(shop_id) REFERENCES Shop(id));";

        db.execSQL(category);
        db.execSQL(shop);
        db.execSQL(menu);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String category = "drop table Category";
        String shop = "drop table Shop";
        String menu = "drop table table";
        db.execSQL(category);
        db.execSQL(shop);
        db.execSQL(menu);
        onCreate(db);
    }
}
