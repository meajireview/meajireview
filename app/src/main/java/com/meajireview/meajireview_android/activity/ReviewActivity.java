package com.meajireview.meajireview_android.activity;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.meajireview.meajireview_android.R;
import com.meajireview.meajireview_android.adapter.ReviewAdapter;
import com.meajireview.meajireview_android.adapter.ShopListAdapter;
import com.meajireview.meajireview_android.item.ReviewItem;
import com.meajireview.meajireview_android.item.ShopInfo;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by songm on 2016-11-23.
 */

public class ReviewActivity extends AppCompatActivity {
    @BindView(R.id.toolBar) Toolbar toolBar;
    @BindView(R.id.recyclerView) RecyclerView recyclerView;


     boolean isLogined = false;
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

        String userName = null;
        if(ParseUser.getCurrentUser()!=null){
            userName = ParseUser.getCurrentUser().getUsername();
            isLogined = true;}

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Review");
        query.whereEqualTo("shop_id",getIntent().getIntExtra("shopId",-1));
        final String finalUserName = userName;
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                ArrayList<ReviewItem> reviewItems = new ArrayList<>();

                ReviewItem reviewHeader =null;
                for(ParseObject o : list){
                    Log.e("ddd", ""+list.size());
                    if(isLogined && o.getString("reviewer").equals(finalUserName)) {
                        reviewHeader = new ReviewItem(o.getBoolean("is_recommend"), o.getString("contents"));
                        Log.e("ddd", ""+o.getString("contents"));
                    }
                    reviewItems.add(new ReviewItem(o.getBoolean("is_recommend"), o.getString("contents")));
                    Log.e("ddd", ""+o.getString("contents"));
                }
                if(reviewHeader==null)
                    reviewHeader = new ReviewItem(false,"");

                Log.e("ddd", ""+reviewItems.size());
                recyclerView.setAdapter(new ReviewAdapter(getApplicationContext(), reviewHeader,reviewItems));
            }
        });


    }

    /**
     * Toolbar 초기화 메소드<br>
     */
    private void initToolbar() {
        setSupportActionBar(toolBar);
        if(getSupportActionBar()!=null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("리뷰");

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
