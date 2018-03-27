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

/**
 * Created by think on 2018/3/27.
 */

public class ScaleView extends View {
    public ScaleView(Context context) {
        super(context);
        initView();
    }

    public ScaleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public ScaleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ScaleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private void initView() {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//  //scale 缩放坐标系密度
        Paint paint_green = generatePaint(Color.GREEN, Paint.Style.STROKE, 5);
        Paint paint_red = generatePaint(Color.RED, Paint.Style.STROKE, 5);

        Rect rect1 = new Rect(10, 10, 200, 100);
        canvas.drawRect(rect1, paint_green);
        //scale其实就是坐标系密度的控制，第一个参数是x轴的伸缩比例，默认是1，小于1就变密，大于1就变疏
        //例如这里是0.5，则如果原来坐标系中0~1之间有1米宽，则现在设置为0.5后，则0~1之间只有0.5米宽；但是0点仍然是0,1点仍然是1，。。。
        //只是视觉上密度的稀疏
        canvas.scale(0.5f, 1);
        canvas.drawRect(rect1, paint_red);
    }

    private Paint generatePaint(int color, Paint.Style style, int width) {
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setStyle(style);
        paint.setStrokeWidth(width);
        return paint;
    }
}
