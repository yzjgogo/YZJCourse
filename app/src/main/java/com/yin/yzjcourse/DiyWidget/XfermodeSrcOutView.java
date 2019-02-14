package com.yin.yzjcourse.DiyWidget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * 计算公式为：[Sa * (1 - Da), Sc * (1 - Da)]
 * 所以这个模式的特性可以概括为：以目标图像的透明度的补值来调节源图像的透明度和色彩饱和度。
 * 即当目标图像为空白像素时，就完全显示源图像，当目标图像的透明度为100%时，交合区域为空像素。
 * Mode.SRC_OUT简单来说，当目标图像有图像时结果显示透明像素，当目标图像没有图像时，结果显示源图像。
 */

public class XfermodeSrcOutView extends View {
    private int width = 400;
    private int height = 400;
    private Bitmap dstBmp;
    private Bitmap srcBmp;
    private Paint mPaint;

    public XfermodeSrcOutView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        srcBmp = makeSrc(width, height);
        dstBmp = makeDst(width, height);
        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int layerID = canvas.saveLayer(0,0,width*2,height*2,mPaint,Canvas.ALL_SAVE_FLAG);

        canvas.drawBitmap(dstBmp, 0, 0, mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));
        canvas.drawBitmap(srcBmp,width/2,height/2, mPaint);
        mPaint.setXfermode(null);

        canvas.restoreToCount(layerID);

    }

    // create a bitmap with a circle, used for the "dst" image
    static Bitmap makeDst(int w, int h) {
        Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bm);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);

        p.setColor(0xFFFFCC44);
        c.drawOval(new RectF(0, 0, w, h), p);
        return bm;
    }

    // create a bitmap with a rect, used for the "src" image
    static Bitmap makeSrc(int w, int h) {
        Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bm);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);

        p.setColor(0xFF66AAFF);
        c.drawRect(0, 0, w, h, p);
        return bm;
    }
}
