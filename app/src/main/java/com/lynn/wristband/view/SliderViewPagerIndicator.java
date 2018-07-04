package com.lynn.wristband.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.lynn.wristband.R;

/**
 * Created by zowee-laisc on 2018/7/2.
 */

public class SliderViewPagerIndicator extends View implements ViewPager.OnPageChangeListener {

    public static final int CIRCLE_PADDING_WIDTH = 10;
    public static final int TEXT_PADDING_WIDTH = 4;
    public static final int TEXT_SIZE_WIDTH = 28;
    public static final int LINEA_STROKE_WIDTH = 4;
    private int indicatorColor;
    private int foregroundColor;
    private Paint mIndicatorPaint;
    private Paint mCurIndicatorPaint;
    private Paint mTextPaint;
    private Paint mCurTextPaint;
    private Paint mLinePaint;

    private ViewPager mViewPager;


    private int radius = 10;
    private int textBaseLine = 10;

    private int mCurPosition = 0;

    public SliderViewPagerIndicator(Context context) {

        this(context, null);
    }

    public SliderViewPagerIndicator(Context context, AttributeSet attrs) {

        this(context, attrs, 0);
    }

    public SliderViewPagerIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attributeSet, int defStyleAttr) {

        Log.i("linlian", "init()");
        initAttr(context);
        initPaints();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        Log.i("linlian", "changed=" + changed + " left=" + left
                + " top" + top + " right=" + right + " bottom=" + bottom);
        super.onLayout(changed, left, top, right, bottom);
        getRadius();
    }

    public void setViewPager(ViewPager view) {
        if (mViewPager == view) {
            return;
        }

        if (view.getAdapter() == null) {
            throw new IllegalStateException("ViewPager does not have adapter instance.");
        }
        mViewPager = view;
        mViewPager.addOnPageChangeListener(this);
        invalidate();


    }

    private void initAttr(Context context) {
        indicatorColor = context.getResources().getColor(R.color.colorPrimary);
        foregroundColor = context.getResources().getColor(R.color.colorWhite);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.i("linlian", "onSizeChanged w=" + w + " h=" + h);

        getRadius();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Log.i("linlian", "onDraw width=" + getWidth());
        Log.i("linlian", "onDraw height=" + getHeight());

        drawIndicator(canvas);
    }

    private void drawIndicator(Canvas canvas) {

        int count = getCount();
        if (count == 0) return;
        Log.i("linlian", "count =" + getCount());

        int startX = CIRCLE_PADDING_WIDTH;
        Log.i("linlian", "startX =" + startX);
        Log.i("linlian", "getTop() =" + getTop());
        for (int i = 0; i < count; i++) {
            if (i == mCurPosition) {
                drawCircle(canvas, startX + radius, radius, radius, mCurIndicatorPaint);
                drawText(canvas, startX, i + 1, mCurTextPaint);
            } else {
                drawCircle(canvas, startX + radius, radius, radius, mIndicatorPaint);
                drawText(canvas, startX, i + 1, mTextPaint);
            }

            startX = startX + radius * 2;
            if (i < (count - 1)) {
                drawLine(canvas, startX, startX + radius * 2, mLinePaint);
                startX = startX + radius * 2;
            }

        }
    }

    private void getRadius() {
        int count = getCount();
        if (count != 0) {
            radius = ((getWidth() - CIRCLE_PADDING_WIDTH * 2) / count * 2 - 1) / 2;
            radius = getHeight() / 2 > radius ? radius : getHeight() / 2;
        }
        textBaseLine = radius - TEXT_SIZE_WIDTH / 2 + TEXT_PADDING_WIDTH;
        Log.i("linlian", "radius =" + radius);
        Log.i("linlian", "textBaseLine =" + textBaseLine);
    }

    private int getCount() {
        if (mViewPager == null) return 0;
        PagerAdapter adapter = mViewPager.getAdapter();
        if (adapter == null) return 0;
        return adapter.getCount();
    }

    private void drawText(Canvas canvas, int startX, int count, Paint paint) {
        if (canvas != null) {
            canvas.drawText(String.valueOf(count), startX + textBaseLine, radius * 2 - textBaseLine, paint);
        }
    }

    private void drawCircle(Canvas canvas, float centerX, float centerY, float radius,
                            Paint paint) {
        if (canvas != null) {
            canvas.drawCircle(centerX, centerY, radius, paint);
        }
    }


    private void drawLine(Canvas canvas, float startX, float stopX,
                          Paint paint) {
        if (canvas != null) {
            canvas.drawLine(startX, getHeight() / 2, stopX, getHeight() / 2, paint);
        }
    }


    private void initPaints() {
        mTextPaint = new Paint();
        mTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(foregroundColor);
        mTextPaint.setStyle(Paint.Style.STROKE);
        mTextPaint.setTextSize(TEXT_SIZE_WIDTH);

        mCurTextPaint = new Paint();
        mCurTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mCurTextPaint.setColor(indicatorColor);
        mCurTextPaint.setStyle(Paint.Style.STROKE);
        mCurTextPaint.setTextSize(TEXT_SIZE_WIDTH);


        mIndicatorPaint = new Paint();
        mIndicatorPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mIndicatorPaint.setColor(indicatorColor);
        mIndicatorPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mIndicatorPaint.setStrokeWidth(LINEA_STROKE_WIDTH);

        mCurIndicatorPaint = new Paint();
        mCurIndicatorPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mCurIndicatorPaint.setColor(indicatorColor);
        mCurIndicatorPaint.setStyle(Paint.Style.STROKE);
        mCurIndicatorPaint.setStrokeWidth(LINEA_STROKE_WIDTH);


        mLinePaint = new Paint();
        mLinePaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mLinePaint.setColor(indicatorColor);
        mLinePaint.setStyle(Paint.Style.STROKE);
        mLinePaint.setStrokeWidth(LINEA_STROKE_WIDTH);

    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (mCurPosition != position) {
            mCurPosition = position;
            invalidate();
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
