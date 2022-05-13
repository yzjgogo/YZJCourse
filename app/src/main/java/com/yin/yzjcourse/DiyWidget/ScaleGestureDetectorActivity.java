package com.yin.yzjcourse.DiyWidget;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

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
        mGestureDetector = new ScaleGestureDetector(this, new ScaleGestureListener());
        fl.setOnTouchListener(this);
//        tv.setFocusable(true);
//        tv.setClickable(true);
//        tv.setLongClickable(true);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }

    /************* 方式1与方式2能实现相同的效果，就是利用了每次新的双指触摸都会重置getScaleFactor()为1，与onScale()的返回值的特点 *******************/

    /**
     * 方式1：因为每次手指接触屏幕，getScaleFactor()都会被重置为1，
     * 且如果onScale()返回false,说明本次没有处理缩放，getScaleFactor()的值时按照从双指按下时或者上次onScale return true时计算缩放比
     * 如果onScale()返回true,则getScaleFactor()是相对上次onScale()执行时的缩放百分比
     *
     */
    private class ScaleGestureListener implements ScaleGestureDetector.OnScaleGestureListener {

        /**
         * getCurrentSpanX() 两个手指在X轴方向的像素距离，可能为负数吗
         * getCurrentSpanY() 两个手指在Y轴方向的像素距离，可能为负数吗
         * getCurrentSpan() 两个手指在屏幕上的像素距离，与getCurrentSpanX()和getCurrentSpanY()满足勾股定理
         *
         * //PreviousSpan与CurrentSpan道理相同，只是PreviousSpan是上次位置两根手指的距离，因此上次的getCurrentSpan == 本次的getPreviousSpan
         * getPreviousSpanX() 上次两个手指在X轴方向的像素距离，可能为负数吗
         * getPreviousSpanY() 上次两个手指在Y轴方向的像素距离，可能为负数吗
         * getPreviousSpanSpan() 上次两个手指在屏幕上的像素距离，与getPreviousSpanX()和getPreviousSpanY()满足勾股定理
         *
         * getFocusX()和getFocusY()时两根手指的连线的中点的坐标，这里是在fl上的坐标
         *
         * getEventTime() === onTouch的event.getEventTime()，从系统启动算起的时间SystemClock.uptimeMillis()
         *
         * getTimeDelta() 返回从上次onScale返回true时开始(可能因为业务逻辑有一段时间返回false)，到本次调用getTimeDelta()的时间毫秒间隔
         *
         * isInProgress()：如果缩放手势正处在进行中，返回true；否则返回false。
         *
         * @param detector
         * @return
         */
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            float scaleValue = detector.getScaleFactor();
            float scaleX = tv.getScaleX();
            float scaleY = tv.getScaleY();
            float finalScaleValue = scaleX*scaleValue;
            DLog.eLog("执行onScale1："+scaleValue+" , "+scaleX+" , "+scaleY+" , "+finalScaleValue+" ,x_dist="+detector.getCurrentSpanX()+",y_dist="+detector.getCurrentSpanY()+",dist="+detector.getCurrentSpan());
            DLog.eLog("执行onScale2："+scaleValue+" , "+scaleX+" , "+scaleY+" , "+finalScaleValue+" ,x_prev="+detector.getPreviousSpanX()+",y_prev="+detector.getPreviousSpanY()+",prev="+detector.getPreviousSpan());
            DLog.eLog("中点坐标："+"x="+detector.getFocusX()+",y="+detector.getFocusY()+",event_time="+detector.getEventTime()+",sys_time="+ SystemClock.uptimeMillis()+",time_delta="+detector.getTimeDelta());
            tv.setScaleX(finalScaleValue);
            tv.setScaleY(finalScaleValue);
            return true;
        }

        /**
         * 双指按下时执行，返回false不会执行onScale
         * @param detector
         * @return
         */
        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            DLog.eLog("执行onScaleBegin：");
            return true;
        }

        /**
         * 双指离开时执行
         * @param detector
         */
        @Override
        public void onScaleEnd(ScaleGestureDetector detector) {
            DLog.eLog("执行onScaleEnd：");

        }
    }


    /**
     * 方式2：因为每次手指接触屏幕，getScaleFactor()都会被重置为1，
     * 且如果onScale()返回false,说明本次没有处理缩放，getScaleFactor()的值时按照从手指按下时或者上次onScale return true时计算缩放比
     * 如果onScale()返回true,则getScaleFactor()是相对上次onScale()执行时的缩放百分比
     *
     * 这里在onScaleEnd()时记录下每次手指离开时，tv的缩放比，然后下次双指再次缩放时，直接用getScaleFactor() * lastScaleValue作用到tv，实现图片的缩放，且不会因为手指离开导致getScaleFactor()被
     * 重置为1而导致的每次双指缩放tv都会从1开始缩放引起的影响，因为我用lastScaleValue记录下来了
     */
    /*
    private class ScaleGestureListener implements ScaleGestureDetector.OnScaleGestureListener {
        float lastScaleValue = 1.0f;

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            float scaleValue = detector.getScaleFactor() * lastScaleValue;
            tv.setScaleX(scaleValue);
            tv.setScaleY(scaleValue);
            return false;
        }

        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            return true;
        }

        @Override
        public void onScaleEnd(ScaleGestureDetector detector) {
            lastScaleValue = tv.getScaleX();
        }
    }
    */
}
