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
    int times = 0;
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
     一：只有ACTION_DOWN时返回true：
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

     二：只有ACTION_MOVE时返回true：
     TouchLinearLayout的dispatchTouchEvent-- action=0
     TouchLinearLayout的onInterceptTouchEvent-- action=0,result=false
     TouchButton的dispatchTouchEvent-- action=0
     TouchButton的onTouch-- action=0
     TouchButton的onTouchEvent-- action=0,result:

     TouchLinearLayout的dispatchTouchEvent-- action=2
     TouchLinearLayout的onInterceptTouchEvent-- action=2,result=true
     TouchButton的dispatchTouchEvent-- action=3
     TouchButton的onTouch-- action=3
     TouchButton的onTouchEvent-- action=3,result:

     TouchLinearLayout的dispatchTouchEvent-- action=2
     TouchLinearLayout的onTouch-- action=2
     TouchLinearLayout的onTouchEvent-- action=2

     TouchLinearLayout的dispatchTouchEvent-- action=2
     TouchLinearLayout的onTouch-- action=2
     TouchLinearLayout的onTouchEvent-- action=2

     TouchLinearLayout的dispatchTouchEvent-- action=1
     TouchLinearLayout的onTouch-- action=1
     TouchLinearLayout的onTouchEvent-- action=1

     三：只有ACTION_UP返回true：
     TouchLinearLayout的dispatchTouchEvent-- action=0
     TouchLinearLayout的onInterceptTouchEvent-- action=0,result=false
     TouchButton的dispatchTouchEvent-- action=0
     TouchButton的onTouch-- action=0
     TouchButton的onTouchEvent-- action=0,result:

     TouchLinearLayout的dispatchTouchEvent-- action=2
     TouchLinearLayout的onInterceptTouchEvent-- action=2,result=false
     TouchButton的dispatchTouchEvent-- action=2
     TouchButton的onTouch-- action=2
     TouchButton的onTouchEvent-- action=2,result:

     TouchLinearLayout的dispatchTouchEvent-- action=2
     TouchLinearLayout的onInterceptTouchEvent-- action=2,result=false
     TouchButton的dispatchTouchEvent-- action=2
     TouchButton的onTouch-- action=2
     TouchButton的onTouchEvent-- action=2,result:

     TouchLinearLayout的dispatchTouchEvent-- action=1
     TouchLinearLayout的onInterceptTouchEvent-- action=1,result=true
     TouchButton的dispatchTouchEvent-- action=3
     TouchButton的onTouch-- action=3
     TouchButton的onTouchEvent-- action=3,result:

     通过以上三步可以得出一个结论：
     对于ACTION_DOWN事件父ViewGroup拦截下来后会自己处理该ACTION_DOWN事件，子View还没有参与整个触摸事件所以子View没啥变化；
     对于ACTION_MOVE123...和ACTION_UP事件父ViewGroup的拦截(从子View那里拦截)下来严格意义上并不叫“拦截”而是叫“中断”子View将要处理的
     ACTION_MOVE123...和ACTION_UP事件转而导致子View处理ACTION_CANCEL事件。因为父ViewGroup拦截下来ACTION_MOVE123...和ACTION_UP后并没有处理
     他拦截下来的事件而是获取到了处理整个事件序列中下一个事件(所拦截下来的事件的下一个事件，如果没有下一个事件则父ViewGroup后面也没什么可处
     理的，例如ACTION_UP就没有下一个事件)的权利。
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean result = super.onInterceptTouchEvent(ev);
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
//                result = true;
                break;
            case MotionEvent.ACTION_MOVE:
//                result = true;
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

    /**
     * onClicks是在ACTION_UP后执行，还有一条时从子View拦截过来的EVENT执行到ACTION_UP后不会执行onClick
     * 必须是ACTION_DOWN时手指正好在该View或ViewGroup上时，ACTION_UP后才会执行OnClick
     * @param v
     */
    @Override
    public void onClick(View v) {
        DLog.eLog("TouchLinearLayout的onClick--");
    }
}
