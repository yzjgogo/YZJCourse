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
 */

public class XfermodeMultiplyTwitterView extends View {
    private Paint mBitPaint;
    private Bitmap BmpDST,BmpSRC;
    public XfermodeMultiplyTwitterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mBitPaint = new Paint();
        //可以互换顺序，因为Multiply模式中，一方透明或无像素，则另一方对应区域也会透明不显示
        BmpDST = BitmapFactory.decodeResource(getResources(), R.mipmap.twiter_light,null);
        BmpSRC = BitmapFactory.decodeResource(getResources(),R.mipmap.twiter_bg,null);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int layerId = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);

        canvas.drawBitmap(BmpDST,0,0,mBitPaint);
        mBitPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.MULTIPLY));
        canvas.drawBitmap(BmpSRC,0,0,mBitPaint);

        mBitPaint.setXfermode(null);
        canvas.restoreToCount(layerId);
    }
}
