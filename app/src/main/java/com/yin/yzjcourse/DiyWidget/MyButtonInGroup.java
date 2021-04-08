package com.yin.yzjcourse.DiyWidget;

import android.content.Context;
import androidx.appcompat.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.yin.yzjcourse.utils.DLog;

public class MyButtonInGroup extends AppCompatButton {
    public MyButtonInGroup(Context context) {
        super(context);
    }

    public MyButtonInGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyButtonInGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        boolean result = super.dispatchTouchEvent(event);
        DLog.eLog("执行MyButtonInGroup的dispatchTouchEvent："+result+",可用："+isEnabled()+",可点击"+isClickable());
        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean result = super.onTouchEvent(event);
        DLog.eLog("执行MyButtonInGroup的onTouchEvent："+result);
        return result;
    }
}
