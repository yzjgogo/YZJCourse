package com.yin.yzjcourse.DiyWidget;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;
import com.yin.yzjcourse.utils.DLog;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * https://blog.csdn.net/u010410408/article/details/39577399
 */
public class ScaleGestureDetectorActivity extends BaseActivity implements View.OnTouchListener {

    @BindView(R.id.fl)
    FrameLayout fl;
    @BindView(R.id.tv)
ImageView tv;

    private ScaleGestureDetector mGestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //试试
        setContentView(R.layout.activity_scale_gesture_detector);
        ButterKnife.bind(this);
        mGestureDetector = new ScaleGestureDetector(this,new ScaleGestureListener());
        fl.setOnTouchListener(this);
//        tv.setFocusable(true);
//        tv.setClickable(true);
//        tv.setLongClickable(true);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }
    private class ScaleGestureListener implements ScaleGestureDetector.OnScaleGestureListener {

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            float scaleValue = detector.getScaleFactor();
            float scaleX = tv.getScaleX();
            float scaleY = tv.getScaleY();
            float finalScaleValue = scaleX*scaleValue;
            DLog.eLog("执行onScale："+scaleValue+" , "+scaleX+" , "+scaleY+" , "+finalScaleValue);
            tv.setScaleX(finalScaleValue);
            tv.setScaleY(finalScaleValue);
            return true;
        }

        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            DLog.eLog("执行onScaleBegin：");
            return true;
        }

        @Override
        public void onScaleEnd(ScaleGestureDetector detector) {
            DLog.eLog("执行onScaleEnd：");

        }
    }
}
