package com.lynn.wristband.utils;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import com.lynn.wristband.R;

/**
 * Created by zowee-laisc on 2018/7/18.
 */

public class RulerDrawHelper {
    public static final int LINE_LENGTH_LONG = 70;
    public static final int LINE_LENGTH_SHORT = 50;
    private static final int LINE_WIDTH = 2;
    private static final int LINE_SPACE = 10;
    private static final int RULER_TEXT_SIZE = 20;

    private int mPerValue;
    private int mSpaceInEach;
    private Paint mLinePaint;
    private Paint mRulerTextPaint;

    private boolean isVertical;


    /**
     * @param width       标尺整体宽
     * @param height      总高
     * @param perValue    每个间隔代表的值 一般为1
     * @param spaceInEach 每个长线之间间隔数，一般为5
     * @param color       标尺颜色
     * @param isVertical  值是否是纵向分布
     */
    public RulerDrawHelper(int width, int height, int perValue, int spaceInEach,
                           int color, boolean isVertical) {
        this.mPerValue = perValue;
        this.mSpaceInEach = spaceInEach;
        this.isVertical = isVertical;

        mLinePaint = new Paint();
        mLinePaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mLinePaint.setColor(color);
        mLinePaint.setStyle(Paint.Style.STROKE);
        mLinePaint.setStrokeWidth(LINE_WIDTH);

        mRulerTextPaint = new Paint();
        mRulerTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mRulerTextPaint.setColor(color);
        mRulerTextPaint.setStyle(Paint.Style.STROKE);
        mRulerTextPaint.setTextSize(RULER_TEXT_SIZE);

        caculateXY(width, height);
    }

    private int startLine;
    private int startText;
    private int middle;
    private int halfOftotalNumber;

    public void caculateXY(int width, int height) {
        if (isVertical) {//线是横线，值纵向分布
            middle = height / 2; //当前值的中心y
            startLine = width / 4;//line 起始x
            startText = startLine - LINE_LENGTH_SHORT;//标尺值左边偏移一个短线长度
            halfOftotalNumber = (height / 2 - LINE_WIDTH / 2) / (LINE_WIDTH + LINE_SPACE);
        } else {//line 是竖线，值横向分布
            middle = width / 2;
            startLine = height - height / 4;//从3/4处，从下往上画竖线
            startText = startLine + LINE_LENGTH_SHORT;//
            halfOftotalNumber = (width / 2 - LINE_WIDTH / 2) / (LINE_WIDTH + LINE_SPACE);
        }

    }

    public void drawRulerVertical(Canvas canvas, int curValue) {
        drawRulerVertical(canvas, startLine, middle, curValue);
    }

    private void drawText(Canvas canvas, int value, int start) {
        if (isLongLine(value)) {
            canvas.drawText(String.valueOf(value), startText, start, mRulerTextPaint);
        }
    }

    private void drawRulerVertical(Canvas canvas, int startX, int middleY, int curValue) {

        //中间位置横线
        drawLine(canvas, startX, middleY, getLineLength(curValue), false);
        drawText(canvas, curValue, middleY);
        //画上下
        int pY = 0;//从中间画起，py表示当前画的线 Y的位置
        int tempValue = 0;// 当前画的值
        for (int i = 1; i <= halfOftotalNumber; i++) {
            //往下，值小
            tempValue = curValue - mPerValue * i;
            pY = middleY - LINE_WIDTH / 2 + (LINE_SPACE + LINE_WIDTH) * i;
            drawLine(canvas, startX, pY, getLineLength(tempValue), false);
            drawText(canvas, tempValue, pY);

            //往上，值大
            tempValue = curValue + mPerValue * i;
            pY = middleY + LINE_WIDTH / 2 - (LINE_SPACE + LINE_WIDTH) * i;
            drawLine(canvas, startX, pY, getLineLength(tempValue), false);
            drawText(canvas, tempValue, pY);

        }

    }


    public void drawRulerHorizontal(Canvas canvas, int curValue) {

        drawRulerHorizontal(canvas, startLine, middle, curValue);
    }

    private void drawRulerHorizontal(Canvas canvas, int startY, int middleX, int curValue) {
        //中间位置横线
        drawLine(canvas, middleX, startY - getLineLength(curValue),
                getLineLength(curValue), true);
        drawText(canvas, curValue, middleX);
        //画左右
        int pX = 0;//从中间画起，py表示当前画的线 Y的位置
        int tempValue = 0;// 当前画的值
        for (int i = 1; i <= halfOftotalNumber; i++) {
            //往左，值小
            tempValue = curValue - mPerValue * i;
            pX = middleX - LINE_WIDTH / 2 + (LINE_SPACE + LINE_WIDTH) * i;
            drawLine(canvas, pX, startY - getLineLength(curValue), getLineLength(tempValue), true);
            drawText(canvas, tempValue, pX);
            //往右，值大
            tempValue = curValue + mPerValue * i;
            pX = middleX + LINE_WIDTH / 2 - (LINE_SPACE + LINE_WIDTH) * i;
            drawLine(canvas, pX, startY - getLineLength(curValue), getLineLength(tempValue), true);
            drawText(canvas, tempValue, pX);
        }

    }


    /**
     * 画线条
     *
     * @param canvas
     * @param startX     起始位置
     * @param startY     起始位置
     * @param length     线条长度
     * @param isVertical
     */
    private void drawLine(Canvas canvas, int startX, int startY, int length, boolean isVertical) {
        Log.i("linlian", "startX =" + startX + " startY=" + startY + " length=" + length);
        if (isVertical) {//竖线
            canvas.drawLine(startX, startY, startX, startY + length, mLinePaint);
        } else {//横线，
            canvas.drawLine(startX, startY, startX + length, startY, mLinePaint);
        }

    }

    private boolean isLongLine(int value) {
        int remainder = value % (mPerValue * mSpaceInEach);
        return remainder == 0;//
    }

    /**
     * 根据当前值是否整除来判断是长线还是短线,返回相应长度
     *
     * @param value
     * @return
     */
    private int getLineLength(int value) {
        if (isLongLine(value)) {
            return LINE_LENGTH_LONG;
        }
        return LINE_LENGTH_SHORT;
    }

}
