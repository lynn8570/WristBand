package com.lynn.wristband.utils;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

/**
 * Created by zowee-laisc on 2018/7/20.
 */

public abstract class DrawHelperBase implements DrawHelper {
    public static final int LINE_LENGTH_LONG = 70;
    public static final int LINE_LENGTH_SHORT = 50;
    protected static final int LINE_WIDTH = 2;
    protected static final int LINE_SPACE = 10;
    protected static final int RULER_TEXT_SIZE = 20;
    protected static final int CURRENT_RADIUS = 10;
    protected static final int TAG_TEXT_SIZE = 80;

    protected int mPerValue;
    protected int mSpaceInEach;
    protected Paint mLinePaint;
    protected Paint mRulerTextPaint;
    protected Paint mCurPaint;


    protected int startLine;
    protected int startText;
    protected int middle;
    protected int halfOftotalNumber;
    protected int offset;

    protected int mMaxValue, mMinValue;

    public DrawHelperBase(int perValue, int spaceInEach,
                          int lineColor, int primaryColor) {
        this.mPerValue = perValue;
        this.mSpaceInEach = spaceInEach;
//        this.isVertical = isVertical;
//        this.mHeight = height;
//        this.mWidth = width;

        mLinePaint = new Paint();
        mLinePaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mLinePaint.setColor(lineColor);
        mLinePaint.setStyle(Paint.Style.STROKE);


        mRulerTextPaint = new Paint();
        mRulerTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mRulerTextPaint.setColor(lineColor);
        mRulerTextPaint.setStyle(Paint.Style.STROKE);
        mRulerTextPaint.setTextSize(RULER_TEXT_SIZE);

        mCurPaint = new Paint();
        mCurPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mCurPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mCurPaint.setStrokeWidth(LINE_WIDTH);
        mCurPaint.setColor(primaryColor);
        mCurPaint.setTextSize(TAG_TEXT_SIZE);


    }

    @Override
    public void drawRuler(Canvas canvas, int curValue) {
        drawRuler(canvas, startLine, middle, curValue);
    }

    @Override
    public int getOffset() {
//        (mMinValue - mSelectorValue) / mPerValue * mLineSpaceWidth * 10;
        Log.i("linlian", "getOffset mPerValue" + mPerValue + " halfOftotalNumber" + halfOftotalNumber
                + " middle" + middle);
        offset = middle / (mPerValue * halfOftotalNumber) / 10;
        return this.offset;
    }

    @Override
    public void setRange(int maxValue, int minValue) {
        this.mMaxValue = maxValue;
        this.mMinValue = minValue;

    }

    private void drawRuler(Canvas canvas, int start, int middle, int curValue) {
        //中间位置横线
        drawRulerLineAt(canvas, start, middle, curValue);
        //画上下
        int tempValue = 0;// 当前画的值
        for (int i = 1; i <= halfOftotalNumber; i++) {
            //往下，值小
            tempValue = curValue - mPerValue * i;
            drawRulerLineAt(canvas, start,
                    updatePosition(middle, i, false), tempValue);

            //往上，值大
            tempValue = curValue + mPerValue * i;
            drawRulerLineAt(canvas, start,
                    updatePosition(middle, i, true), tempValue);

        }

        //画蓝线，当前选定值圆点
        drawCurValueCircle(canvas);
    }


    protected abstract int updatePosition(int middle, int i, boolean getUper);

    protected abstract void drawCurValueCircle(Canvas canvas);


    protected abstract void drawRulerLineAt(Canvas canvas, int start, int middle, int curValue);


    protected boolean isLongLine(int value) {
        int remainder = value % (mPerValue * mSpaceInEach);
        return remainder == 0;//
    }

    /**
     * 根据当前值是否整除来判断是长线还是短线,返回相应长度
     *
     * @param value
     * @return
     */
    protected int getLineLength(int value) {
        if (isLongLine(value)) {
            return LINE_LENGTH_LONG;
        }
        return LINE_LENGTH_SHORT;
    }

    public boolean isInRange(int value) {//在范围内
        if (mMinValue < mMaxValue) {
            return value >= mMinValue && value <= mMaxValue;
        }
        return true;
    }
}
