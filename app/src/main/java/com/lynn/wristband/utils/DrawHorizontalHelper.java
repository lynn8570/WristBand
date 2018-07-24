package com.lynn.wristband.utils;

import android.graphics.Canvas;

/**
 * Created by zowee-laisc on 2018/7/20.
 */

public class DrawHorizontalHelper extends DrawHelperBase {


    public DrawHorizontalHelper(int perValue, int spaceInEach, int lineColor, int primaryColor) {
        super(perValue, spaceInEach, lineColor, primaryColor);
    }

    @Override
    protected int updatePosition(int middle, int i, boolean getUper) {
        if (getUper) {
            return middle - LINE_WIDTH / 2 + (LINE_SPACE + LINE_WIDTH) * i;
        } else {
            return middle + LINE_WIDTH / 2 - (LINE_SPACE + LINE_WIDTH) * i;
        }

    }

    @Override
    public void calculateXY(int width, int height) {
        middle = width / 2;
        startLine = height - height / 2;//从3/4处，从下往上画竖线
        startText = startLine + LINE_LENGTH_SHORT;//
        halfOftotalNumber = (width / 2 - LINE_WIDTH / 2) / (LINE_WIDTH + LINE_SPACE);
    }


    @Override
    protected void drawCurValueCircle(Canvas canvas) {
        canvas.drawLine(middle, startLine - LINE_LENGTH_LONG, middle, startLine, mCurPaint);
        canvas.drawCircle(middle, startLine - LINE_LENGTH_LONG - CURRENT_RADIUS, CURRENT_RADIUS,
                mCurPaint);
    }


    @Override
    protected void drawRulerLineAt(Canvas canvas, int start, int middle, int curValue) {
        drawLine(canvas, middle, start, getLineLength(curValue));
        drawText(canvas, curValue, middle);
    }

    protected void drawText(Canvas canvas, int value, int start) {
        if (isLongLine(value) && isInRange(value)) {
            canvas.drawText(String.valueOf(value), start - RULER_TEXT_SIZE, startText, mRulerTextPaint);
        }
    }

    private void drawLine(Canvas canvas, int startX, int startY, int length) {
        canvas.drawLine(startX, startY - length, startX, startY, mLinePaint);
    }

}
