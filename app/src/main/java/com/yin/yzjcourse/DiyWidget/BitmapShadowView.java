package com.yin.yzjcourse.DiyWidget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
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
 * Bitmap的Bitmap extractAlpha()方法：
 * 生成只提取了原图的alpha通道的新图，也就是说新的bitmap只有alpha值，rgb值为0。这个函数的作用是获取原图的轮廓，然后可以填充rgb值。因此可以实现阴影，影子，光晕等效果。
 * 返回一个新的Bitmap，这个Bitmap与原来的Bitmap有相同的透明度。且通过canvas.drawBitmap()方法，可以使用Paint中的颜色填充该bitmap区域
 *
 */

public class BitmapShadowView extends View {
    private Paint paint;
    private Bitmap imageBmp,alphaBmp;
    private int shadowDx = 10,shadowDy = 10;
    private float shadowRadius = 10;
    private int shadowColor;
    public BitmapShadowView(Context context) throws Exception{
        super(context);
        initView(context,null);
    }

    public BitmapShadowView(Context context, @Nullable AttributeSet attrs) throws Exception{
        super(context, attrs);
        initView(context,attrs);
    }

    public BitmapShadowView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) throws Exception{
        super(context, attrs, defStyleAttr);
        initView(context,attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public BitmapShadowView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) throws Exception{
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context,attrs);
    }

    private void initView(Context context,AttributeSet attrs) throws Exception{
        //禁用硬件加速，因为我把这个控件用到了ScrollView里，所以我在最顶层的父控件通过android:layerType="software"禁用了硬件加速，具体看布局文件
//        setLayerType( LAYER_TYPE_SOFTWARE , null);
        if (attrs!=null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.BitmapShadowView);
            int resId = typedArray.getResourceId(R.styleable.BitmapShadowView_src,-1);
            if (resId == -1) {
                throw new Exception("必须制定一个图片");
            }
            imageBmp = BitmapFactory.decodeResource(getResources(),resId);
            alphaBmp = imageBmp.extractAlpha();
            shadowDx = typedArray.getInt(R.styleable.BitmapShadowView_shadowDx,0);
            shadowDy = typedArray.getInt(R.styleable.BitmapShadowView_shadowDy,0);
            shadowRadius = typedArray.getFloat(R.styleable.BitmapShadowView_shadowRadius,0.0f);
            shadowColor = typedArray.getColor(R.styleable.BitmapShadowView_shadowColor,Color.BLACK);
            typedArray.recycle();
        }
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int measureHeight = MeasureSpec.getSize(heightMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
        int width = imageBmp.getWidth();
        int height = imageBmp.getHeight();
        setMeasuredDimension(modeWidth ==MeasureSpec.EXACTLY?measureWidth:width,modeHeight == MeasureSpec.EXACTLY?measureHeight:height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth()-shadowDx;
        int height = width*imageBmp.getHeight()/imageBmp.getWidth();
        paint.setColor(shadowColor);
        paint.setMaskFilter(new BlurMaskFilter(shadowRadius, BlurMaskFilter.Blur.SOLID));
        canvas.drawBitmap(alphaBmp,null,new Rect(shadowDx,shadowDy,width,height),paint);//paint的颜色用于填充alphaBmp的区域
        //红色阴影的原图
        paint.setMaskFilter(null);
        canvas.drawBitmap(imageBmp,null,new Rect(0,0,width,height),paint);//画笔颜色无效，只画原图像
    }
}
