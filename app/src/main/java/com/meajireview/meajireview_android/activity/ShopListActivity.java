package com.meajireview.meajireview_android.activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.meajireview.meajireview_android.R;
import com.meajireview.meajireview_android.adapter.ShopListAdapter;
import com.meajireview.meajireview_android.item.CategoryItem;
import com.meajireview.meajireview_android.item.ShopInfo;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Sujin on 2016. 11. 7..
 */

public class ShopListActivity extends AppCompatActivity {
    @BindView(R.id.toolBar) Toolbar toolBar;
    @BindView(R.id.recyclerView) RecyclerView recyclerView;

    public SQLiteDatabase db;
    String category;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoplist);
        ButterKnife.bind(this);

        initToolbar();
        initRecyclerView();

        makeList();
    }

    /**
     * RecyclerView 초기화 메소드<br>
     */
    private void initRecyclerView() {
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
    }

    /**
     * List 생성 메소드 <br>
     */
    private void makeList() {
        ArrayList<ShopInfo> shopInfos = new ArrayList<>();

        db = SplashActivity.sqliteHelper.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("select id, name, phone, open from Shop where category_name='"+category+"'", null);
            while (cursor.moveToNext())
                shopInfos.add(new ShopInfo(cursor.getInt(0), cursor.getString(1),cursor.getString(2),"4.5",cursor.getString(3)));
        }
        catch (Exception e){
            e.printStackTrace();
        } finally {
            if (cursor != null) cursor.close();
        }

        recyclerView.setAdapter(new ShopListAdapter(getApplicationContext(), shopInfos));
    }

    /**
     * Toolbar 초기화 메소드<br>
     */
    private void initToolbar()
    {
        setSupportActionBar(toolBar);
        if(getSupportActionBar()!=null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if(getIntent().getStringExtra("category")!=null) {
            category = getIntent().getStringExtra("category");
            getSupportActionBar().setTitle(category);
        }
    }
}
