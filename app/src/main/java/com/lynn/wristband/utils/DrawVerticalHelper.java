package com.lynn.wristband.utils;

import android.graphics.Canvas;

/**
 * Created by zowee-laisc on 2018/7/20.
 */

public class DrawVerticalHelper extends DrawHelperBase {


    public DrawVerticalHelper( int perValue, int spaceInEach, int lineColor, int primaryColor) {
        super( perValue, spaceInEach, lineColor, primaryColor);
    }

    @Override
    protected int updatePosition(int middle, int i, boolean getUper) {
        if (!getUper) {//降序排列
            return middle - LINE_WIDTH / 2 + (LINE_SPACE + LINE_WIDTH) * i;
        } else {
            return middle + LINE_WIDTH / 2 - (LINE_SPACE + LINE_WIDTH) * i;
        }

    }

    @Override
    protected void drawCurValueCircle(Canvas canvas) {
        canvas.drawLine(startLine, middle, startLine + LINE_LENGTH_LONG, middle, mCurPaint);
        canvas.drawCircle(startLine + LINE_LENGTH_LONG + CURRENT_RADIUS,
                middle, CURRENT_RADIUS, mCurPaint);
    }

    @Override
    public void calculateXY(int width, int height) {
        middle = height / 2; //当前值的中心y
        startLine = width / 2;//line 起始x
        startText = startLine - LINE_LENGTH_SHORT;//标尺值左边偏移一个短线长度
        halfOftotalNumber = (height / 2 - LINE_WIDTH / 2) / (LINE_WIDTH + LINE_SPACE);
    }

    @Override
    protected void drawRulerLineAt(Canvas canvas, int start, int middle, int curValue) {
        drawLine(canvas, start, middle, getLineLength(curValue));
        drawText(canvas, curValue, middle);
    }

    private void drawText(Canvas canvas, int value, int start) {
        if (isLongLine(value)) {
            canvas.drawText(String.valueOf(value), startText, start + RULER_TEXT_SIZE / 4, mRulerTextPaint);
        }
    }

    private void drawLine(Canvas canvas, int startX, int startY, int length) {
        canvas.drawLine(startX, startY, startX + length, startY, mLinePaint);
    }


}
