package com.lynn.wristband.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.lynn.wristband.R;
import com.lynn.wristband.base.FullScreenBaseActivity;

public class MainActivity extends FullScreenBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }
}
