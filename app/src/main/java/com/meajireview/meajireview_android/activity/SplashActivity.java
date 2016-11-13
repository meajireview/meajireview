package com.meajireview.meajireview_android.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import com.meajireview.meajireview_android.R;
import com.meajireview.meajireview_android.SqliteHelper;

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

            String sql7 = "insert into Category(id, category_name) " +
                    "values (7,'랜덤');";

            db.execSQL(sql7);
            String sql8 = "insert into Shop (ID,name,phone,delivery,open,category_name) values (1,'맛존', '033-000-0000',1,'10:30~20:00','밥');";
            db.execSQL(sql8);
            String sql9 = "insert into Shop (ID,name,phone,delivery,open,category_name) values (2 ,'둘로스 돈까스', '033-000-0000',0,'10:30~20:00','양식');";
            db.execSQL(sql9);
            String sql10 = "insert into Shop (ID,name,phone,delivery,open,category_name) values (3 ,'BnC', '033-000-0000',1,'10:30~20:00','치킨');";
            db.execSQL(sql10);
            sql10 = "insert into Menu (name, price, shop_id) values ('김치찌개',5000, 1);";
            db.execSQL(sql10);
            sql10 = "insert into Menu (name, price, shop_id) values ('된장찌개',5000, 1);";
            db.execSQL(sql10);
            sql10 = "insert into Menu (name, price, shop_id) values ('두부찌개',5000, 2);";
            db.execSQL(sql10);
            sql10 = "insert into Menu (name, price, shop_id) values ('얼큰순두부',5000, 2);";
            db.execSQL(sql10);
            sql10 = "insert into Menu (name, price, shop_id) values ('두부찌개',5000, 3);";
            db.execSQL(sql10);
            sql10 = "insert into Menu (name, price, shop_id) values ('얼큰순두부',5000, 3);";
            db.execSQL(sql10);


        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        if (cursor != null) cursor.close();
    }
    }
}