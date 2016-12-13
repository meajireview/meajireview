package com.meajireview.meajireview_android.activity;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.meajireview.meajireview_android.R;
import com.meajireview.meajireview_android.adapter.ReviewAdapter;
import com.meajireview.meajireview_android.adapter.ShopListAdapter;
import com.meajireview.meajireview_android.item.ReviewItem;
import com.meajireview.meajireview_android.item.ShopInfo;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by songm on 2016-11-23.
 */

public class ReviewActivity extends AppCompatActivity {
    @BindView(R.id.toolBar) Toolbar toolBar;
    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        ButterKnife.bind(this);

        initToolbar();
        initRecyclerView();
        makeList();
    }

    /**
     * List 생성 메소드 <br>
     */
    private void makeList() {
        ReviewItem reviewHeader = new ReviewItem(1,"");
        ArrayList<ReviewItem> reviewItems = new ArrayList<>();

        for(int i=0;i<5; i++)
            reviewItems.add(new ReviewItem(3,""));


        recyclerView.setAdapter(new ReviewAdapter(getApplicationContext(), reviewHeader,reviewItems));
    }

    /**
     * Toolbar 초기화 메소드<br>
     */
    private void initToolbar() {
        setSupportActionBar(toolBar);
        if(getSupportActionBar()!=null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if(getIntent().getStringExtra("category")!=null) {
            getSupportActionBar().setTitle(getIntent().getStringExtra("category"));
        }
    }

    /**
     * RecyclerView 초기화 메소드<br>
     */
    private void initRecyclerView() {
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
    }

}
