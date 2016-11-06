package com.meajireview.meajireview_android.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.meajireview.meajireview_android.R;
import com.meajireview.meajireview_android.adapter.ShopListAdapter;
import com.meajireview.meajireview_android.item.ShopInfo;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Sujin on 2016. 11. 7..
 */

public class ShopListActivity extends AppCompatActivity {
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.recyclerView) RecyclerView recyclerView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoplist);
        ButterKnife.bind(this);

        initToolbar();

        makeContext();

    }

    private void makeContext()
    {
        ArrayList<ShopInfo> shopInfos = new ArrayList<>();

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        for(int i=0;i<9;i++)
        {
            shopInfos.add(new ShopInfo("둘로스 돈까스", "033-766-3373", "4.5"));
        }

        recyclerView.setAdapter(new ShopListAdapter(getApplicationContext(), shopInfos));
    }

    private void initToolbar()
    {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}
