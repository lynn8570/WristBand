package com.lynn.wristband.activity;

import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.lynn.wristband.R;
import com.lynn.wristband.base.FullScreenBaseActivity;
import com.lynn.wristband.fragment.ExerciseFragment;
import com.lynn.wristband.fragment.SettingsFragment;
import com.lynn.wristband.fragment.StatusFragment;
import com.lynn.wristband.view.ProgressFrameLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends FullScreenBaseActivity {

    @BindView(R.id.frameLayout)
    FrameLayout frameLayout;
    @BindView(R.id.rg_main)
    RadioGroup rgMain;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.app_bar)
    AppBarLayout mAppBarLayout;

    @BindView(R.id.progress_header)
    ProgressFrameLayout progressFrameLayout;


    private ArrayList<Fragment> fragments;

    private Fragment mCurFragment;

    private int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initFragment();

        initHeader();

    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }


    private void initHeader() {
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                int totalScrollRange = appBarLayout.getTotalScrollRange();
                Log.i("linlian", "onOffsetChanged totalScrollRange=" + totalScrollRange);
                Log.i("linlian", "onOffsetChanged verticalOffset=" + verticalOffset);
                float alpha =  Math.abs((float) verticalOffset) / (float) totalScrollRange;
                Log.i("linlian", "onOffsetChanged alpha=" + alpha);

                mToolbar.setAlpha(alpha);
                progressFrameLayout.setAlpha(1 - alpha);

            }
        });
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new StatusFragment());
        fragments.add(new ExerciseFragment());
        fragments.add(new SettingsFragment());

        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_status:
                        position = 0;
                        break;
                    case R.id.rb_exercise:
                        position = 1;
                        break;
                    case R.id.rb_settings:
                        position = 2;
                        break;
                }
                Fragment fragment = getFragment(position);
                switchToFragment(fragment);
            }
        });

        rgMain.check(R.id.rb_status);
    }

    private void switchToFragment(Fragment nextFragment) {
        if (mCurFragment != nextFragment) {
            if (nextFragment != null) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                if (mCurFragment != null) {
                    ft.hide(mCurFragment);
                }
                if (!nextFragment.isAdded()) {
                    ft.add(R.id.frameLayout, nextFragment).commit();
                } else {

                    ft.show(nextFragment).commit();
                }
            }
            mCurFragment = nextFragment;
        }

    }

    private Fragment getFragment(int position) {
        if (fragments != null && fragments.size() > 0) {
            Fragment baseFragment = fragments.get(position);
            return baseFragment;
        }
        return null;
    }

}
