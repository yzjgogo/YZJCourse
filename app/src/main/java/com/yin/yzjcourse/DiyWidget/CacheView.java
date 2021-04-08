package com.yin.yzjcourse.DiyWidget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.os.Build;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;


public class CacheView extends View {
    private Bitmap mCacheBitmap;
    /**被缓存的View的宽和高，就是这个mCacheBitmap的宽高*/
    private float mWidth, mHeight;
    /**mCacheBitmap的左上角的位置*/
    private PointF bitmapLeftTopPoint;
    private Paint mPaint;

    public CacheView(Context context) {
        super(context);
        init();
    }

    public CacheView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CacheView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CacheView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        //初始化Paint
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(Color.RED);
        //初始化拖拽PointF
        bitmapLeftTopPoint = new PointF();
    }

    /**
     * 设置缓存Bitmap
     *
     * @param mCacheBitmap 缓存Bitmap
     */
    public void setCacheBitmap(Bitmap mCacheBitmap,float left,float top) {
        this.mCacheBitmap = mCacheBitmap;
        mWidth = mCacheBitmap.getWidth();
        mHeight = mCacheBitmap.getHeight();
        bitmapLeftTopPoint.set(left,top);
//        isInvalidate = true;
        invalidate();
    }

    /**
     * 设置缓存Bitmap
     *
     * @param mCacheBitmap 缓存Bitmap
     */
    public void setCacheBitmapImmediately(Bitmap mCacheBitmap) {
        this.mCacheBitmap = mCacheBitmap;
        mWidth = mCacheBitmap.getWidth();
        mHeight = mCacheBitmap.getHeight();
//        isInvalidate = true;
        invalidate();
    }

    /**
     * 设置bitmap绘制的左上角坐标
     *
     * @param touchX
     * @param touchY
     * @param isDraw
     */
    public void setDragPoint(float touchX, float touchY, boolean isDraw) {
        bitmapLeftTopPoint.set(touchX, touchY);
        if (isDraw) {
//            this.isInvalidate = true;
            invalidate();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        try {
            if (mCacheBitmap != null) {
//            if (mCacheBitmap!=null) {
                canvas.drawBitmap(mCacheBitmap, bitmapLeftTopPoint.x,
                        bitmapLeftTopPoint.y, mPaint);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
