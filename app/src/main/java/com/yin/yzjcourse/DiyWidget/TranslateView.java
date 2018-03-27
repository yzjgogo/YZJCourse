package com.yin.yzjcourse.DiyWidget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import com.yin.yzjcourse.utils.DLog;

/**
 * Created by think on 2018/3/27.
 */

public class TranslateView extends View {
    public TranslateView(Context context) {
        super(context);
        initView();
    }

    public TranslateView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public TranslateView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TranslateView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private void initView() {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //构造两个画笔，一个红色，一个绿色
        Paint paint_green = generatePaint(Color.GREEN, Paint.Style.STROKE, 3);
        Paint paint_red = generatePaint(Color.RED, Paint.Style.STROKE, 3);
        Paint paint_black = generatePaint(Color.BLACK, Paint.Style.STROKE, 3);

        //构造一个矩形
        Rect rect1 = new Rect(0, 0, 400, 220);
        Rect rect2 = new Rect(0, 0, 400, 220);
        Rect rect3 = new Rect(50, 50, 400, 220);

        //在平移画布前用绿色画下边框
        canvas.drawRect(rect1, paint_green);
        DLog.eLog("平移前的矩形坐标："+rect1.toShortString());
        //平移画布后,再用红色边框重新画下这个矩形
//        canvas.save();
        canvas.translate(100, 100);//平移的是canvas的坐标系，即拿着坐标系原点挪来挪去，但是原来canvas上的图形并不会随着移动rotate、scale、skew、clip也是同样的道理
        canvas.drawRect(rect2, paint_red);
//        canvas.restore();调用了translate之后如果不回复，则canvas就会一直是平移后的状态，即不可逆，rotate、scale、skew、clip也是同样的道理
        canvas.drawRect(rect3, paint_black);//如果之前没有调用restore()则会在平移后的canvas中画该rect3，如果之前调用了restore()则是在平移之前(原来)的canvas中画rect3
        DLog.eLog("平移后的矩形坐标："+rect1.toShortString());
    }

    private Paint generatePaint(int color, Paint.Style style, int width) {
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setStyle(style);
        paint.setStrokeWidth(width);
        return paint;
    }
}
