package com.meajireview.meajireview_android.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.meajireview.meajireview_android.R;
import com.meajireview.meajireview_android.adapter.ShopDetailAdapter;
import com.meajireview.meajireview_android.item.ShopHeader;
import com.meajireview.meajireview_android.item.ShopItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by songmho on 2016-10-09.
 */

public class ShopDetailActivity extends AppCompatActivity implements View.OnClickListener {

    List<ShopItem> shopItems = new ArrayList<>();
    ShopHeader shopHeader = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopdetail);

        setToolBar();

        FloatingActionButton fabCall = (FloatingActionButton)findViewById(R.id.fabCall);
        fabCall.setOnClickListener(this);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        shopHeader = new ShopHeader("10:00 ~ 20:00","안함","4.5");

        for(int i=0;i<11;i++){
            shopItems.add(new ShopItem("치닭삼","6000원"));
        }
        recyclerView.setAdapter(new ShopDetailAdapter(getApplicationContext(),shopHeader,shopItems));
    }

    /**
     * Toolbar setting 메소드 <br>
     */
    private void setToolBar() {
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolBar);
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout)findViewById(R.id.collapsingToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        collapsingToolbar.setTitle("ShopDetail");
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.fabCall){
            Toast.makeText(this, "전화번호 받고 intent 해야함.", Toast.LENGTH_SHORT).show();
        }
    }
}
