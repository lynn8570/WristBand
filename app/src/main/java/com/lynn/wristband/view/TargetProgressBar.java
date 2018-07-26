package com.lynn.wristband.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
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
    private Paint mBackPaint, mFrontPaint, mLinePaint;
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

        mLinePaint = new Paint();
        mLinePaint.setColor(mContext.getResources().getColor(R.color.colorPrimary));
        mLinePaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mLinePaint.setStyle(Paint.Style.STROKE);
        mLinePaint.setStrokeWidth(mStrokeWidth / 2);
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
        drawCircle(canvas);
        drawProgress(canvas, mProgress);
        drawLines(canvas);
    }

    private void drawProgress(Canvas canvas, int progress) {
        float angle = progress / (float) 100 * 360;
        canvas.drawArc(mRect, -90, angle, false, mFrontPaint);

    }

    private void drawCircle(Canvas canvas) {
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, mRadius, mBackPaint);
    }

    private void drawLines(Canvas canvas) {

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int radius = mRadius + mStrokeWidth / 2;
        float max = 360f / 100f * mProgress;
        for (int i = 0; i < max; i += 3) {
            double rad = Math.PI - i * Math.PI / 180;
            float startX = (float) (centerX + (radius - mStrokeWidth) * Math.sin(rad));
            float startY = (float) (centerY + (radius - mStrokeWidth) * Math.cos(rad));

            float stopX = (float) (centerX + radius * Math.sin(rad) + 1);
            float stopY = (float) (centerY + radius * Math.cos(rad) + 1);

            canvas.drawLine(startX, startY, stopX, stopY, mLinePaint);

        }
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
