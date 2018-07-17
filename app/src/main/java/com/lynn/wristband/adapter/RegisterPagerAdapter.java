package com.lynn.wristband.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
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

        for (int i = 0; i < 3; i++) {
            ImageView imageView = new ImageView(mContext);
            LinearLayout linearLayout = new LinearLayout(mContext);
            if (i == 0) {
                inflater.inflate(R.layout.register_gender, linearLayout, true);
                mList.add(linearLayout);
            } else if (i == 1) {
                imageView.setImageResource(R.drawable.cat2);
                mList.add(imageView);
            } else {
                imageView.setImageResource(R.drawable.ghost);
                mList.add(imageView);
            }


        }

    }

}
