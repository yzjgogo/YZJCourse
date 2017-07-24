package com.yin.yzjcourse.DiyWidget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.yin.yzjcourse.R;

/**
 *
 */

public class XfermodeSrcOutEraserView extends View {
    private Paint mBitPaint;
    private Bitmap BmpDST,BmpSRC;
    private Path mPath;
    private float mPreX,mPreY;
    public XfermodeSrcOutEraserView(Context context, AttributeSet attrs) {
        super(context, attrs);

        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        mBitPaint = new Paint();
        mBitPaint.setColor(Color.RED);//什么颜色都可以
        mBitPaint.setStyle(Paint.Style.STROKE);
        mBitPaint.setStrokeWidth(45);

        BmpSRC = BitmapFactory.decodeResource(getResources(), R.mipmap.dog,null);
        BmpDST = Bitmap.createBitmap(BmpSRC.getWidth(), BmpSRC.getHeight(), Bitmap.Config.ARGB_8888);
        mPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int layerId = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);

        //先把手指轨迹画到目标Bitmap上
        Canvas c = new Canvas(BmpDST);
        c.drawPath(mPath,mBitPaint);

        //然后把目标图像画到画布上
        canvas.drawBitmap(BmpDST,0,0,mBitPaint);

        //计算源图像区域
        mBitPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));
        canvas.drawBitmap(BmpSRC,0,0,mBitPaint);

        mBitPaint.setXfermode(null);
        canvas.restoreToCount(layerId);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(event.getX(),event.getY());
                mPreX = event.getX();
                mPreY = event.getY();
                return true;
            case MotionEvent.ACTION_MOVE:
                float endX = (mPreX+event.getX())/2;
                float endY = (mPreY+event.getY())/2;
                mPath.quadTo(mPreX,mPreY,endX,endY);
                mPreX = event.getX();
                mPreY =event.getY();
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        //invadite()必须在主线程中调用，而postInvalidate()内部是由Handler的消息机制实现的，所以在任何线程都可以调用，
        // 但实时性没有invadite()强。所以一般为了保险起见，一般是使用postInvalidate()来刷新界面。
        postInvalidate();
        return super.onTouchEvent(event);
    }
}
