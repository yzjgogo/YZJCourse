package com.yin.yzjcourse.DiyWidget;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;
import com.yin.yzjcourse.utils.DLog;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * https://blog.csdn.net/harvic880925/article/details/39520901
 */
public class GestureDetectorActivity extends BaseActivity implements View.OnTouchListener {

    //    @BindView(R.id.my_btn)
//    TouchButton myBtn;
    @BindView(R.id.tv)
    TextView tv;

    private GestureDetector mGestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //试试
        setContentView(R.layout.activity_gesture_detector);
        ButterKnife.bind(this);
        mGestureDetector = new GestureDetector(this, new GestureListener());
        tv.setOnTouchListener(this);
        tv.setFocusable(true);
        tv.setClickable(true);
        tv.setLongClickable(true);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }

    private class GestureListener implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

        /**
         * 只要手指接触到屏幕就第一个出发，且整个触摸事件只出发这一次
         *
         * @param e
         * @return
         */
        @Override
        public boolean onDown(MotionEvent e) {
            DLog.eLog("执行onDown：" + e.getAction() + ",rawX=" + e.getRawX() + ",rawY=" + e.getRawY() + ",x=" + e.getX() + ",y=" + e.getY() + " , " + System.currentTimeMillis() + " , " + e.getAction());
            return false;
        }

        /**
         * 从onDown到瞬间时间，这期间没有松开手指，也没有拖动手指，就会执行onShowPress
         * 具体这个瞬间是多久，不清楚，我用System.currentTimeMillis()计算了下，应该是100ms，
         * 也就是说从onDown被执行开始，到100ms这段时间内，如果手指没有松开，也没有拖动，则就会出发onShowPress,
         *
         * @param e
         */
        @Override
        public void onShowPress(MotionEvent e) {
            DLog.eLog("执行onShowPress：" + e.getAction() + " , " + System.currentTimeMillis());
        }

        /**
         * 长按屏幕从执行onDown开始超过300ms就会出发，这个300毫秒是我用System.currentTimeMillis()计算出来的
         * 前提是没有滑动(没有超过系统的最小滑动距离)，执行了onLongPress后，即使你再在屏幕上滑动也不会出发其他任何回调方法
         * 也就是说，如果onLongPress被执行，则一定是按顺序执行了 onDown -> onShowPress -> onLongPress这三个方法
         *
         * @param e
         */
        @Override
        public void onLongPress(MotionEvent e) {
            DLog.eLog("执行onLongPress：" + e.getAction() + " , " + System.currentTimeMillis());
        }

        /**
         * 按下并抬起手指后(滑动距离小于系统能识别的最小滑动距离)触发 onSingleTapUp，效果同OnDoubleTapListener的OnDoubleTapListener的onSingleTapConfirmed，可理解为‘单击事件’
         * 分两种情况：
         * 1，按下后立刻快速抬起，执行顺序是 onDown -> onSingleTapUp -> onSingleTapConfirmed
         * 2，按下后稍等小于300ms的时间(避免出发onLongPress)后抬起，执行顺序是 onDown -> onShowPress -> onSingleTapUp -> OnDoubleTapListener的onSingleTapConfirmed
         * <p>
         * 按下后滑动距离大于系统能识别的最小滑动距离 或者 到抬起的时间大于300ms，都不会触发onSingleTapUp当然也不会出发OnDoubleTapListener的onSingleTapConfirmed
         *
         * @param e
         * @return
         */
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            DLog.eLog("执行onSingleTapUp：" + e.getAction());
            return false;
        }

        /**
         * 手指在屏幕滑动的过程时出发，也就是ACTION_MOVE时触发，最后如果有快速瞬间滑动的操作(Fling)就会出发onFling
         *
         * @param e1        手指按下的event,也就是onDown(MotionEvent e)里的参数e，也就是onFling(e1,e2,velocityX,velocityY)里的e1
         * @param e2        当前ACTION_MOVE的event
         * @param distanceX 参考distanceY
         * @param distanceY 本次调用onScroll方法时，相对上次调用onScroll方式时，手指在Y轴移动的距离，向下滑动为负数，向上滑动为正数
         * @return
         */
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
//            DLog.eLog("执行onScroll："+",rawX="+e1.getRawX()+",rawY="+e1.getRawY()+",x="+e1.getX()+",y="+e1.getY()+","+distanceX+","+distanceY);
            DLog.eLog("执行onScroll：" + ",rawX=" + e2.getRawX() + ",rawY=" + e2.getRawY() + ",x=" + e2.getX() + ",y=" + e2.getY() + "," + distanceX + "," + distanceY + " * " + e1.getAction() + " * " + e2.getAction());
            return false;
        }

        /***
         * 滑屏：手指按下(onDown)->随意滑动一段举例(onScroll,可无)->手指快速滑一下屏幕(必须，才能出发onScroll..onFling)
         *
         * @param e1 手指按下的event,也就是onDown(MotionEvent e)里的参数e，也就是第一个onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY)的参数e1，action是ACTION_DOWN
         * @param e2 快速滑动结束时手指抬起时的event，其x,y,rawX,rawY与onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY)的e2的一样，但是action时ACTION_UP
         * @param velocityX x轴Fling的速度，像素/秒
         * @param velocityY y轴Fling的速度，像素/秒
         * @return
         */
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
//            DLog.eLog("执行onFling："+",rawX="+e1.getRawX()+",rawY="+e1.getRawY()+",x="+e1.getX()+",y="+e1.getY()+","+velocityX+","+velocityY);
            DLog.eLog("执行onFling：" + ",rawX=" + e2.getRawX() + ",rawY=" + e2.getRawY() + ",x=" + e2.getX() + ",y=" + e2.getY() + "," + velocityX + "," + velocityY + " * " + e1.getAction() + " * " + e2.getAction());
            return false;
        }
        ////////////////////////////////////////////////////////////////////////////

        /**
         * 可理解为“单击事件”，效果同OnGestureListener的onSingleTapUp,区别是，如果执行了onDoubleTap则不会执行onSingleTapConfirmed,但onSingleTapUp已经事先执行了
         * @param e
         * @return
         */
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            DLog.eLog("执行onSingleTapConfirmed：" + e.getAction());
            return false;
        }

        /**
         * 双击事件
         *
         * @param e
         * @return
         */
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            DLog.eLog("执行onDoubleTap：" + e.getAction());
            return false;
        }

        /**
         * 与onDoubleTap有关
         * @param e
         * @return
         */
        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {
            DLog.eLog("执行onDoubleTapEvent：" + e.getAction());
            return false;
        }
    }
}
