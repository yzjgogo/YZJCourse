package com.yin.yzjcourse.DiyWidget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by think on 2017/5/8.
 */

public class XfermodeView extends View {
    private Canvas myCanvas;
    private Paint ringPaint;//在当前View画空心圆用的画笔
    private Paint circlePaint;
    private int mRadius;
    private Paint mFgroundPaint;
    private RectF mRect;
    public XfermodeView(Context context) {
        super(context);
        initView();
    }

    private void initView() {
        mRadius = getScreenWidth()*1/4;
        ringPaint = new Paint();
        circlePaint = new Paint();
        mFgroundPaint = new Paint();

//        ringPaint.setStyle(Paint.Style.FILL);
        ringPaint.setStyle(Paint.Style.STROKE);
        ringPaint.setAntiAlias(true);
//        ringPaint.setARGB(255, 255 ,225, 255);
        ringPaint.setColor(Color.RED);
//        ringPaint.setStrokeWidth(10);

//        circlePaint.setStrokeWidth(mRadius);
        circlePaint.setAntiAlias(true);
//        circlePaint.setARGB(255, 0, 0, 0);
        circlePaint.setColor(Color.BLUE);
        //对于同一个Canvas,PorterDuff.Mode用在上面图形的Paint上，用于与下面图形的交互,两个图形用一个Paint也是相同的道理
        circlePaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.XOR));


        mFgroundPaint.setAntiAlias(true);
//        mFgroundPaint.setARGB(185, 0, 0, 0);
        mFgroundPaint.setColor(Color.GREEN);
//        mFgroundPaint.setStrokeWidth(10);
//        mFgroundPaint.setStyle(Paint.Style.STROKE);不设置默认fill
//        mFgroundPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.XOR));
    }

    public XfermodeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public XfermodeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public XfermodeView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawColor(Color.BLUE);
        mRect = new RectF(0, 0, getWidth(), getHeight());
        Bitmap bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        myCanvas = new Canvas(bitmap);//创建Canvas要指定一个Bitmap，也可用无参的构造器之后手动指定
        //在当前View的画布上画红色空心圆
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 3, ringPaint);
        //下面的图形
        myCanvas.drawRect(new RectF(getWidth() / 2, getHeight() / 2, getWidth(), getHeight()), mFgroundPaint) ;//在自己的画布上画绿色矩形
        //上面的图形
        myCanvas.drawCircle(getWidth() / 2, getHeight() / 2, mRadius, circlePaint);//在自己的画布上画蓝色实心圆
        //在你自己创建的画布myCanvas上画完图像后，要将myCanvas的bitmap画到当前View的canvas上才能显示出来
        canvas.drawBitmap(bitmap, null, mRect, null);//第三个参数是bitmap要画到的目标矩形
        //你在自己画布上画的图形添加到当前View的canvas上后会遮挡当前view的对应区域
    }

    private int getScreenWidth() {
        WindowManager wm = (WindowManager) this.getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics m = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(m);
        return m.widthPixels;
    }
}
