package com.wheel.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.lidroid.xutils.R;


/**
 * Created by 任少东 on 2016/6/6 0006.09:44
 */
public class DateTimePicker extends LinearLayout {
    private DatePicker datePicker;
    private TimePicker timePicker;

    public DateTimePicker(Context context) {
        super(context);
        init(context);
    }

    public DateTimePicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DateTimePicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_date_time, this);
        datePicker = (DatePicker) findViewById(R.id.date);
        timePicker = (TimePicker) findViewById(R.id.time);
    }

    public int[] getSelect() {
        int[] date = datePicker.getSelect();
        int[] time = timePicker.getSelect();
        int[] result = new int[5];
        result[0] = date[0];
        result[1] = date[1];
        result[2] = date[2];
        result[3] = time[0];
        result[4] = time[1];
        return result;
    }
}
