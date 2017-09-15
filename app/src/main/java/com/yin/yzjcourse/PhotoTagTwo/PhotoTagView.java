package com.yin.yzjcourse.PhotoTagTwo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

import com.yin.yzjcourse.utils.DLog;

/**
 * Created by think on 2017/9/13.
 */

public class PhotoTagView extends View {
    private Bitmap srcBitmap;
    private int screenWidth;
    private int screenHeight;

    public PhotoTagView(Context context, Bitmap bitmap){
//        super(context);
        this(context);
        srcBitmap = bitmap;
    }
    public PhotoTagView(Context context) {
        super(context);
        initView();
    }

    public PhotoTagView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public PhotoTagView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PhotoTagView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private void initView() {
        WindowManager wm = (WindowManager) this.getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics m = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(m);
        screenWidth = m.widthPixels;
        screenHeight = m.heightPixels;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawBitmap();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
//        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
//        int measureHeight = MeasureSpec.getSize(heightMeasureSpec);
//        int modeHeight = MeasureSpec.getSize(heightMeasureSpec);
        int[] location = new int[2];
        getRootView().getLocationOnScreen(location);
        DLog.eLog("根view的位置："+location[0]+","+location[1]);
        int seeableHeight = screenHeight-location[1];
        int seeableWidth = seeableHeight*srcBitmap.getWidth()/srcBitmap.getHeight();
        DLog.eLog("最终图片的宽高："+seeableWidth+","+seeableHeight);
        setMeasuredDimension(seeableWidth,seeableHeight);
    }
}
