package com.yin.yzjcourse.DiyWidget;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.TextView;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;
import com.yin.yzjcourse.utils.DLog;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * android开发艺术探索
 */
public class VelocityTrackerActivity extends BaseActivity implements View.OnTouchListener {

    //    @BindView(R.id.my_btn)
//    TouchButton myBtn;
    @BindView(R.id.tv)
    TextView tv;

    private VelocityTracker velocityTracker = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //试试
        setContentView(R.layout.activity_velocity_tracker);
        ButterKnife.bind(this);
        tv.setOnTouchListener(this);
//        tv.setFocusable(true);
        tv.setClickable(true);
//        tv.setLongClickable(true);
    }

    /**
     * View的onTuchEvent()也是同样的道理
     * @param v
     * @param event
     * @return
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        //第一步：创建velocityTracker实例
        if (velocityTracker == null) {
            velocityTracker = VelocityTracker.obtain();
        }
        //第二步：接收MotionEvent追踪当前手势的速度
        velocityTracker.addMovement(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                //写在这里不行
                velocityTracker.addMovement(event);
                break;
            }
            case MotionEvent.ACTION_MOVE: {

                break;
            }
            case MotionEvent.ACTION_UP: {
                //第三步：计算速度，获取速度之前必须先计算，参数的单位是ms，你传1000，getXVelocity()或getYVelocity()就返
                // 回1000ms时间内划过多少像素；你传100，getXVelocity()或getYVelocity()就返回会100ms时间内划过多少像素
                velocityTracker.computeCurrentVelocity(1000);

                //第四步：获取水平方向或竖直方向的速度，速度可以为负数，速度的正负可按这个公式来看 -> 速度=(坐标系终点位置-坐标系起点位置) / 时间段
                //因此手指逆着坐标系的正方向滑动，所产生的速度就是负值
                int xVelocity = (int) velocityTracker.getXVelocity();
                int yVelocity = (int) velocityTracker.getYVelocity();
                DLog.eLog("计算速度："+xVelocity+ " , " +yVelocity);

                //第五步：重置VelocityTracker
                velocityTracker.clear();
                break;
            }
            default:
                break;
        }

        return false;
    }

    @Override
    protected void onDestroy() {
        //第六步：回收内存
        if (velocityTracker!=null) {
            velocityTracker.recycle();
        }
        super.onDestroy();
    }
}
