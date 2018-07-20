package com.lynn.wristband.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.lynn.wristband.R;
import com.lynn.wristband.utils.DrawHelper;
import com.lynn.wristband.utils.DrawHorizontalHelper;
import com.lynn.wristband.utils.DrawHorizontalTimeHelper;
import com.lynn.wristband.utils.DrawVerticalHelper;

/**
 * Created by zowee-laisc on 2018/7/17.
 */


public class TagRulerView extends View {


    private boolean isVertical = true;
    private int mCurValue = 170;
    private int mPerSpaceValue = 1;
    private int mSpaceInEach = 5;
    private int mRulerColor;
    private int mPrimaryColor;
    private boolean isTime = false;
    private DrawHelper mHelper;


    public TagRulerView(Context context) {
        this(context, null);
    }

    public TagRulerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        initAttr(context, attrs);
    }

    private void initAttr(Context context, AttributeSet attributeSet) {
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.TagRulerView);
        isVertical = typedArray.getBoolean(R.styleable.TagRulerView_isVertical, true);
        mCurValue = typedArray.getInteger(R.styleable.TagRulerView_curValue, 170);
        mPerSpaceValue = typedArray.getInteger(R.styleable.TagRulerView_perSpaceValue, 1);
        mSpaceInEach = typedArray.getInteger(R.styleable.TagRulerView_spaceInEach, 5);
        mRulerColor = typedArray.getColor(R.styleable.TagRulerView_rulerColor,
                getResources().getColor(R.color.colorLineGrey));
        mPrimaryColor = typedArray.getColor(R.styleable.TagRulerView_primaryColor,
                getResources().getColor(R.color.colorPrimary));
        isTime = typedArray.getBoolean(R.styleable.TagRulerView_isTime, false);
        typedArray.recycle();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        prepareHelper();
        mHelper.calculateXY(getWidth(), getHeight());

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        prepareHelper();
        mHelper.drawRuler(canvas, mCurValue);


    }


    private void prepareHelper() {
        if (mHelper == null) {
            if (isTime ) {
                mHelper = new DrawHorizontalTimeHelper(getWidth(), getHeight(), mPerSpaceValue, mSpaceInEach,
                        mRulerColor, mPrimaryColor);
                return;
            }
            if (isVertical) {
                mHelper = new DrawVerticalHelper(getWidth(), getHeight(), mPerSpaceValue, mSpaceInEach,
                        mRulerColor, mPrimaryColor);
            } else {
                mHelper = new DrawHorizontalHelper(getWidth(), getHeight(), mPerSpaceValue, mSpaceInEach,
                        mRulerColor, mPrimaryColor);
            }

        }

    }


}

