package com.yin.yzjcourse.DiyWidget;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.yin.yzjcourse.utils.DLog;

public class MyRelativeInGroup extends AppCompatImageView {
    public MyRelativeInGroup(Context context) {
        super(context);
    }

    public MyRelativeInGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRelativeInGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        boolean result = super.dispatchTouchEvent(event);
        DLog.eLog("执行*MyRelativeInGroup*的dispatchTouchEvent："+result+",可用："+isEnabled()+",可点击"+isClickable());
        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean result = super.onTouchEvent(event);
        DLog.eLog("执行*MyRelativeInGroup*的onTouchEvent："+result);
        return result;
    }
}
