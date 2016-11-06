package com.meajireview.meajireview_android.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.meajireview.meajireview_android.R;
import com.meajireview.meajireview_android.adapter.ShopDetailAdapter;
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
    @BindView (R.id.toolBar) Toolbar toolbar;
    @BindView(R.id.collapsingToolbar) CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.fabCall)  FloatingActionButton fabCall;
    @BindView(R.id.recyclerView) RecyclerView recyclerView;


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

        ShopHeader shopHeader = new ShopHeader("10:00 ~ 20:00","안함","4.5","033-766-3373");

        for(int i=0;i<11;i++)                          //나중에 db에서 가져올 때 변경 필요.
            shopItems.add(new ShopItem("치닭삼","6000원"));

        recyclerView.setAdapter(new ShopDetailAdapter(getApplicationContext(),shopHeader,shopItems));
    }

    /**
     * Toolbar 초기화 메소드 <br>
     */
    private void initToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        collapsingToolbar.setTitle("둘로스돈까스");       //전의 목록에서 get Intent로 받아와야
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.fabCall){
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", "010-2016-2689", null));
            startActivity(intent);
        }
    }
}
