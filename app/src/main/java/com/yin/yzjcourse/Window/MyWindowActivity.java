package com.yin.yzjcourse.Window;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyWindowActivity extends BaseActivity implements View.OnTouchListener {
    private static final String TAG = "MainActivity";

    private Button mFloatingButton;
    private WindowManager.LayoutParams mLayoutParams;
    private WindowManager mWindowManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_window);
        ButterKnife.bind(this);
        mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
    }

    /**
     * 假想：一个Window就是整个屏幕的大小，分成两个部分：1:View所占的区域；2：View所占区域以外的区域(透明区域)。
     *
     * flags:
     * FLAG_NOT_FOCUSABLE:表示Window不需要获取焦点，也不需要接收各种输入事件，此标记会同时开启FLAG_NOT_TOUCH_MODAL,
     *                      最终事件会直接传递给下层的具有焦点的Window(也就是说允许透明区域里的事件给下层的Window)。
     *
     *  FLAG_NOT_TOUCH_MODAL：如果没开启，则整个屏幕任何区域的事件都不向下层传递(也就是说不允许透明区域里的事件给下层的Window)
     *
     *  FLAG_SHOW_WHEN_LOCKED：锁屏的时候也允许显示该Window
     *
     *
     *  type：指定Window的类型
     *  应用Window：z-ordered范围1-99,只存活于某个Activity。
     *  子Window：z-ordered范围1000-1999，不能单独存在，它需要附属在特定的父Window中，例如常见的Dialog就是一个子Window。
     *  系统Window：z-ordered范围2000-2999，无论你的屏幕切换到哪个应用了，甚至是切换到桌面了，系统Window仍然在显示。例如，Toast和系统状态栏就是系统Window。
     *
     *  Window是分层的，每个Window都有对应的z-ordered。z-ordered值大的在上，小的在下，类似于HTML中的z-index概念。
     *  z-ordered值就可以赋值给type,type在哪个范围就属于哪类Window，例如给type = 20001则type属于系统Window，但是最好
     *  不要这样做，系统可能不允许你这样做，一般如果我们要指定某个Window是系统Window则只需让type = LayoutParams.TYPE_SYSTEM_ERROR即可。
     *
     * addView(view,params):创建一个Window并向其添加View；
     * updateViewLayout(view,params)：更新Window中的View；
     * removeView(view)：如果想删除Window只需删除里面的View即可。
     * 可见WindowManager操作Window的过程实际上就是操作Window里的View的过程；
     */
    @OnClick(R.id.bt_window)
    public void onViewClicked() {
        mFloatingButton = new Button(this);
        mFloatingButton.setText("click me");
        mLayoutParams = new WindowManager.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0, 0,
                PixelFormat.TRANSPARENT);
        mLayoutParams.flags = LayoutParams.FLAG_NOT_FOCUSABLE
                | LayoutParams.FLAG_NOT_TOUCH_MODAL
                | LayoutParams.FLAG_SHOW_WHEN_LOCKED;
        mLayoutParams.type = LayoutParams.TYPE_SYSTEM_ERROR;
        mLayoutParams.gravity = Gravity.LEFT | Gravity.TOP;
        mLayoutParams.x = 100;
        mLayoutParams.y = 300;
        mFloatingButton.setOnTouchListener(this);
        mWindowManager.addView(mFloatingButton, mLayoutParams);
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int rawX = (int) event.getRawX();
        int rawY = (int) event.getRawY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                int x = (int) event.getX();
                int y = (int) event.getY();
                mLayoutParams.x = rawX;
                mLayoutParams.y = rawY;
                mWindowManager.updateViewLayout(mFloatingButton, mLayoutParams);
                break;
            }
            case MotionEvent.ACTION_UP: {
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
}
