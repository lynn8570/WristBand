package com.lynn.wristband.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.Scroller;
import android.widget.TextView;

import com.lynn.wristband.R;
import com.lynn.wristband.utils.DrawHelper;
import com.lynn.wristband.utils.DrawHelperBase;
import com.lynn.wristband.utils.DrawHorizontalHelper;
import com.lynn.wristband.utils.DrawHorizontalTimeHelper;
import com.lynn.wristband.utils.DrawVerticalHelper;

/**
 * Created by zowee-laisc on 2018/7/17.
 */


public class TagRulerView extends View {


    private boolean isVertical = true;


    private int mCurValue = 170;
    private int mMaxValue = 250;        // 设置的最大值
    private int mMinValue = 50;     // 设置的最小值
    private int mPerSpaceValue = 1;
    private int mSpaceInEach = 5;
    private int mRulerColor;
    private int mPrimaryColor;
    private boolean isTime = false;
    private DrawHelperBase mHelper;
    private RulerView.OnValueChangeListener mListener;

    private TextView mCurValueText;

    public TextView getCurValueText() {
        return mCurValueText;
    }

    public void setCurValueText(TextView mCurValueText) {
        this.mCurValueText = mCurValueText;
    }

    public TagRulerView(Context context) {
        this(context, null);
    }

    public TagRulerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        initAttr(context, attrs);
        prepareHelper();
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
        mMaxValue = typedArray.getInteger(R.styleable.TagRulerView_rulerMaxValue, 0);
        mMinValue = typedArray.getInteger(R.styleable.TagRulerView_rulerMinValue, 0);
        typedArray.recycle();

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mHelper.calculateXY(getMeasuredWidth(), getMeasuredHeight());

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mHelper.calculateXY(getWidth(), getHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isInRange(mCurValue)) {
            mHelper.drawRuler(canvas, mCurValue);
        }

    }

    private boolean isInRange(int value) {//在范围内
        if (mHelper != null) {
            return mHelper.isInRange(value);
        }
        return true;
    }

    private void prepareHelper() {
        if (mHelper == null) {
            if (isTime) {
                mHelper = new DrawHorizontalTimeHelper(mPerSpaceValue, mSpaceInEach,
                        mRulerColor, mPrimaryColor);
                return;
            }
            if (isVertical) {
                mHelper = new DrawVerticalHelper(mPerSpaceValue, mSpaceInEach,
                        mRulerColor, mPrimaryColor);
            } else {
                mHelper = new DrawHorizontalHelper(mPerSpaceValue, mSpaceInEach,
                        mRulerColor, mPrimaryColor);
            }
            mHelper.setRange(mMaxValue, mMinValue);
        }
    }

    private int mLastY, mMove;


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {

        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        int position;//
        if (isVertical) {
            position = (int) event.getY();
        } else {
            position = (int) event.getX();
        }


        Log.i("linlian", "onTouchEvent offset=" + mHelper.getOffset());
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mLastY = position;
                Log.i("linlian", "onTouchEvent ACTION_DOWN mLastY=" + mLastY);
                mMove = 0;
                break;
            case MotionEvent.ACTION_MOVE:
                mMove = (mLastY - position);
                int tempValue = mCurValue + mMove * mHelper.getOffset();
                setCurValue(tempValue);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                notifyValueChange();
                return true;
            default:
                break;
        }

        mLastY = position;
        return true;
    }

    private void notifyValueChange() {
        if (null != mListener) {
            mListener.onValueChange(mCurValue);
        }
    }

    private void updateText() {
        if (mCurValueText != null) {
            mCurValueText.setText(String.valueOf(mCurValue));
        }
    }

    /**
     * 滑动后的回调
     */
    public interface OnValueChangeListener {
        void onValueChange(float value);
    }

    public int getCurValue() {
        return mCurValue;
    }

    public void setCurValue(int curValue) {
        if (isInRange(curValue)) {
            this.mCurValue = curValue;
            invalidate();
            updateText();
        }

    }

}

