package com.lynn.wristband.utils;

import android.graphics.Canvas;

/**
 * Created by zowee-laisc on 2018/7/20.
 */

public interface DrawHelper {
    void calculateXY(int width, int height);

    void drawRuler(Canvas canvas, int curValue);
}
