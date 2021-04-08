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
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import com.yin.yzjcourse.R;

/**
 * Created by think on 2017/8/21.
 */

public class ShaderView extends View {
    private Paint paint;
    private Bitmap bitmap;

    public ShaderView(Context context) {
        super(context);
        initView();
    }

    public ShaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public ShaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ShaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private void initView() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.dog_edge);
        setTileMode(Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
    }

    public void setTileMode(Shader.TileMode xTileMode, Shader.TileMode yTileMode) {
        paint.setShader(new BitmapShader(bitmap, xTileMode, yTileMode));
//        paint.setColor(Color.BLUE);如果paint设置了shader则color无效
        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /**
         * Shader:着色器，BitmapShader图片着色器。
         * paint.setShader(Shader shader):设置着色器，用于给指定区域着色。
         * 这里是用paint里的着色器给所画的矩形区域着色，但是你的着色器的图片，可能比所画的矩形区域小，所以就出现了TileMode,用于指定目标图片填充指定区域的方式。
         * TileMode:有三种MIRROR,CLAMP,REPEAT;
         * MIRROR:镜子效果，对称的，就跟镜子一样，多出的剪裁掉；
         * CLAMP:取图片的边缘色填充；
         * REPEAT:复制整个图片填充，多出的剪裁掉；
         * 着色器的填充方式：先填充Y轴，再填充X轴；例如REPEAT,则先Y轴REPEAT，Y轴填充满后再X轴REPEAT;CLAMP和MIRROR也是同样的道理。
         *
         * 下面这个非常重要：着色器永远只从View的左上角开始画
         * 这里我们drawRect画的矩形是整个控件的大小(getWidth(),getHeight())，如果我们画一个只占控件一小部分的矩形，着色器会从哪里开始着色呢？
         * 答案是从控件的左上角开始着色，而不是从你所画的区域的左上角开始着色。只不过会在你所画的区域显示出着色后的内容，而区域之外不显示着色内容。
         * 根据这个原理可以实现望远镜效果。
         *
         */
        canvas.drawRect(new Rect(0, 0, getWidth(), getHeight()), paint);
    }
}
