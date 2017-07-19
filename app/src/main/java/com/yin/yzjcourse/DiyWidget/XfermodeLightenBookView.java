package com.yin.yzjcourse.DiyWidget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.yin.yzjcourse.R;

/**
 * 它的算法是： [Sa + Da - Sa*Da,Sc*(1 - Da) + Dc*(1 - Sa) + max(Sc, Dc)]
 * 两个图像重合的区域才会有颜色值变化，所以只有重合区域才有变亮的效果，
 * 源图像非重合的区域，由于对应区域的目标图像是空白像素，所以直接显示源图像。
 */

public class XfermodeLightenBookView extends View {
    private Paint mBitPaint;
    private Bitmap BmpDST,BmpSRC;

    public XfermodeLightenBookView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mBitPaint = new Paint();
        BmpDST = BitmapFactory.decodeResource(getResources(), R.mipmap.book_bg,null);
        BmpSRC = BitmapFactory.decodeResource(getResources(),R.mipmap.book_light,null);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int layerId = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);
        //先画书架，做为目标图像
        canvas.drawBitmap(BmpDST,0,0,mBitPaint);
        mBitPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.LIGHTEN));
        //再图光点
        canvas.drawBitmap(BmpSRC,0,0,mBitPaint);

        mBitPaint.setXfermode(null);
        canvas.restoreToCount(layerId);

    }
}
