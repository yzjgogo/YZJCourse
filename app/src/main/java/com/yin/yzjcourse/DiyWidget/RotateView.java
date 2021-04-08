package com.yin.yzjcourse.DiyWidget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

/**
 * 在View的onDraw方法中，通常在执行Canvas的translate,scale,rotate,skew,concat,或者clipRect等方法时会先调用Canvas的save方法，用于保存canvas原来的状态；当以上一系列方法及其延伸操作执行完成后，会调用Canvas的restore方法，以恢复到Canvas原来的状态。
 以rotate方法为例：
 参考图：R.drawable.rotate_one , R.drawable.rotate_two , R.drawable.rotate_three
 */

public class RotateView extends View {
    public RotateView(Context context) {
        super(context);
    }

    public RotateView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RotateView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public RotateView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint background = new Paint();
        Paint line = new Paint();
        line.setStrokeWidth(4);
        background.setColor(Color.GRAY);
        line.setColor(Color.RED);
        //正方形看不出旋转的效果，因此用矩形
        int px = 400;
        int py = 700;

        canvas.drawRect(0, 0, px, py, background);//画灰色矩形
        //进入translate,scale,rotate,skew,concat or clipRect等操作时调用
        canvas.save();
        //以(px/2,py/2)位中心将canvas旋转90度，Canvas的旋转可以理解为只是旋转了其坐标轴,同时出现一个“新的”canvas，
        // 你在canvas原来的操作样式等保持不变,即你在调用translate,scale,rotate,skew,concat之前对canvas的操作样式，在调用
        //这些方法之后，之前的样式保持不变，因为已经显示到屏幕上了，但是这些方法是不可逆的，要想恢复canvas原来的状态需要调用save,restore等方法
        canvas.rotate(90, px / 2, py / 2);//canvas.rotate(float degrees)方法默认沿着View的原点旋转;
        //旋转后的操作以旋转后的坐标系为准，因为x,y轴会旋转， 在旋转后的canvas上画箭头
        canvas.drawLine(px / 2, 0, 0, py / 2, line);
        canvas.drawLine(px / 2, 0, px, py / 2, line);
        canvas.drawLine(px / 2, 0, px / 2, py, line);
        //离开translate,scale,rotate,skew,concat or clipRect等操作时调用
        canvas.restore();
        //这里是在原Canvas上操作，而不是在旋转后的canvas上操作，因为前面已经调用了restore
        canvas.drawCircle(px - 100, py - 100, 50, line);
    }
}
