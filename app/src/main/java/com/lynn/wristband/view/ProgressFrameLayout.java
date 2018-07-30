package com.lynn.wristband.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.lynn.wristband.R;

public class ProgressFrameLayout extends FrameLayout {

    private FrameLayout mFrameLayout;
    private ImageView mImageviewBg;
    private TargetProgressBar mTargetProgressBar;
    private TextView mTextSteps;
    private TextView mTextTarget;

    private boolean changeColor;

    public ProgressFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ProgressFrameLayout);
        changeColor = typedArray.getBoolean(R.styleable.ProgressFrameLayout_themeWhite, false);


        init(context);
        updateTheme();
    }

    private void init(Context context) {
        mFrameLayout = (FrameLayout) LayoutInflater.from(context).inflate(R.layout.framelayout_progress_header, ProgressFrameLayout.this);

        mImageviewBg = mFrameLayout.findViewById(R.id.image_bg);
        mTargetProgressBar = mFrameLayout.findViewById(R.id.target_progress_bar);
        mTextSteps = mFrameLayout.findViewById(R.id.text_steps);
        mTextTarget = mFrameLayout.findViewById(R.id.text_target);
    }

    private void updateTheme() {
        if (changeColor) {
            Resources resources = getContext().getResources();
            mFrameLayout.setBackgroundColor(resources.getColor(R.color.colorWhite));
            mFrameLayout.setBackground(null);
            mImageviewBg.setImageDrawable(resources.getDrawable(R.drawable.progress_circle_blue));
            mTextSteps.setTextColor(resources.getColor(R.color.colorWhite));
            mTextTarget.setTextColor(resources.getColor(R.color.colorWhite));
        }
    }
}
