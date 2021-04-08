package com.yin.yzjcourse.DiyWidget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.os.Build;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by think on 2017/8/22.
 */

public class ShaderSweepView extends View {
    private Paint paint;
    private RectF rectF;
    private SweepGradient sweepGradient;
    private LinearGradient linearGradient;
    private RadialGradient radialGradient;

    public ShaderSweepView(Context context) throws Exception {
        super(context);
        initView(context, null);
    }

    public ShaderSweepView(Context context, @Nullable AttributeSet attrs) throws Exception {
        super(context, attrs);
        initView(context, attrs);
    }

    public ShaderSweepView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) throws Exception {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ShaderSweepView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) throws Exception {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) throws Exception {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        rectF = new RectF(100,200,900,400);
        /**
         * 扫描渐变：围绕一个中心点，从水平向右方向开始，顺时针扫描渐变
         *
         * @param cx       中心点x
         * @param cy       中心点y
         * @param colors   参与渐变的颜色数组
         * @param positions 一般为null
         */
        sweepGradient= new SweepGradient(200,100,new int[]{Color.YELLOW,Color.BLUE,Color.RED},null);
        /**
         * 线性渐变：从起始点到结束点连成的线的方向上颜色渐变，注意斜线也是可以的，并不是必须水平或竖直
         * Create a shader that draws a linear gradient along a line.
         *
         * @param x0           开始点的x
         * @param y0           T开始点的y
         * @param x1           结束点的x
         * @param y1           结束点的y
         * @param colors       参与渐变的颜色数组
         * @param positions    一般为null，如果不为null好像就是非渐变的，而是层次分明的颜色梯度
         * @param tile         开始点和结束点两段之外的区域如何取颜色，参考ShaderView.java
         */
        linearGradient = new LinearGradient(100,200,900,400,new int[]{Color.YELLOW,Color.BLUE,Color.RED},null, Shader.TileMode.CLAMP);
        /**
         * 辐射渐变：从中心点向四面八方发射渐变色
         *
         * @param centerX  中心点的x
         * @param centerY  中心点的y
         * @param radius   辐射范围的半径
         * @param colors   参与渐变的颜色数组
         * @param stops
         * @param tileMode 超过辐射范围半径的区域怎么取颜色，参考ShaderView.java
         */
        radialGradient = new RadialGradient(200,600,150,new int[]{Color.YELLOW,Color.BLUE,Color.RED},null, Shader.TileMode.CLAMP);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setShader(sweepGradient);
        canvas.drawCircle(200,100,100,paint);

        paint.setShader(linearGradient);
        canvas.drawRect(rectF,paint);

        paint.setShader(radialGradient);
        canvas.drawCircle(200,600,200,paint);
    }
}
