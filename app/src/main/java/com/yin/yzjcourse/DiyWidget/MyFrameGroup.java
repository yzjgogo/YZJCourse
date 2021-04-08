package com.yin.yzjcourse.DiyWidget;

import android.content.Context;
import androidx.appcompat.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

public class MyFrameGroup extends FrameLayout {
    public MyFrameGroup(Context context) {
        super(context);
    }

    public MyFrameGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyFrameGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }
}
