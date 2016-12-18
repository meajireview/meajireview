package com.meajireview.meajireview_android.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.meajireview.meajireview_android.R;
import com.meajireview.meajireview_android.SqliteHelper;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

/**
 * Created by TOMO on 2016-10-07.
 */

public class SplashActivity extends AppCompatActivity {
    int SPLASH_TIME=3000;
    public static SqliteHelper sqliteHelper;
    private final String DBNAME = "wtfs.db";
    private final int DBVERSION = 1;
    public SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        initDataBase();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
                finish();
            }
        }, SPLASH_TIME);
    }

    private void insertDataBase() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Shop");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                int i= 1;
                String sql8 = null;
                for(ParseObject o : list){
                    Log.e(""+i,""+o.getString("name"));
                    if(o.getBoolean("isdelivery"))
                        sql8 = "insert into Shop (ID,name,phone,delivery,open,category_name) values ("+i+",'"+o.getString("name")+
                            "', '"+o.getString("phone")+"',1,'"+o.getString("open")+" ~ "+o.getString("close")+"','"+o.getString("category_name")+"');";
                    else
                        sql8 = "insert into Shop (ID,name,phone,delivery,open,category_name) values ("+i+",'"+o.getString("name")+
                                "', '"+o.getString("phone")+"',0,'"+o.getString("open")+" ~ "+o.getString("close")+"','"+o.getString("category_name")+"');";
                    db.execSQL(sql8);
                    i++;
                }
            }
        });
    }

    private void initDataBase() {
        sqliteHelper = new SqliteHelper(this, DBNAME, null, DBVERSION);
        db = sqliteHelper.getWritableDatabase();

        boolean isExist = false;
        Cursor cursor = null;
        try{
        cursor = db.rawQuery("select * from Category;", null);
        while (cursor.moveToNext())
            if (cursor.getString(1) != null)
                isExist = true;

        if (!isExist) {      //Question 데이터 생성(임의로)
            String sql = "insert into Category(id, category_name) " +
                    "values (0,'밥');";
            db.execSQL(sql);
            String sql1 = "insert into Category(id, category_name) " +
                    "values (1,'중식');";
            db.execSQL(sql1);

            String sql2 = "insert into Category(id, category_name) " +
                    "values (2,'양식');";
            db.execSQL(sql2);

            String sql3 = "insert into Category(id, category_name) " +
                    "values (3,'치킨');";
            db.execSQL(sql3);

            String sql4 = "insert into Category(id, category_name) " +
                    "values (4,'야식');";
            db.execSQL(sql4);

            String sql5 = "insert into Category(id, category_name) " +
                    "values (5,'카페');";
            db.execSQL(sql5);

            String sql6 = "insert into Category(id, category_name) " +
                    "values (6,'술집');";
            db.execSQL(sql6);

            insertDataBase();

        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        if (cursor != null) cursor.close();
    }
    }
}