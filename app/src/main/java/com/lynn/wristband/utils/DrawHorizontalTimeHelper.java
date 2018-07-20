package com.lynn.wristband.utils;

import android.graphics.Canvas;

/**
 * Created by zowee-laisc on 2018/7/20.
 * <p>
 * 1小时10 分 就是 7 这样换算
 * 8小时30分 就是 8*6 +3 = 51
 * 最大24小时 就是 24*6=144
 * 被6整除是整点
 */

public class DrawHorizontalTimeHelper extends DrawHorizontalHelper {


    public DrawHorizontalTimeHelper( int perValue, int spaceInEach, int lineColor, int primaryColor) {
        super( perValue, spaceInEach, lineColor, primaryColor);
    }


    @Override
    protected void drawText(Canvas canvas, int value, int start) {
        if (isLongLine(value)) {
            canvas.drawText(String.valueOf(value / 6), start - RULER_TEXT_SIZE / 2, startText, mRulerTextPaint);
        }
    }
}
