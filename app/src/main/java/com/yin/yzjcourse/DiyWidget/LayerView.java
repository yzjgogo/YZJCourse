package com.yin.yzjcourse.DiyWidget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by think on 2017/5/10.
 */

public class LayerView extends View {
    private Paint ringPaint;//在当前View画空心圆用的画笔
    public LayerView(Context context) {
        super(context);
        initView();
    }

    public LayerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public LayerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public LayerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
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

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //当前View的画布上使用了xfermode, color filter, or alpha时常用saveLayer方法创建一个新的图层
//        创建一个单独的图层，并放入Canvas图层栈
        int saveCount = canvas.saveLayer(0, 0, getWidth(), getHeight(), null,
                Canvas.ALL_SAVE_FLAG);
        Log.e("yin","saveCount:"+saveCount);//saveCount:1
        float outSideRadius = getWidth()/3;//外圆半径
        float inSideRadius = getWidth()/4;//内圆半径
        float midX = getWidth()/2;
        float midY = getHeight()/ 2;
        ringPaint.reset();
        ringPaint.setColor(Color.GREEN);
        ringPaint.setAntiAlias(true);
        canvas.drawCircle(midX, midY, outSideRadius, ringPaint);
        ringPaint.reset();
        ringPaint.setColor(Color.BLUE);
        ringPaint.setAntiAlias(true);
        ringPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        canvas.drawCircle(midX, midY, inSideRadius, ringPaint);//内层圆
        //将之前创建的图层从图层栈中退出，并将在该图层绘制的图片”绘制“到canvas上
        canvas.restoreToCount(saveCount);
//        canvas.restore();
    }
}
