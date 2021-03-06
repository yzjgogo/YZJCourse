package com.yin.yzjcourse.DiyWidget.DrawView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

/**
 * TODO: document your custom view class.
 */
public class SkewView extends View {
    private Paint paintGreen;
    private Paint paintRed;
    private int angle = 60;
    public SkewView(Context context) {
        super(context);
        initView();
    }

    public SkewView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public SkewView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SkewView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private void initView() {
        paintGreen =new Paint();
        paintGreen.setColor(Color.GREEN);  //设置画笔颜色
        paintGreen.setStyle(Paint.Style.STROKE);//填充样式改为描边
        paintGreen.setStrokeWidth(5);//设置画笔宽度
        paintRed =new Paint();
        paintRed.setColor(Color.RED);  //设置画笔颜色
        paintRed.setStyle(Paint.Style.STROKE);//填充样式改为描边
        paintRed.setStrokeWidth(5);//设置画笔宽度
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Rect rect1 = new Rect(10,10,200,100);
        canvas.drawRect(rect1,paintGreen);
//        DLog.eLog("结果："+Math.tan(Math.toRadians(60)));
        //skew参数是倾斜角度的tan值，并不是倾斜角度
        //skew的结果类似于纸片在空中飞舞时，人眼在某一刻的角度看到的情况
//        float sx:将画布在x方向上倾斜相应的角度，sx倾斜角度的tan值，
//        float sy:将画布在y轴方向上倾斜相应的角度，sy为倾斜角度的tan值，
//        canvas.save();
        canvas.skew((float) Math.tan(Math.toRadians(angle)),(float) Math.tan(Math.toRadians(0)));//X轴倾斜60度，Y轴不变
//        canvas.restore();
        canvas.drawRect(rect1, paintRed);
    }
    public void setAngle(int angle){
        this.angle = angle;
        invalidate();
    }
}
