package com.lynn.wristband.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
    @BindView(R.id.txt_register)
    TextView mTextRegister;
    @BindView(R.id.indicator_pager)
    SliderViewPagerIndicator mIndicator;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        init();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_register_pager;
    }

    private void init() {
        mPager.setAdapter(new RegisterPagerAdapter(RegisterPagerActivity.this));
        mIndicator.setViewPager(mPager);

        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                switch (position) {
                    case 0:
                        updateTitle(R.string.register_gender);
                        break;
                    case 1:
                        updateTitle(R.string.register_birth);
                        break;
                    case 2:
                        updateTitle(R.string.register_stature);
                        break;
                    case 3:
                        updateTitle(R.string.register_weight);
                        break;
                    case 4:
                        updateTitle(R.string.register_target);
                        break;

                }
                if (position == 4) {
                    mNextButton.setText(getString(R.string.register_finish));
                } else {
                    mNextButton.setText(getString(R.string.register_next));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPager.getCurrentItem() == 4) {
                    startMainActivity();
                } else {
                    mPager.setCurrentItem(mPager.getCurrentItem() + 1, true);
                }

            }
        });
    }

    private void startMainActivity() {
        Intent startMainActivity = new Intent(RegisterPagerActivity.this, MainActivity.class);
        startActivity(startMainActivity);
        RegisterPagerActivity.this.finish();
    }

    private void updateTitle(int resId) {
        mTextRegister.setText(getString(resId));
    }
}
