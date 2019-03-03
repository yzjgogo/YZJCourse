package com.yin.yzjcourse.Base.mysurface;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * SurfaceView一般用于需要频繁刷新且每一帧都需要处理较大数据时，因为它是在子线程绘制，不会阻塞UI线程，特别适用于视频或游戏的应用。
 */
public class SurfaceViewTemplate extends SurfaceView implements SurfaceHolder.Callback,Runnable {
    private  static  final  String TAG="SurfaceView";
    //SurfaceHolder
    private SurfaceHolder mHolder;
    //用于绘图的Canvas
    private Canvas mCanvas;
    //子线程标志位，用于判断是否可以继续绘制
    private boolean mIsDrawing;
    //画笔
    private Paint mPaint;
    //路径
    private Path mPath;
    public SurfaceViewTemplate(Context context) {
        super(context);
        initView();
    }


    public SurfaceViewTemplate(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public SurfaceViewTemplate(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        //获取SurfaceHolder
        mHolder = getHolder();
        //添加回调,用于管理SurfaceView的生命周期
        mHolder.addCallback(this);
        mPath=new Path();
        //初始化画笔
        mPaint=new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(6);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setKeepScreenOn(true);//保持屏幕常亮


    }

    /**
     * SurfaceView被创建时调用
     * @param holder
     */
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mIsDrawing=true;//可以开始画了
        new Thread(this).start();//启动子线程去执行绘制的操作
    }

    /**
     * SurfaceView发生变化时调用
     * @param holder
     * @param format
     * @param width
     * @param height
     */
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    /**
     * SurfaceView销毁时调用
     * @param holder
     */
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mIsDrawing=false;//说明子线程应该停止绘制了，后面的while会根据这个判断，是否停止绘制

    }

    /**
     * 真正执行绘制所在的子线程，不是在UI线程，这是与View的区别；
     * 而且从这里可看到是被动刷新，我们通过线程的sleep来控制我们想要的刷新评率
     */
    @Override
    public void run() {
        long start =System.currentTimeMillis();
        while(mIsDrawing){
            draw();
            long end = System.currentTimeMillis();
            if(end-start<100){
                try{
                    Thread.sleep(100-end+start);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 具体的绘制方法，运行在子线程里
     */
    private void draw() {
        try{
            //锁定画布并返回画布对象，其实就是获取SurfaceView的画布，拿来画东西
            mCanvas=mHolder.lockCanvas();
            //接下去就是在画布上进行一下draw，这个过程就和View的画东西一样了
            mCanvas.drawColor(Color.WHITE);
            mCanvas.drawPath(mPath,mPaint);

        }catch (Exception e){
        }finally {
            //当画布内容不为空时，才post，避免出现黑屏的情况。
            //画完后，交还画布，并显示出来
            if(mCanvas!=null)
                mHolder.unlockCanvasAndPost(mCanvas);
        }
    }

    /**
     * 绘制触摸滑动路径
     * @param event MotionEvent
     * @return true
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x=(int) event.getX();
        int y= (int) event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "onTouchEvent: down");
                mPath.moveTo(x,y);
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "onTouchEvent: move");
                mPath.lineTo(x,y);
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "onTouchEvent: up");
                break;
        }
        return true;
    }

    /**
     * 清屏
     * @return true
     */
    public boolean reDraw(){
        mPath.reset();
        return true;
    }

}