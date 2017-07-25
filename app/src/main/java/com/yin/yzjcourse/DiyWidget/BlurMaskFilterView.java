package com.yin.yzjcourse.DiyWidget;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

/**
 * public BlurMaskFilter(float radius, Blur style)
 * 其中：
 float radius：用来定义模糊半径，同样是高斯模糊算法。
 Blur style：发光样式，有内发光、外发光、和内外发光，分别对应：Blur.INNER(内发光)、
 Blur.SOLID(外发光)、Blur.NORMAL(内外发光)、Blur.OUTER(仅发光部分可见)，这几个模式，后面我们会逐个来展示用法。

 大家是否可以看出来发光效果与setShadowLayer所生成的阴影之间有什么联系？
 setShadowLayer所生成的阴影，其实就是将新建的阴影图形副本进行发光效果并且位移一定的距离而已。
 */

public class BlurMaskFilterView extends View {
    private Paint paint;
    private BlurMaskFilter.Blur blur = null;

    public BlurMaskFilterView(Context context) {
        super(context);
        initView();
    }

    public BlurMaskFilterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public BlurMaskFilterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public BlurMaskFilterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private void initView() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
    }

    public void setInner() {
        blur = BlurMaskFilter.Blur.INNER;
        postInvalidate();
    }

    public void setSolid() {
        blur = BlurMaskFilter.Blur.SOLID;
        postInvalidate();
    }

    public void setNormal() {
        blur = BlurMaskFilter.Blur.NORMAL;
        postInvalidate();
    }

    public void setOuter() {
        blur = BlurMaskFilter.Blur.OUTER;
        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (blur != null) {
            paint.setMaskFilter(new BlurMaskFilter(50, blur));
        }
        canvas.drawCircle(300, 300, 150, paint);
    }
}
