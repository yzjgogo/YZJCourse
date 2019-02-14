package com.yin.yzjcourse.DiyWidget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Shader;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.yin.yzjcourse.R;

/**
 * Created by think on 2017/8/21.
 */

public class TelescopeView extends View {
    private Paint paint;
    private Bitmap shaderBitmap;
    private float pointX = -1;
    private float pointY = -1;

    public TelescopeView(Context context) {
        super(context);
        initView();
    }

    public TelescopeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public TelescopeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TelescopeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private void initView() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        shaderBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.scenery);
//        paint.setColor(Color.BLUE);如果paint设置了shader则color无效
        paint.setShader(new BitmapShader(shaderBitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (pointX != -1 && pointY != -1) {
            //手指触摸屏幕的时候才开始用canvas画图，所以，刚进入该页面是空白页面，因为还没画图呢
            canvas.drawCircle(pointX, pointY, 150, paint);//画图的过程中，着色器从View的左上角开始着色，铺满view，但是drawCircle只画出了局部，也就实现了望远镜，参考ShaderView.java
        }
    }

    /**
     * 手指所到位置画一个圆，用于看下面的图画
     *
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                pointX = event.getX();
                pointY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                pointX = event.getX();
                pointY = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                pointX = -1;
                pointY = -1;
                break;
        }
        postInvalidate();
        return true;
    }
}
