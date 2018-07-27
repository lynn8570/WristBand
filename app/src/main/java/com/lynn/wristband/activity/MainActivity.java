package com.lynn.wristband.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import com.lynn.wristband.R;
import com.lynn.wristband.base.FullScreenBaseActivity;
import com.lynn.wristband.fragment.ExcerciseFragment;
import com.lynn.wristband.fragment.SettingsFragment;
import com.lynn.wristband.fragment.StatusFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends FullScreenBaseActivity {

    @BindView(R.id.frameLayout)
    FrameLayout frameLayout;
    @BindView(R.id.rg_main)
    RadioGroup rgMain;


    private ArrayList<Fragment> fragments;

    private Fragment mCurFragment;

    private int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initFragment();


    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new StatusFragment());
        fragments.add(new ExcerciseFragment());
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
