package com.meajireview.meajireview_android.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.meajireview.meajireview_android.R;
import com.meajireview.meajireview_android.SqliteHelper;
import com.meajireview.meajireview_android.adapter.CategoryAdapter;
import com.meajireview.meajireview_android.item.CategoryItem;
import com.parse.ParseUser;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *   메인페이지<br>
 */
public class MainActivity extends AppCompatActivity {
    @BindView(R.id.toolBar) Toolbar toolbar;
    @BindView(R.id.recyclerView) RecyclerView recyclerView;

    public static SqliteHelper sqliteHelper;
    private final String DBNAME = "wtfs.db";
    private final int DBVERSION = 1;
    public SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initToolbar();
        makeList();

    }

    /**
     * toolBar 초기화 메소드<br>
     */
    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("매지리뷰");
    }

    /**
     * 그리드리스트 생성 메소드<br>
     */
    private void makeList() {
        ArrayList<CategoryItem> items= new ArrayList<>();
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        sqliteHelper = new SqliteHelper(this, DBNAME, null, DBVERSION);
        db = sqliteHelper.getReadableDatabase();

        Cursor cursor = null;
        try {
            cursor = db.rawQuery("select category_name from Category", null);
            while (cursor.moveToNext())
                items.add(new CategoryItem(cursor.getString(0)));
        }
        catch (Exception e){
            e.printStackTrace();
        } finally {
            if (cursor != null) cursor.close();
        }

            items.add(new CategoryItem("추천"));

        items.add(new CategoryItem("설정"));
        recyclerView.setAdapter(new CategoryAdapter(getApplicationContext(),items));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.action_search){
            startActivity(new Intent(MainActivity.this,SearchActivity.class));
            overridePendingTransition(0,0);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
