package com.yin.yzjcourse.DiyWidget.DrawView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * TODO: document your custom view class.
 */
public class QuadView extends View {
    private Paint paint;
    private Path path;
    private float mPreX,mPreY;
    public QuadView(Context context) {
        super(context);
        initView();
    }

    public QuadView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public QuadView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public QuadView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private void initView() {
        paint=new Paint();
        paint.setColor(Color.RED);  //设置画笔颜色
        paint.setStyle(Paint.Style.STROKE);//填充样式改为描边
        paint.setStrokeWidth(2);//设置画笔宽度
        paint.setAntiAlias(true);
        path = new Path();
    }

    /**使用quadTo实现手势轨迹，不完美
     * 这种方式不平滑，有锯齿，因为虽然你使用的是贝塞尔曲线，但是你一直在画一阶贝塞尔曲线
     * 因为你一直把上一个点作为贝塞尔曲线的控制点，但是上一个点又是贝塞尔曲线的起点，所以是已阶贝塞尔曲线
     * @return
     */
    /*
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:{
                path.moveTo(event.getX(),event.getY());
                mPreX = event.getX();
                mPreY = event.getY();
                return true;
            }
            case MotionEvent.ACTION_MOVE:{
                path.quadTo(mPreX,mPreY,event.getX(),event.getY());
                mPreX = event.getX();
                mPreY = event.getY();
                invalidate();
            }
            break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }
    */

    /**
     * 使用lineTo实现手势轨迹，不完美，因为实现方式是线段连接，所以有锯齿，效果和，上面的quadTo方法实现的一样
     * @return
     */
    /*
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:{
                path.moveTo(event.getX(),event.getY());
                return true;
            }
            case MotionEvent.ACTION_MOVE:{
                path.lineTo(event.getX(),event.getY());
                invalidate();
            }
            break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }
    */

    /**
     * 该方式比前面两种方式都流程
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:{
                path.moveTo(event.getX(),event.getY());
                mPreX = event.getX();
                mPreY = event.getY();
                return true;
            }
            case MotionEvent.ACTION_MOVE:{
                //上一个点作为二阶贝赛尔曲线的控制点，上一个点和当前点的中间位置作为贝塞尔曲线的结束点
                path.quadTo(mPreX,mPreY,(mPreX+event.getX())/2,(mPreY+event.getY())/2);
                mPreX = event.getX();
                mPreY = event.getY();
                invalidate();
            }
            break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.GREEN);
        canvas.drawPath(path, paint);
    }
}
