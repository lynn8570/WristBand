package com.lynn.wristband.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.lynn.wristband.R;
import com.lynn.wristband.base.FullScreenBaseActivity;
import com.lynn.wristband.fragment.ExcerciseFragment;
import com.lynn.wristband.fragment.SettingsFragment;
import com.lynn.wristband.fragment.StatusFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CollapseActivity extends FullScreenBaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        setContentView(getLayoutId());

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main_collapse;
    }


}
