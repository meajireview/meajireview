package com.meajireview.meajireview_android.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.meajireview.meajireview_android.R;
import com.meajireview.meajireview_android.adapter.ShopViewPagerAdapter;
import com.meajireview.meajireview_android.fragment.ListFragment;

/**
 * Created by songmho on 2016-10-09.
 */

public class ShopDetailActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopdetail);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolBar);
        FloatingActionButton fabCall = (FloatingActionButton)findViewById(R.id.fabCall);
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout)findViewById(R.id.collapsingToolbar);
        ViewPager viewPager = (ViewPager)findViewById(R.id.viewPager);
        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabLayout);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        collapsingToolbar.setTitle("ShopDetail");
        fabCall.setOnClickListener(this);

        ShopViewPagerAdapter shopViewPagerAdapter =new ShopViewPagerAdapter(getSupportFragmentManager());
        shopViewPagerAdapter.addFragment(new ListFragment(),"메뉴");
        shopViewPagerAdapter.addFragment(new ListFragment(),"리뷰");
        viewPager.setAdapter(shopViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.fabCall){
            Toast.makeText(this, "전화번호 받고 intent 해야함.", Toast.LENGTH_SHORT).show();
        }
    }
}
