package com.lynn.wristband.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lynn.wristband.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zowee-laisc on 2018/7/2.
 */

public class RegisterPagerAdapter extends PagerAdapter {
    private List<View> mList;
    private Context mContext;

    public RegisterPagerAdapter(Context context) {
        this.mContext = context;
        initViewList();
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        container.addView(mList.get(position));
        return mList.get(position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(mList.get(position));
    }

    private void initViewList() {
        mList = new ArrayList<>();

        LayoutInflater inflater = LayoutInflater.from(mContext);
        View inflate = null;
        for (int i = 0; i < 5; i++) {
            if (i == 0) {
                inflate = inflater.inflate(R.layout.register_gender, null, false);
            } else if (i == 1) {
                inflate = inflater.inflate(R.layout.register_birth, null, false);
            } else if (i == 2) {
                inflate = inflater.inflate(R.layout.register_stature, null, false);
            } else if (i == 3) {
                inflate = inflater.inflate(R.layout.register_weight, null, false);
            } else {
                inflate = inflater.inflate(R.layout.register_target, null, false);
            }

            mList.add(inflate);

        }

    }


}
