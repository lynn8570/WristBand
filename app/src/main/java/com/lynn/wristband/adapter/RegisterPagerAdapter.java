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
import android.widget.TextView;

import com.lynn.wristband.R;
import com.lynn.wristband.view.TagRulerView;

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
                bindRulerAndText(inflate, R.id.ruler_view_stature, R.id.curValue);
            } else if (i == 3) {
                inflate = inflater.inflate(R.layout.register_weight, null, false);
                bindRulerAndText(inflate, R.id.ruler_view_weight, R.id.curValue);
            } else {
                inflate = inflater.inflate(R.layout.register_target, null, false);
                bindRulerAndText(inflate, R.id.ruler_view_steps, R.id.targt_steps);
                bindRulerTimeAndText(inflate,R.id.ruler_view_time,R.id.targt_time);
            }

            mList.add(inflate);

        }
    }

    /**
     * @param inflate
     * @param rulerViewId
     * @param textViewId
     */
    private void bindRulerTimeAndText(View inflate, int rulerViewId, int textViewId) {
        TagRulerView rulerview = inflate.findViewById(rulerViewId);
        final TextView textView = inflate.findViewById(textViewId);
        rulerview.setListener(new TagRulerView.OnValueChangeListener() {
            @Override
            public void onValueChange(int value) {
                int hour = value / 6;
                int minite = value % 6;
                textView.setText(hour + "小时" + minite * 10 + "分");
            }
        });
    }

    /**
     * @param inflate
     * @param rulerViewId
     * @param textViewId
     */
    private void bindRulerAndText(View inflate, int rulerViewId, int textViewId) {
        TagRulerView rulerview = inflate.findViewById(rulerViewId);
        TextView textView = inflate.findViewById(textViewId);
        rulerview.setCurValueText(textView);
    }
}
