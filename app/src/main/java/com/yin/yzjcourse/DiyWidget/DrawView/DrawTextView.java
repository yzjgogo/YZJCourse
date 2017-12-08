package com.yin.yzjcourse.DiyWidget.DrawView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import com.yin.yzjcourse.utils.DLog;

/**
 */
public class DrawTextView extends View {
    private Paint paint;
    public DrawTextView(Context context) {
        super(context);
        initView();
    }

    public DrawTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public DrawTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public DrawTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private void initView() {
        DLog.eLog("初始化");
        paint =new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        DLog.eLog("开始画矩形1");
        super.onDraw(canvas);
        DLog.eLog("开始画矩形2");
        int baseLineX = 40 ;
        int baseLineY = 200;

        //画基线
        paint.setColor(Color.RED);
        canvas.drawLine(baseLineX, baseLineY, 3000, baseLineY, paint);

        //写文字
        paint.setColor(Color.GREEN);
        paint.setTextSize(120); //以px为单位
        paint.setTextAlign(Paint.Align.LEFT);//默认就是LEFT
        canvas.drawText("garvic\'s blog", baseLineX, baseLineY, paint);
        //drawText的第二三个参数(baseLineX,baseLineY)决定的text绘制的起点
        //drawText的第三个参数是基线所在的Y轴坐标
        //当TextAlign为LEFT时,从点(baseLineX,baseLineY)开始向右侧画text，超出屏幕不显示；
        //当TextAlign为CENTER时,从点(baseLineX,baseLineY)开始向同时向左右两侧画text，超出屏幕不显示；
        //当TextAlign为RIGHT,从点(baseLineX,baseLineY)开始向左侧画text，超出屏幕不显示；

    }
}
