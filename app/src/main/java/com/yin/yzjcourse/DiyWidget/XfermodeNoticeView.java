package com.yin.yzjcourse.DiyWidget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

/**
 * 我想使用ProterDuff.Mode.DST_OUT实现notice.png中图一的效果，结果实际效果却是图二的效果
 *
 * 原因是：在该例中你直接在当前View的Canvas上画图，无论你使用什么Mode，
 * 两个图的的相交部分总是黑色的，不知道是从哪里来的，要想实现Mode的正常效果，
 * 可以自己创建一个myCanvas,在该画布上画完图后再在当前View的画布上显示，如上一个例子：XfermodeView.java。
 */

public class XfermodeNoticeView extends View {
    private Paint ringPaint;//在当前View画空心圆用的画笔
    public XfermodeNoticeView(Context context) {
        super(context);
        initView();
    }

    private void initView() {
        ringPaint = new Paint();

//        ringPaint.setStyle(Paint.Style.FILL);
        ringPaint.setStyle(Paint.Style.STROKE);
        ringPaint.setAntiAlias(true);
//        ringPaint.setARGB(255, 255 ,225, 255);
        ringPaint.setColor(Color.RED);
//        ringPaint.setStrokeWidth(10);
    }

    public XfermodeNoticeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public XfermodeNoticeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public XfermodeNoticeView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float outSideRadius = getWidth()/3;//外圆半径
        float inSideRadius = getWidth()/4;//内圆半径
        float midX = getWidth()/2;
        float midY = getHeight()/ 2;
        ringPaint.reset();
        ringPaint.setColor(Color.GREEN);
        ringPaint.setAntiAlias(true);
        canvas.drawCircle(midX, midY, outSideRadius, ringPaint);//外层圆,第一二个参数是圆心的x,y坐标，这里是View的中心，第二个参数是半径，第三个参数是Paint
        ringPaint.reset();
        ringPaint.setColor(Color.BLUE);
        ringPaint.setAntiAlias(true);
        ringPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));//取下层图片非交集部分
        canvas.drawCircle(midX, midY, inSideRadius, ringPaint);//内层圆
    }

}
