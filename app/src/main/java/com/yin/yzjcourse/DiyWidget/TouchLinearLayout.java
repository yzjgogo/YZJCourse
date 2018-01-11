package com.yin.yzjcourse.DiyWidget;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.yin.yzjcourse.utils.DLog;

/**
 参考百度脑图：ViewGroup触摸事件
 */

public class TouchLinearLayout extends LinearLayout implements View.OnTouchListener,View.OnClickListener{
    public TouchLinearLayout(Context context) {
        super(context);
        DLog.eLog("TouchLinearLayout1");
        initView();
    }

    public TouchLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        DLog.eLog("TouchLinearLayout2");
        initView();
    }

    public TouchLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        DLog.eLog("TouchLinearLayout3");
        initView();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TouchLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        DLog.eLog("TouchLinearLayout4");
        initView();
    }

    private void initView() {
        setOnClickListener(this);
        setOnTouchListener(this);
//        requestDisallowInterceptTouchEvent(false);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        DLog.eLog("TouchLinearLayout的dispatchTouchEvent-- action=" + event.getAction());
        return super.dispatchTouchEvent(event);
    }

    @Override
    public void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        DLog.eLog("TouchLinearLayout的requestDisallowInterceptTouchEvent-- disallowIntercept=" + disallowIntercept);
        super.requestDisallowInterceptTouchEvent(disallowIntercept);
    }

    /**
        第一步：单击按钮
     TouchLinearLayout的dispatchTouchEvent-- action=0
     TouchLinearLayout的onInterceptTouchEvent-- action=0,result=false
     TouchButton的dispatchTouchEvent-- action=0
     TouchButton的onTouch-- action=0
     TouchButton的onTouchEvent-- action=0
     TouchLinearLayout的dispatchTouchEvent-- action=2
     TouchLinearLayout的onInterceptTouchEvent-- action=2,result=false
     TouchButton的dispatchTouchEvent-- action=2
     TouchButton的onTouch-- action=2
     TouchButton的onTouchEvent-- action=2
     TouchLinearLayout的dispatchTouchEvent-- action=1
     TouchLinearLayout的onInterceptTouchEvent-- action=1,result=false
     TouchButton的dispatchTouchEvent-- action=1
     TouchButton的onTouch-- action=1
     TouchButton的onTouchEvent-- action=1
     TouchButton的onClick--

     第二步：单击按钮以外的区域
     TouchLinearLayout的dispatchTouchEvent-- action=0
     TouchLinearLayout的onInterceptTouchEvent-- action=0,result=false
     TouchLinearLayout的onTouch-- action=0
     TouchLinearLayout的onTouchEvent-- action=0
     TouchLinearLayout的dispatchTouchEvent-- action=2
     TouchLinearLayout的onTouch-- action=2
     TouchLinearLayout的onTouchEvent-- action=2
     TouchLinearLayout的dispatchTouchEvent-- action=1
     TouchLinearLayout的onTouch-- action=1
     TouchLinearLayout的onTouchEvent-- action=1
     TouchLinearLayout的onClick--

     第三步：onInterceptTouchEvent返回true，单击按钮
     TouchLinearLayout的dispatchTouchEvent-- action=0
     TouchLinearLayout的onInterceptTouchEvent-- action=0,result=true
     TouchLinearLayout的onTouch-- action=0
     TouchLinearLayout的onTouchEvent-- action=0
     TouchLinearLayout的dispatchTouchEvent-- action=2
     TouchLinearLayout的onTouch-- action=2
     TouchLinearLayout的onTouchEvent-- action=2
     TouchLinearLayout的dispatchTouchEvent-- action=1
     TouchLinearLayout的onTouch-- action=1
     TouchLinearLayout的onTouchEvent-- action=1
     TouchLinearLayout的onClick--

     第四步：onInterceptTouchEvent返回true，单击按钮以外的区域，因为ViewGroup拦截了，所以效果好第三步一样
     TouchLinearLayout的dispatchTouchEvent-- action=0
     TouchLinearLayout的onInterceptTouchEvent-- action=0,result=true
     TouchLinearLayout的onTouch-- action=0
     TouchLinearLayout的onTouchEvent-- action=0
     TouchLinearLayout的dispatchTouchEvent-- action=2
     TouchLinearLayout的onTouch-- action=2
     TouchLinearLayout的onTouchEvent-- action=2
     TouchLinearLayout的dispatchTouchEvent-- action=1
     TouchLinearLayout的onTouch-- action=1
     TouchLinearLayout的onTouchEvent-- action=1
     TouchLinearLayout的onClick--
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean result = super.onInterceptTouchEvent(ev);
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
//                result = false;
                break;
            case MotionEvent.ACTION_MOVE:
//                result = false;
                break;
            case MotionEvent.ACTION_UP:
//                result = true;
                break;
            default:
                break;
        }
        DLog.eLog("TouchLinearLayout的onInterceptTouchEvent-- action=" + ev.getAction()+",result="+result);
        return result;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        DLog.eLog("TouchLinearLayout的onTouch-- action="+event.getAction());
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        DLog.eLog("TouchLinearLayout的onTouchEvent-- action="+event.getAction());
        return super.onTouchEvent(event);
    }

    @Override
    public void onClick(View v) {
        DLog.eLog("TouchLinearLayout的onClick--");
    }
}
