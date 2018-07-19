package com.lynn.wristband.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.lynn.wristband.R;
import com.lynn.wristband.utils.RulerDrawHelper;

/**
 * Created by zowee-laisc on 2018/7/17.
 */


public class TagRulerView extends View {

    private Paint mTagValuePaint;
    private Paint mTagUnitPaint;

    private boolean isVertical = true;
    private int mCurValue = 170;
    private int mPerSpaceValue = 1;
    private int mSpaceInEach = 5;
    private int mRulerColor;
    private RulerDrawHelper mHelper;


    public TagRulerView(Context context) {
        this(context, null);
    }

    public TagRulerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        initAttr(context, attrs);
        initPaints();
    }

    private void initAttr(Context context, AttributeSet attributeSet) {
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.TagRulerView);
        isVertical = typedArray.getBoolean(R.styleable.TagRulerView_isVertical, true);
        mCurValue = typedArray.getInteger(R.styleable.TagRulerView_curValue, 170);
        mPerSpaceValue = typedArray.getInteger(R.styleable.TagRulerView_perSpaceValue, 1);
        mSpaceInEach = typedArray.getInteger(R.styleable.TagRulerView_spaceInEach, 5);
        mRulerColor = getResources().getColor(R.color.colorLineGrey);
        mRulerColor = typedArray.getColor(R.styleable.TagRulerView_rulerColer, mRulerColor);


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        prepareHelper();
        mHelper.caculateXY(getWidth(), getHeight());

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        prepareHelper();
        mHelper.drawRuler(canvas, mCurValue);


    }


    private void prepareHelper() {
        if (mHelper == null) {
            mHelper = new RulerDrawHelper(getWidth(), getHeight(), mPerSpaceValue, mSpaceInEach,
                    mRulerColor, getResources().getColor(R.color.colorPrimary), isVertical);
        }

    }

    private void initPaints() {


        mTagValuePaint = new Paint();
        mTagValuePaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mTagValuePaint.setColor(getResources().getColor(R.color.colorDark));
        mTagValuePaint.setStyle(Paint.Style.FILL_AND_STROKE);
//        mTagValuePaint.setTextSize(TAG_TEXT_SIZE);

        mTagUnitPaint = new Paint();
        mTagUnitPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mTagUnitPaint.setColor(getResources().getColor(R.color.colorDark));
        mTagUnitPaint.setStyle(Paint.Style.STROKE);
//        mTagUnitPaint.setTextSize(TAG_UNIT_TEXT_SIZE);


    }
}

