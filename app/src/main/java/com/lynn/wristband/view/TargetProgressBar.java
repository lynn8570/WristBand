package com.lynn.wristband.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.lynn.wristband.R;

public class TargetProgressBar extends View {


    private Context mContext;

    private Paint mBackPaint, mFrontPaint;
    private RectF mRect;

    private int mStrokeWidth = 4;
    private int mRadius = 100;
    private int mProgress = 50;

    public TargetProgressBar(Context context) {
        this(context, null);
    }

    public TargetProgressBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();

    }

    public void updateProgress(int progress) {
        this.mProgress = progress;
        invalidate();
    }

    private void init() {
        mBackPaint = new Paint();
        mBackPaint.setColor(mContext.getResources().getColor(R.color.colorPrimaryDark));
        mBackPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mBackPaint.setStyle(Paint.Style.STROKE);
        mBackPaint.setStrokeWidth(mStrokeWidth);

        mFrontPaint = new Paint();
        mFrontPaint.setColor(mContext.getResources().getColor(R.color.colorProgress));
        mFrontPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mFrontPaint.setStyle(Paint.Style.STROKE);
        mFrontPaint.setStrokeWidth(mStrokeWidth);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        updateRadius();
    }

    private void updateRadius() {
        mRadius = getWidth() / 2 - mStrokeWidth;
        initRect();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawCircle(canvas, mBackPaint);
        drawProgress(canvas, mProgress, mFrontPaint);
    }

    private void drawProgress(Canvas canvas, int progress, Paint paint) {
        float angle = progress / (float) 100 * 360;
        canvas.drawArc(mRect, -90, angle, false, paint);

    }

    private void drawCircle(Canvas canvas, Paint paint) {
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, mRadius, paint);
    }

    private void initRect() {
        if (mRect == null) {
            mRect = new RectF();
            int viewSize = (int) (mRadius * 2);
            int left = (getWidth() - viewSize) / 2;
            int top = (getHeight() - viewSize) / 2;
            int right = left + viewSize;
            int bottom = top + viewSize;
            mRect.set(left, top, right, bottom);
        }
    }

}
