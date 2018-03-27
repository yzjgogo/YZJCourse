package com.yin.yzjcourse.DiyWidget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

/**
 * 弧形柱：参考图形arc_column.png
 * Created by think on 2017/5/8.
 */

public class ArcColumnView extends View {

    private Paint ringPaint;//在当前View画空心圆用的画笔
    private Paint textPaint;
    public ArcColumnView(Context context) {
        super(context);
        initView();
    }

    public ArcColumnView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public ArcColumnView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ArcColumnView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
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
        initTextPaint();
    }

    /**
     * 画文字用
     */
    private void initTextPaint() {
        textPaint = new Paint();
        textPaint.setColor(Color.RED);
        textPaint.setTextSize(30);
        textPaint.setTextAlign(Paint.Align.LEFT);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawThisText(canvas);
        float outSideRadius = getWidth()/3;//外圆半径
        float inSideRadius = getWidth()/4;//内圆半径
        //midX和midY是View的中心位置，这里用作俩圆的圆心(midX,midY)坐标
        float midX = getWidth()/2;
        float midY = getHeight()/2;
        canvas.drawCircle(midX, midY, outSideRadius, ringPaint);//外层圆,第一二个参数是圆心的x,y坐标，这里是View的中心，第二个参数是半径，第三个参数是Paint
        canvas.drawCircle(midX, midY, inSideRadius, ringPaint);//内层圆
        //圆心的x,y坐标各减半径即为包裹该圆的矩形左上角的x,y坐标，各加半径即为右下角的坐标，这样就确定了这个矩形
        //RectF(left,top,right,bottom);(left,top)即左上角的(x,y),(right,bottom)即右下角的(x,y)
        RectF outSideRectF = new RectF(midX-outSideRadius,midY-outSideRadius,midX + outSideRadius, midY+outSideRadius);//包裹外层圆
        RectF inSideRectF = new RectF(midX-inSideRadius,midY-inSideRadius,midX+inSideRadius,midY+inSideRadius);//包裹内层圆的矩形
        canvas.drawRect(outSideRectF,ringPaint);
        canvas.drawRect(inSideRectF, ringPaint);

        Paint arcPaint = new Paint();
        arcPaint.setColor(Color.GREEN);
        arcPaint.setAntiAlias(true);
        //默认就是FILL,FILL的意思是显示将Paint的起点和终点直线联系后形成闭合再用指定的颜色填满后的效果，STROKE是指只显示Paint走过的线的路径
        arcPaint.setStyle(Paint.Style.FILL);
        //路径，可以理解为线性的路径，没有宽度概念？
        Path path = new Path();
        //arcTo方法只是指定弧形的路径，还没有画出来显示到canvas上
        //画弧形的原理是：截取矩形所包裹的椭圆(包含圆)的一段即为弧形，
        // 圆的起始角度是圆心的正右侧(0度)，第二个参数是弧形开始的角度，第三个参数是弧形走过的角度，顺时针为正，逆时针为负
        path.arcTo(outSideRectF, 0, 90);//外层弧形
//        path.arcTo(inSideRectF, 0, 90);//内层弧形，不要这样走，这样两个path走不成弧形柱，可以用笔画一下,参考下图的图二
        //同一个path两次调用arcTo,中间的路径不会断开，而是第一次arcTo的终点最短距离走到第二次arcTo的起点，即中间没有抬笔，类似于汉子的连笔字
        path.arcTo(inSideRectF, 0+90, -90);//内层弧形，第二个path应该倒着走，这样就走成了一个空的弧形柱，再用paint的颜色一填充就形成了相应颜色的实体的扇形柱，参考下图图二
        path.close();//封闭轮廓，即轮廓是闭合的，没有开口,path走完后起点和终点直线连接，或者说path走完后封闭路径
        //将path指定的弧形用画笔画到画布上
        canvas.drawPath(path,arcPaint);
        //一个画笔Paint画两个Path中间是不可以抬笔的，类似于汉字的连笔字
        //即一个path从起点画到终点后要直接连(这个连是有痕迹的，类似连笔字，也是paint画出的内容)到下一个path的起点
    }

    /**
     * 参考：DrawTextView.java
     * @param canvas
     */
    private void drawThisText(Canvas canvas) {
        canvas.drawText("画矩形、圆、弧、弧形柱、Path用法",40,60,textPaint);
    }
}
