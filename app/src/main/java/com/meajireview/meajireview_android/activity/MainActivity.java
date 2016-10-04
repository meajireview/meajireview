package com.meajireview.meajireview_android.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.meajireview.meajireview_android.R;
import com.meajireview.meajireview_android.adapter.CategoryAdapter;
import com.meajireview.meajireview_android.item.CategoryItem;

import java.util.ArrayList;

/**
 * MainActivity<br>
 *   메인페이지
 *
 */
public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<CategoryItem> items= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
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
}
