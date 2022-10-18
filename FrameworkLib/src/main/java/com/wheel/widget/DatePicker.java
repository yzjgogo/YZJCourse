package com.wheel.widget;


import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.lidroid.xutils.R;
import com.wheel.OnWheelChangedListener;
import com.wheel.WheelView;
import com.wheel.adapters.NumericWheelAdapter;

import java.util.Calendar;


/**
 * Created by 任少东 on 2016/6/4 0004.16:12
 */
public class DatePicker extends LinearLayout implements OnWheelChangedListener {

    private final int DEFAULT_MIN_YEAR = 1900;
    private final int DEFAULT_MAX_YEAR = 2100;
    private Context context;
    private WheelView year;
    private WheelView month;
    private WheelView day;

    private int minYear;

    public DatePicker(Context context) {
        super(context);
        init(context);
    }

    public DatePicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DatePicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.layout_date, this);
        year = (WheelView) findViewById(R.id.date_year);
        month = (WheelView) findViewById(R.id.date_month);
        day = (WheelView) findViewById(R.id.date_day);
        month.setCyclic(true);
        day.setCyclic(true);

        setYearRange(DEFAULT_MIN_YEAR, DEFAULT_MAX_YEAR);
        month.setViewAdapter(new NumericWheelAdapter(context, 1, 12));

        year.addChangingListener(this);
        month.addChangingListener(this);
        setToday();
    }

    private void changeDay(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        day.setViewAdapter(new NumericWheelAdapter(context, 1, maxDay));
        int cur = Math.min(maxDay, day.getCurrentItem() + 1);
        day.setCurrentItem(cur - 1, true);
    }

    @Override
    public void onChanged(WheelView wheel, int oldValue, int newValue) {
        int y = minYear + year.getCurrentItem();
        int m = month.getCurrentItem() + 1;
        changeDay(y, m);
    }

    public void setYearRange(int minYear, int maxYear) {
        this.minYear = minYear;
        year.setViewAdapter(new NumericWheelAdapter(context, minYear, maxYear));
    }

    public void setCurrent(int y, int m, int d) {
        year.setCurrentItem(y - minYear);
        month.setCurrentItem(m - 1);
        day.setCurrentItem(d - 1);
    }

    public void setToday() {
        Calendar calendar = Calendar.getInstance();
        int y = calendar.get(Calendar.YEAR);
        int m = calendar.get(Calendar.MONTH) + 1;
        int d = calendar.get(Calendar.DAY_OF_MONTH);
        setCurrent(y, m, d);
    }

    public int[] getSelect() {
        int[] date = new int[3];
        date[0] = year.getCurrentItem() + minYear;
        date[1] = month.getCurrentItem() + 1;
        date[2] = day.getCurrentItem() + 1;
        return date;
    }

}
