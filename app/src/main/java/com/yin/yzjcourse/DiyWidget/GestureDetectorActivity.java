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
        mGestureDetector = new GestureDetector(this,new GestureListener());
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

        @Override
        public boolean onDown(MotionEvent e) {
            DLog.eLog("执行onDown："+e.getAction());
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {
            DLog.eLog("执行onShowPress："+e.getAction());
        }

        @Override
        public void onLongPress(MotionEvent e) {
            DLog.eLog("执行onLongPress："+e.getAction());
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            DLog.eLog("执行onSingleTapUp："+e.getAction());
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            DLog.eLog("执行onScroll："+distanceX+","+distanceY);
            return false;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            DLog.eLog("执行onFling："+velocityX+","+velocityY);
            return false;
        }
        ////////////////////////////////////////////////////////////////////////////

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            DLog.eLog("执行onSingleTapConfirmed："+e.getAction());
            return false;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            DLog.eLog("执行onDoubleTap："+e.getAction());
            return false;
        }

        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {
            DLog.eLog("执行onDoubleTapEvent："+e.getAction());
            return false;
        }
    }
}
