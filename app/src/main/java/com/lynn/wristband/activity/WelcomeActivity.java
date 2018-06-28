package com.lynn.wristband.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.lynn.wristband.R;
import com.lynn.wristband.base.FullScreenBaseActivity;

public class WelcomeActivity extends FullScreenBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startPagerActivityDelay();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_welcome;
    }


    private void startPagerActivityDelay() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(WelcomeActivity.this, WelcomePagerActivity.class));
                finish();
            }
        }, 2000);
    }
}
