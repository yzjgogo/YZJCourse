package com.yin.yzjcourse.DiyWidget;

import android.content.Context;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.yin.yzjcourse.utils.DLog;

public class MyImageViewInGroup extends AppCompatImageView {
    public MyImageViewInGroup(Context context) {
        super(context);
    }

    public MyImageViewInGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyImageViewInGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        boolean result = super.dispatchTouchEvent(event);
        DLog.eLog("执行-MyImageViewInGroup-的dispatchTouchEvent："+result+",可用："+isEnabled()+",可点击"+isClickable());
        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean result = super.onTouchEvent(event);
        DLog.eLog("执行-MyImageViewInGroup-的onTouchEvent："+result);
        return result;
    }
}
