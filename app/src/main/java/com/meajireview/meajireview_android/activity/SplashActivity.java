package com.meajireview.meajireview_android.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.meajireview.meajireview_android.R;

/**
 * Created by TOMO on 2016-10-07.
 */

public class SplashActivity extends Activity {
    int SPLASH_TIME=3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                finish();
            }
        }, SPLASH_TIME);
    }
}