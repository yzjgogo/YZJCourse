package com.yin.yzjcourse.Window;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;
import com.yin.yzjcourse.Utils;
import com.yin.yzjcourse.utils.DLog;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyWindowActivity extends BaseActivity implements View.OnTouchListener {
    private static final String TAG = "MainActivity";

    private Button mFloatingButton;
    private LayoutParams mLayoutParams;
    private WindowManager mWindowManager;
    boolean isClick = true;//没有用系统的onClickListener,在ACTION_UP自己模拟处理onClick()，用于将单击和拖拽时间区分，根据时间差区分
    long startTime = 0;

    //xm和ym用于弥补，滑动时刚开始滑动，window就瞬移了一段距离，其实就是单击的坐标和window左上角的坐标的差值
    int xm = 0;
    int ym = 0;
//    private boolean isMove = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_window);
        ButterKnife.bind(this);
        mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        WindowManager m1 = getWindowManager();
        if (m1 == mWindowManager) {
            DLog.eLog("是同一个");
        } else {
            DLog.eLog("不是同一个");
        }
    }

    /**
     * 假想：一个Window就是整个屏幕的大小，分成两个部分：1:View所占的区域；2：View所占区域以外的区域(透明区域)。
     * <p>
     * flags:
     * <p>
     * 首先，无论flag是什么类型，‘View所占的区域’这部分区域始终会响应输入事件和触摸事件的，例如，无论是什么类型的flags点击‘View所占的区域’
     * 都会响应单击事件，但是‘透明区域’分如下情况。
     * <p>
     * FLAG_NOT_FOCUSABLE:表示Window不需要获取焦点，也不需要接收各种输入事件，此标记会同时开启FLAG_NOT_TOUCH_MODAL,
     * 最终事件会直接传递给下层的具有焦点的Window(也就是说允许透明区域里的事件给下层的Window)。
     * <p>
     * FLAG_NOT_TOUCH_MODAL：如果没开启，则整个屏幕任何区域的事件都不向下层传递(也就是说不允许透明区域里的事件给下层的Window)
     * 例如，你单击‘透明区域’，响应的还是‘View所在区域’的单击事件，而不是你单击的具体区域的事件。
     * <p>
     * FLAG_SHOW_WHEN_LOCKED：锁屏的时候也允许显示该Window
     * <p>
     * <p>
     * type：指定Window的类型
     * 应用Window：z-ordered范围1-99,只存活于某个Activity。
     * 子Window：z-ordered范围1000-1999，不能单独存在，它需要附属在特定的父Window中，例如常见的Dialog就是一个子Window。
     * 系统Window：z-ordered范围2000-2999，无论你的屏幕切换到哪个应用了，甚至是切换到桌面了，系统Window仍然在显示。例如，Toast和系统状态栏就是系统Window。
     * <p>
     * Window是分层的，每个Window都有对应的z-ordered。z-ordered值大的在上，小的在下，类似于HTML中的z-index概念。
     * z-ordered值就可以赋值给type,type在哪个范围就属于哪类Window，例如给type = 20001则type属于系统Window，但是最好
     * 不要这样做，系统可能不允许你这样做，一般如果我们要指定某个Window是系统Window则只需让type = LayoutParams.TYPE_SYSTEM_ERROR即可。
     * <p>
     * addView(view,params):创建一个Window并向其添加View；
     * updateViewLayout(view,params)：更新Window中的View；
     * removeView(view)：如果想删除Window只需删除里面的View即可。
     * 可见WindowManager操作Window的过程实际上就是操作Window里的View的过程；
     */
    @OnClick(R.id.bt_window)
    public void onViewClicked() {
        mFloatingButton = new Button(this);
        mFloatingButton.setText("click me");
        mLayoutParams = new LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0, 0,
                PixelFormat.TRANSPARENT);
        mLayoutParams.flags = LayoutParams.FLAG_NOT_FOCUSABLE;
//        mLayoutParams.type = LayoutParams.TYPE_SYSTEM_ERROR;

        //占用状态栏 https://blog.csdn.net/qq_21154101/article/details/123926510
//        mLayoutParams.flags = WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS or WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN


        mLayoutParams.type = 2;
        mLayoutParams.gravity = Gravity.LEFT | Gravity.TOP;
        mLayoutParams.x = 100;
        mLayoutParams.y = 300;
        mFloatingButton.setOnTouchListener(this);
        mWindowManager.addView(mFloatingButton, mLayoutParams);
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                Log.e("yin","按下");
                xm = (int) (event.getRawX()-mLayoutParams.x);
                ym = (int) (event.getRawY()-mLayoutParams.y);
                startTime = System.currentTimeMillis();
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                Log.e("yin","移动");
                long currentTime = System.currentTimeMillis();
                long midtime = currentTime-startTime;
                //midtime用于区分是拖拽还是单击
                if (midtime > 100) {//走拖拽
                    isClick = false;
                    mLayoutParams.x = (int) event.getRawX()-xm;
                    mLayoutParams.y = (int) event.getRawY()-ym;
                    mWindowManager.updateViewLayout(mFloatingButton, mLayoutParams);//更新window的位置
                }else {//走单击，具体在ACTION_UP执行单击
                    isClick = true;
                }
                break;
            }
            case MotionEvent.ACTION_UP: {
                Log.e("yin","抬起");
                if (isClick) {
                    Log.e("yin","单击");
                    Utils.showToast(MyWindowActivity.this, "点击我了");
                }
                break;
            }
            default:
                break;
        }

        return false;
    }

    @Override
    protected void onDestroy() {
        try {
            mWindowManager.removeView(mFloatingButton);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }

    @OnClick(R.id.bt_window2)
    public void onViewClicked2() {
        DLog.eLog("移除去");
        mWindowManager.removeView(getWindow().getDecorView());
        DLog.eLog("移除去2");
    }
}
