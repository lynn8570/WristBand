package com.lynn.wristband.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.ContextMenu;
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

public class SliderPagerAdapter extends PagerAdapter {
    private List<View> mList;
    private Context mContext;

    public SliderPagerAdapter(Context context) {
        this.mContext = context;
        initViewList();
    }

    @Override
    public int getCount() {
        return 3;
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

        for (int i = 0; i < 3; i++) {
            ImageView imageView = new ImageView(mContext);
            if(i==0){
                imageView.setImageResource(R.drawable.cat);
            }else if(i==1){
                imageView.setImageResource(R.drawable.cat2);
            }else {
                imageView.setImageResource(R.drawable.ghost);
            }

            mList.add(imageView);
        }

    }

}
