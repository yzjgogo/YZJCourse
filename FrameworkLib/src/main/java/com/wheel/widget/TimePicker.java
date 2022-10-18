package com.wheel.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.lidroid.xutils.R;
import com.wheel.OnWheelScrollListener;
import com.wheel.WheelView;
import com.wheel.adapters.NumericWheelAdapter;

import java.util.Calendar;


/**
 *
 */
public class TimePicker extends LinearLayout {
    private Context context;
    private WheelView hour;
    private WheelView minute;
    private int mHourNum;
    private int mMinute;
    private OnTimePickerListener mScrollListener;
    public TimePicker(Context context) {
        super(context);
        init(context);
    }

    public TimePicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TimePicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.layout_time, this);
        hour = (WheelView) findViewById(R.id.time_hour);
        minute = (WheelView) findViewById(R.id.time_minute);
        hour.setVisibleItems(3);//设置滚轮可见数量
        minute.setVisibleItems(3);
        hour.setViewAdapter(new NumericWheelAdapter(context, 0, 23, "%02d"));//不足2位的补零
        minute.setViewAdapter(new NumericWheelAdapter(context, 0, 59, "%02d"));
        hour.setCyclic(true);
        minute.setCyclic(true);
        hour.addScrollingListener(new OnWheelScrollListener() {
            @Override public void onScrollingStarted(WheelView wheel) {

            }

            @Override public void onScrollingFinished(WheelView wheel) {
                mHourNum = wheel.getCurrentItem();
                mScrollListener.onScrollingFinished(mHourNum, mMinute);
            }
        });
        minute.addScrollingListener(new OnWheelScrollListener() {
            @Override public void onScrollingStarted(WheelView wheel) {

            }

            @Override public void onScrollingFinished(WheelView wheel) {
                mMinute = wheel.getCurrentItem();
                mScrollListener.onScrollingFinished(mHourNum, mMinute);
            }
        });
        setNow();
    }

    public void setCurrent(int h, int m) {
        hour.setCurrentItem(h);
        minute.setCurrentItem(m);
    }

    public void setNow() {
        Calendar calendar = Calendar.getInstance();
        int h = calendar.get(Calendar.HOUR_OF_DAY);
        int m = calendar.get(Calendar.MINUTE);
        mHourNum = h < 10 ? Integer.valueOf("0" + h) : h;
        mMinute = m < 10 ? Integer.valueOf("0" + m) : m;
        setCurrent(h, m);
    }

    public int[] getSelect() {
        int[] time = new int[2];
        time[0] = hour.getCurrentItem();
        time[1] = hour.getCurrentItem();
        return time;
    }

    public void addScrollingListener(OnTimePickerListener listener){
        mScrollListener = listener;
    }

    public interface OnTimePickerListener {
        void onScrollingStarted(int hour, int minute);

        void onScrollingFinished(int hour, int minute);
    }
}
