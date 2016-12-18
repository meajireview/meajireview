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
import android.util.Log;
import android.widget.Toast;

import com.meajireview.meajireview_android.Algorithm.Algorithm;
import com.meajireview.meajireview_android.Algorithm.Data;
import com.meajireview.meajireview_android.Algorithm.RawData;
import com.meajireview.meajireview_android.Fragment.RecommendFragment;
import com.meajireview.meajireview_android.R;
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
 * Created by songm on 2016-11-14.
 */

public class RecommendActivity extends AppCompatActivity {
    @BindView(R.id.toolBar) Toolbar toolBar;
    @BindView(R.id.viewPager) ViewPager viewPager;

    final int MAX_PAGE = 4;
    public Algorithm algorithm;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend);
        ButterKnife.bind(this);

        initToolbar();
        makeList();

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

    private void makeList()  {
        final List<RawData> lists =new ArrayList<>();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Category");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                for(ParseObject o : list)
                    lists.add(new RawData(o.getString("name"),  o.getInt("count"), o.getInt("today_count")));

                algorithm = new Algorithm(lists);
                List<Data> datas =algorithm.getResultDatas();
                List<String> results = new ArrayList<>();

                for(int i=1;i<=3;i++) {
                    results.add(datas.get(lists.size()-i).getContent());
                    if(i==1)
                        results.add(datas.get(lists.size()-i).getContent());
                }
                viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(),results));
            }
        });
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {
        List<String> lists ;
        public ViewPagerAdapter(FragmentManager fragmentManager, List<String> results) {
            super(fragmentManager);
            lists = results;
        }

        @Override
        public Fragment getItem(int position) {
            if(position<0 || MAX_PAGE<=position)
                return null;

            RecommendFragment frament = new RecommendFragment();
            Bundle bundle = new Bundle();
                bundle.putString("category", lists.get(position));
            frament.setArguments(bundle);
            return frament;
        }

        @Override
        public int getCount() {
            return MAX_PAGE;
        }
    }
}
