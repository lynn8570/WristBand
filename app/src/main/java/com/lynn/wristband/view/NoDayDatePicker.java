package com.lynn.wristband.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.DatePicker;
import android.widget.NumberPicker;

import com.lynn.wristband.R;

import java.lang.reflect.Field;

/**
 * Created by zowee-laisc on 2018/7/17.
 */

public class NoDayDatePicker extends DatePicker {
    public NoDayDatePicker(Context context) {
        this(context, null);
    }

    public NoDayDatePicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        hidDay(this);
    }

    private void setSelectionDivider(NumberPicker numberPicker, Drawable drawable) {
        Field[] declaredFields = numberPicker.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            if ("mSelectionDivider".equals(field.getName())) {
                field.setAccessible(true);
                try {
                    field.set(numberPicker, drawable);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void hidDay(DatePicker mDatePicker) {
        Field[] datePickerfFields = mDatePicker.getClass().getDeclaredFields();
        for (Field datePickerField : datePickerfFields) {
            if ("mDaySpinner".equals(datePickerField.getName())) {
                datePickerField.setAccessible(true);
                Object dayPicker = new Object();
                try {
                    dayPicker = datePickerField.get(mDatePicker);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }
                // datePicker.getCalendarView().setVisibility(View.GONE);
                ((View) dayPicker).setVisibility(View.GONE);
            }
        }
        /**
         *   兼容于5.0以上版本
         */

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            int daySpinnerId = Resources.getSystem().getIdentifier("day", "id", "android");
            if (daySpinnerId != 0) {
                NumberPicker daySpinner = mDatePicker.findViewById(daySpinnerId);
                if (daySpinner != null) {
                    daySpinner.setVisibility(View.GONE);
                }
            }

            //get year
            int yearSpinnerId = Resources.getSystem().getIdentifier("year", "id", "android");
            if (yearSpinnerId != 0) {
                NumberPicker yearSpinner = mDatePicker.findViewById(yearSpinnerId);
                if (yearSpinner != null) {
                    setSelectionDivider(yearSpinner,new ColorDrawable(this.getResources().getColor(R.color.colorPrimary)));
                }
            }

            //get Month
            int monthSpinnerId = Resources.getSystem().getIdentifier("month", "id", "android");
            if (monthSpinnerId != 0) {
                NumberPicker monthSpinner = mDatePicker.findViewById(monthSpinnerId);
                if (monthSpinner != null) {
                    setSelectionDivider(monthSpinner,new ColorDrawable(this.getResources().getColor(R.color.colorPrimary)));
                }
            }
        }

    }


}
