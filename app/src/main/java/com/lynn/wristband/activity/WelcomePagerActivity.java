package com.lynn.wristband.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.lynn.wristband.R;
import com.lynn.wristband.adapter.SliderPagerAdapter;
import com.lynn.wristband.base.FullScreenBaseActivity;
import com.lynn.wristband.view.SliderViewPagerIndicator;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WelcomePagerActivity extends FullScreenBaseActivity {


    @BindView(R.id.welcome_pager)
    ViewPager mViewPager;
    @BindView(R.id.enter_button)
    Button mEnterButton;
    @BindView(R.id.indicator_pager)
    SliderViewPagerIndicator mIndicator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        initViewPager();

        mEnterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startMainActivity = new Intent(WelcomePagerActivity.this, MainActivity.class);
                startActivity(startMainActivity);
                WelcomePagerActivity.this.finish();
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_welcome_pager;
    }


    private void initViewPager() {
        mViewPager.setAdapter(new SliderPagerAdapter(this));
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 2) {
                    mEnterButton.setVisibility(View.VISIBLE);
                } else {
                    mEnterButton.setVisibility(View.GONE);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mIndicator.setViewPager(mViewPager);
    }

}
