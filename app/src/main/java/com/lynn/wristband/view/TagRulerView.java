package com.lynn.wristband.view;

import android.content.Context;
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


    private static final int TAG_TEXT_SIZE = 80;
    private static final int TAG_UNIT_TEXT_SIZE = 28;


    private static final int CURRENT_RADIUS = 10;


    private Paint mTagValuePaint;
    private Paint mTagUnitPaint;

    private boolean isVertical = true;
    private RulerDrawHelper mHelper;


    public TagRulerView(Context context) {
        this(context, null);
    }

    public TagRulerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaints();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (mHelper == null) {
            mHelper = new RulerDrawHelper(getWidth(), getHeight(), 1, 5,
                    getResources().getColor(R.color.colorLineGrey), isVertical);

        }
        mHelper.caculateXY(getWidth(), getHeight());

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mHelper == null) {
            mHelper = new RulerDrawHelper(getWidth(), getHeight(), 1, 5,
                    getResources().getColor(R.color.colorLineGrey), isVertical);
        }

        if (isVertical) {
            mHelper.drawRulerVertical(canvas, 170);
        } else {
            mHelper.drawRulerHorizontal(canvas, 67);
        }


    }


    private void initPaints() {


        mTagValuePaint = new Paint();
        mTagValuePaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mTagValuePaint.setColor(getResources().getColor(R.color.colorDark));
        mTagValuePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mTagValuePaint.setTextSize(TAG_TEXT_SIZE);

        mTagUnitPaint = new Paint();
        mTagUnitPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mTagUnitPaint.setColor(getResources().getColor(R.color.colorDark));
        mTagUnitPaint.setStyle(Paint.Style.STROKE);
        mTagUnitPaint.setTextSize(TAG_UNIT_TEXT_SIZE);


    }
}

