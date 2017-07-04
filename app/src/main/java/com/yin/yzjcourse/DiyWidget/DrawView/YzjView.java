package com.yin.yzjcourse.DiyWidget.DrawView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.yin.yzjcourse.R;

/**
 * TODO: document your custom view class.
 */
public class YzjView extends View {
    private Paint paint;
    private Path path;
    public YzjView(Context context) {
        super(context);
        initView();
    }

    public YzjView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public YzjView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public YzjView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private void initView() {
        paint=new Paint();
        paint.setColor(Color.RED);  //设置画笔颜色
        paint.setStyle(Paint.Style.STROKE);//填充样式改为描边
        paint.setStrokeWidth(5);//设置画笔宽度
        path = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.GREEN);
        path.moveTo(10, 150);
        path.quadTo(40, 10, 100, 150);
        canvas.drawPath(path, paint);
    }
}
