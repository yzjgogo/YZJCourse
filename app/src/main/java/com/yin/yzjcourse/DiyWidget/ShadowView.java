package com.yin.yzjcourse.DiyWidget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import com.yin.yzjcourse.R;

/**
 * void setShadowLayer(float radius, float dx, float dy, int color)
 * 它参数的意义如下：
 * float radius：意思是模糊半径，radius越大越模糊，越小越清晰，但是如果radius设置为0，则阴影消失不见；有关清除阴影的问题，下面我们会专门讲。
 * float dx：阴影的横向偏移距离，正值向右偏移，负值向左偏移
 * float dy：阴影的纵向偏移距离，正值向下偏移，负值向上偏移
 * int color：绘制阴影的画笔颜色，即阴影的颜色（对图片阴影无效）
 * <p>
 * 使用setShadowLayer所产生的阴影，对于文字和绘制的图形的阴影都是使用自定义的阴影画笔颜色来画的，
 * 而图片的阴影则是直接产生一张相同的图片，仅对阴影图片的边缘进行模糊。
 * <p>
 * 注意：这里有一点需要非常注意的是setShadowLayer只有文字绘制阴影支持硬件加速，其
 * 它都不支持硬件加速，所以为了方便起见，我们需要在自定义控件中禁用硬件加速。
 */

public class ShadowView extends View {
    private Paint paint = new Paint();
    private Bitmap bmp;
    private float radius = 1, dx = 10, dy = 10;
    private boolean isClearShadow = false;

    public ShadowView(Context context) {
        super(context);
        initView();
    }

    public ShadowView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public ShadowView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ShadowView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private void initView() {
        //禁用硬件加速，因为我把这个控件用到了ScrollView里，所以我在最顶层的父控件通过android:layerType="software"禁用了硬件加速，具体看布局文件
//        setLayerType( LAYER_TYPE_SOFTWARE , null);
        paint.setColor(Color.GREEN);
        paint.setTextSize(25);
        bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
    }

    public void addRadius() {
        radius += 5;
        postInvalidate();
    }

    public void addDx() {
        dx += 5;
        postInvalidate();
    }

    public void addDy() {
        dy += 5;
        postInvalidate();
    }

    public void clearShadowWithRadius() {
        radius = 0;
        postInvalidate();
    }

    public void clearLayer() {
        isClearShadow = true;
        postInvalidate();
    }

    public void reset() {
        isClearShadow = false;
        radius = 1;
        dx = 10;
        dy = 10;
        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isClearShadow) {
            paint.clearShadowLayer();
        } else {
            paint.setShadowLayer(radius, dx, dy, Color.GRAY);
        }
        canvas.drawText("测试一下", 100, 100, paint);
        canvas.drawCircle(200, 200, 50, paint);
        canvas.drawBitmap(bmp, null, new Rect(200, 300, 200 + bmp.getWidth(), 300 + bmp.getHeight()), paint);
    }
}
