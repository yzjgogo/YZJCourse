package com.yin.yzjcourse.DiyWidget;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;

import com.yin.yzjcourse.utils.DLog;

public class CircleLoadingBar extends Drawable implements Drawable.Callback,Runnable {
    private Paint paint;
    private Canvas canvas;
    private int angle;
    private RectF circle;
    private float cx,cy;
    private float mHeight,mWidth=100;
    private float mRadius=20;
    private Drawable.Callback cb;
    private boolean running=false;


    public void invalidateDrawable(Drawable drawable){
        super.invalidateSelf(); //This was done for my specific example. I wouldn't use it otherwise
    }


    public void scheduleDrawable(Drawable drawable, Runnable runnable, long l){
        invalidateDrawable(drawable);
    }

    public void unscheduleDrawable(Drawable drawable,Runnable runnable){
        super.unscheduleSelf(runnable);
    }

    public CircleLoadingBar(){
        this(0);
    }

    public CircleLoadingBar(int angle){
        this.angle=angle;

        paint=new Paint();
        paint.setColor(Color.GREEN);
        paint.setAntiAlias(true);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStyle(Paint.Style.STROKE);

        circle= new RectF();
    }


    @Override
    public void draw(Canvas canvas){

        canvas.save();

        Rect bounds = getBounds();

        circle.set(bounds);
        DLog.eLog("执行这里的draw："+angle);
        canvas.drawArc(circle, 90, angle, true, paint);

    }

    public void nextFrame(){
        unscheduleSelf(this);
        scheduleSelf(this, SystemClock.uptimeMillis() + 250);
    }

    public void stop(){
        running=false;
        unscheduleSelf(this);
    }

    public void start(){
        if(!running){
            running=true;
            nextFrame();
        }
    }

    public void run(){
        angle++;
        invalidateSelf();
        nextFrame();
    }

    public void doAnim(){
        angle++;
        invalidateSelf();
    }


    @Override
    public void setAlpha(int alpha) {
        // Has no effect
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        // Has no effect
    }

    @Override
    public int getOpacity() {
        // Not Implemented
        return PixelFormat.TRANSLUCENT;
    }
}
