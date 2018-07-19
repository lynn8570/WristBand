package com.lynn.wristband.view;

import android.content.Context;
import android.content.res.TypedArray;
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

    public static final int INDICATOR_PADDING = 10;
    public static final int TEXT_SIZE_WIDTH = 22;
    public static final int LINE_STROKE_WIDTH = 4;
    public static final int TEXT_BASE_LINE = 2;
    private int indicatorColor;
    private int foregroundColor;
    private Paint mIndicatorPaint;
    private Paint mCurIndicatorPaint;
    private Paint mTextPaint;
    private Paint mCurTextPaint;
    private Paint mLinePaint;

    private ViewPager mViewPager;
    private int radius = 10;
    private int mCurPosition = 0;
    private int mWidth;
    private int mHeight;

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
        initAttr(context, attributeSet);
        initPaints();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        Log.i("linlian", "onMeasure widthMode =" + widthMode + " width=" + width);
        Log.i("linlian", "onMeasure heightMode =" + heightMode + " height=" + height);
        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            //if size was set to wrap_content
            calculateSizeByRadius(radius);
            setMeasuredDimension(mWidth, mHeight);
        } else if (widthMode == MeasureSpec.EXACTLY) {
            calculateRadiusByWidth(width);
        }


    }

    private void calculateSizeByRadius(int indicatorRadius) {
        Log.i("linlian", "calculateSizeByRadius calculateSizeByRadius =" + indicatorRadius);
        mWidth = INDICATOR_PADDING * 2 + indicatorRadius * 2 * getCount() + indicatorRadius * (getCount() - 1) * 2;
        mHeight = INDICATOR_PADDING * 2 + indicatorRadius * 2;
        Log.i("linlian", "calculateSizeByRadius calculateSizeByRadius =" + mWidth + " mheight=" + mHeight);
    }

    private void calculateRadiusByWidth(int width) {
        int count = getCount();
        if (count != 0) {
            radius = ((width - INDICATOR_PADDING * 2 - count * LINE_STROKE_WIDTH * 2) / (count * 4 - 2));
        }
        int calculateTestWidth = INDICATOR_PADDING * 2 + count * 2 * radius + (count - 1) * 2 * radius + LINE_STROKE_WIDTH * count * 2;

        Log.i("linlian", "radius =" + radius);
        Log.i("linlian", "calculateTestWidth =" + calculateTestWidth);
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        Log.i("linlian", "changed=" + changed + " left=" + left
                + " top" + top + " right=" + right + " b ottom=" + bottom);
        super.onLayout(changed, left, top, right, bottom);
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

    private void initAttr(Context context, AttributeSet attributeSet) {
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.SliderViewPagerIndicator);
        indicatorColor = getResources().getColor(R.color.colorPrimary);
        foregroundColor = getResources().getColor(R.color.colorWhite);
        indicatorColor = typedArray.getColor(R.styleable.SliderViewPagerIndicator_indicatorColor, indicatorColor);
        foregroundColor = typedArray.getColor(R.styleable.SliderViewPagerIndicator_textColor, foregroundColor);
        radius = (int) typedArray.getDimension(R.styleable.SliderViewPagerIndicator_indicatorRadius, 10);
        typedArray.recycle();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.i("linlian", "onSizeChanged w=" + w + " h=" + h);

        Log.i("linlian", "onSizeChanged ", new RuntimeException());

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

        int startX = INDICATOR_PADDING;
        Log.i("linlian", "startX =" + startX);
        Log.i("linlian", "getTop() =" + getTop());
        for (int i = 0; i < count; i++) {
            if (i == mCurPosition) {
                drawCircle(canvas, startX + radius, radius + INDICATOR_PADDING + LINE_STROKE_WIDTH, radius, mCurIndicatorPaint);
                drawText(canvas, startX, i + 1, mCurTextPaint);
            } else {
                drawCircle(canvas, startX + radius , radius + INDICATOR_PADDING + LINE_STROKE_WIDTH, radius, mIndicatorPaint);
                drawText(canvas, startX, i + 1, mTextPaint);
            }

            startX = startX + radius * 2;
            if (i < (count - 1)) {
                drawLine(canvas, startX, startX + radius * 2, mLinePaint);
                startX = startX + radius * 2;
            }

        }
    }


    private int getCount() {
        if (mViewPager == null) return 0;
        PagerAdapter adapter = mViewPager.getAdapter();
        if (adapter == null) return 0;
        return adapter.getCount();
    }

    private void drawText(Canvas canvas, int startX, int count, Paint paint) {
        if (canvas != null) {
            canvas.drawText(String.valueOf(count), startX + LINE_STROKE_WIDTH + radius - TEXT_SIZE_WIDTH / 2,
                    INDICATOR_PADDING + radius + LINE_STROKE_WIDTH + TEXT_SIZE_WIDTH / 4 + TEXT_BASE_LINE, paint);
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
            canvas.drawLine(startX, radius + INDICATOR_PADDING + LINE_STROKE_WIDTH, stopX, radius + INDICATOR_PADDING + LINE_STROKE_WIDTH, paint);
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
        mIndicatorPaint.setStrokeWidth(LINE_STROKE_WIDTH);

        mCurIndicatorPaint = new Paint();
        mCurIndicatorPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mCurIndicatorPaint.setColor(indicatorColor);
        mCurIndicatorPaint.setStyle(Paint.Style.STROKE);
        mCurIndicatorPaint.setStrokeWidth(LINE_STROKE_WIDTH);


        mLinePaint = new Paint();
        mLinePaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mLinePaint.setColor(indicatorColor);
        mLinePaint.setStyle(Paint.Style.STROKE);
        mLinePaint.setStrokeWidth(LINE_STROKE_WIDTH);

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
