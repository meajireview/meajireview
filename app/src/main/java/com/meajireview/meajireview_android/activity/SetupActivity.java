package com.meajireview.meajireview_android.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.meajireview.meajireview_android.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by songm on 2016-12-14.
 */

public class SetupActivity extends AppCompatActivity{
    @BindView(R.id.toolBar) Toolbar toolBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        ButterKnife.bind(this);
        initToolbar();
    }

    /**
     * Toolbar 초기화 메소드<br>
     */
    private void initToolbar() {
        setSupportActionBar(toolBar);
        if(getSupportActionBar()!=null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if(getIntent().getStringExtra("category")!=null) {
            getSupportActionBar().setTitle(getIntent().getStringExtra("설정"));
        }
    }

}
