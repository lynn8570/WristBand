package com.lynn.wristband.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.widget.Button;

import com.lynn.wristband.R;
import com.lynn.wristband.adapter.RegisterPagerAdapter;
import com.lynn.wristband.base.FullScreenBaseActivity;
import com.lynn.wristband.view.SliderViewPagerIndicator;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zowee-laisc on 2018/7/16.
 */

public class RegisterPagerActivity extends FullScreenBaseActivity {

    @BindView(R.id.register_pager)
    ViewPager mPager;
    @BindView(R.id.next_button)
    Button mNextButton;

    @BindView(R.id.indicator_pager)
    SliderViewPagerIndicator mIndicator;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initPager();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_register_pager;
    }

    private void initPager() {
        mPager.setAdapter(new RegisterPagerAdapter(RegisterPagerActivity.this));
        mIndicator.setViewPager(mPager);
    }
}
