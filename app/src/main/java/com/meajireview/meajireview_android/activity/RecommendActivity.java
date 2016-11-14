package com.meajireview.meajireview_android.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.meajireview.meajireview_android.Fragment.RecommendFragment;
import com.meajireview.meajireview_android.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by songm on 2016-11-14.
 */

public class RecommendActivity extends AppCompatActivity {
    @BindView(R.id.toolBar) Toolbar toolBar;
    @BindView(R.id.viewPager) ViewPager viewPager;

    final int MAX_PAGE = 3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend);
        ButterKnife.bind(this);
        initToolbar();

        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
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

    private class ViewPagerAdapter extends FragmentPagerAdapter {
        public ViewPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            if(position<0 || MAX_PAGE<=position)
                return null;

            return new RecommendFragment();
        }

        @Override
        public int getCount() {
            return MAX_PAGE;
        }
    }
}
