package com.meajireview.meajireview_android.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.meajireview.meajireview_android.R;
import com.meajireview.meajireview_android.adapter.ShopDetailAdapter;
import com.meajireview.meajireview_android.item.CategoryItem;
import com.meajireview.meajireview_android.item.ShopHeader;
import com.meajireview.meajireview_android.item.ShopItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by songmho on 2016-10-09.
 */

public class ShopDetailActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.toolBar) Toolbar toolbar;
    @BindView(R.id.collapsingToolbar) CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.fabCall)  FloatingActionButton fabCall;
    @BindView(R.id.recyclerView) RecyclerView recyclerView;

    SQLiteDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopdetail);
        ButterKnife.bind(this);

        initToolBar();

        fabCall.setOnClickListener(this);

        makeContext();
    }

    /**
     * 액티비티안의 내용을 채우는 메소드<br>
     *     가게 관련 정보 및 메뉴 정보
     */
    private void makeContext() {
        List<ShopItem> shopItems = new ArrayList<>();

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        ShopHeader shopHeader;
        if(getIntent()!=null)
            shopHeader = new ShopHeader(getIntent().getStringExtra("open"),"안함",
                getIntent().getStringExtra("rating"),getIntent().getStringExtra("phone"));
        else
            shopHeader = new ShopHeader("","","","");

        db = SplashActivity.sqliteHelper.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("select name, price from Menu where shop_id ="+getIntent().getIntExtra("shopId",-1), null);
            while (cursor.moveToNext())
                shopItems.add(new ShopItem(cursor.getString(0),cursor.getInt(1)+"원"));
        }
        catch (Exception e){
            e.printStackTrace();
        } finally {
            if (cursor != null) cursor.close();
        }

        recyclerView.setAdapter(new ShopDetailAdapter(getApplicationContext(),shopHeader,shopItems));
    }


    /**
     * Toolbar 초기화 메소드 <br>
     */
    private void initToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(getIntent().getStringExtra("shop")!=null)
            collapsingToolbar.setTitle(getIntent().getStringExtra("shop"));       //전의 목록에서 get Intent로 받아와야

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.fabCall){
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", getIntent().getStringExtra("phone"), null));
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_favorite,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.action_favorite){
            Toast.makeText(ShopDetailActivity.this, "즐겨찾기", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
