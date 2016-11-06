package com.meajireview.meajireview_android.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.meajireview.meajireview_android.R;
import com.meajireview.meajireview_android.adapter.CategoryAdapter;
import com.meajireview.meajireview_android.item.CategoryItem;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * MainActivity<br>
 *   메인페이지
 */
public class MainActivity extends AppCompatActivity {
    @BindView(R.id.toolBar) Toolbar toolbar;
    @BindView(R.id.recyclerView) RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initToolbar();
        makeList();
    }

    /**
     * toolbar 초기화 메소드<br>
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

        items.add(new CategoryItem("즐겨찾기"));
        items.add(new CategoryItem("랜덤"));
        items.add(new CategoryItem("밥"));
        items.add(new CategoryItem("중식"));
        items.add(new CategoryItem("양식"));
        items.add(new CategoryItem("치킨"));
        items.add(new CategoryItem("야식"));
        items.add(new CategoryItem("카페"));
        items.add(new CategoryItem("술집"));

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
