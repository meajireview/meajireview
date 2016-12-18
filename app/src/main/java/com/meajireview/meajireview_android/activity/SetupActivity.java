package com.meajireview.meajireview_android.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.meajireview.meajireview_android.R;
import com.parse.ParseUser;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by songm on 2016-12-14.
 */

public class SetupActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.toolBar) Toolbar toolBar;
    @BindView(R.id.btLogout) Button btLogout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        ButterKnife.bind(this);
        initToolbar();

        btLogout.setOnClickListener(this);
    }

    /**
     * Toolbar 초기화 메소드<br>
     */
    private void initToolbar() {
        setSupportActionBar(toolBar);
        if(getSupportActionBar()!=null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("설정");
        }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btLogout:
                if(ParseUser.getCurrentUser()!=null){
                    ParseUser user = ParseUser.getCurrentUser();
                    user.logOutInBackground();
                    Toast.makeText(this, "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
