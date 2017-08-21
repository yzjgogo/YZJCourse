package com.yin.yzjcourse.DiyWidget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.yin.yzjcourse.R;
import com.yin.yzjcourse.utils.DLog;

/**
 * Created by think on 2017/8/21.
 */

public class TelescopeView extends View {
    private Paint paint;
    private Bitmap bitmap, shaderBmp;
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
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.scenery);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (shaderBmp == null) {
            //这段代码要在onDraw里执行，如果你在initView()中执行则getWidth和getHeight获取不到值
            shaderBmp = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);//创建一个和控件大小一样的空shaderBmp
            Canvas shaderCanvas = new Canvas(shaderBmp);
            shaderCanvas.drawBitmap(bitmap, null, new Rect(0, 0, getWidth(), getHeight()), paint);//将图片bitmap拉伸后填充到shaderBmp中
            //通过以上代码就实现了用于着色的图片与控件大小一样
            paint.setShader(new BitmapShader(shaderBmp, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT));
        }
        if (pointX != -1 && pointY != -1) {
            paint.setShader(new BitmapShader(shaderBmp, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT));
            canvas.drawCircle(pointX, pointY, 150, paint);
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
