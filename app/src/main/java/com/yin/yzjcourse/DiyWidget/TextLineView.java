package com.yin.yzjcourse.DiyWidget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import com.yin.yzjcourse.utils.DLog;

/**
 * Created by think on 2018/3/28.
 */

public class TextLineView extends View {
    private Paint paint;//这个是用于画文字的
    private String content = "Good Morning";
    private Paint linePaint;
    private Paint baseLinePaint;
//    private Paint.Align align = Paint.Align.LEFT;

    public TextLineView(Context context) {
        super(context);
        initView();
    }

    public TextLineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public TextLineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TextLineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private void initView() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextSize(100);
        paint.setColor(Color.RED);
        paint.setTextAlign(Paint.Align.LEFT);

        linePaint = new Paint();
        linePaint.setColor(Color.RED);
        linePaint.setAntiAlias(true);

        baseLinePaint = new Paint();
        baseLinePaint.setColor(Color.parseColor("#3F51B5"));
        baseLinePaint.setAntiAlias(true);
    }
    public void setAlign(Paint.Align newAlign){
        paint.setTextAlign(newAlign);
        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int baseLineY = 200;
        int baseLineX = 50;
        ///////////////////////////////////////////////////

        //画出top，ascent，descent，bottom，基线这5条线和绘制点
        Paint.FontMetricsInt fm = paint.getFontMetricsInt();
        int top = baseLineY + fm.top;
        int ascent = baseLineY + fm.ascent;
        int descent = baseLineY + fm.descent;
        int bottom = baseLineY + fm.bottom;
        canvas.drawLine(0, top, 3000, top, linePaint);
        canvas.drawLine(0, ascent, 3000, ascent, linePaint);
        canvas.drawLine(0, descent, 3000, descent, linePaint);
        canvas.drawLine(0, bottom, 3000, bottom, linePaint);
        canvas.drawLine(0, baseLineY, 3000, baseLineY, baseLinePaint);
        canvas.drawCircle(baseLineX, baseLineY, 5, baseLinePaint);

        ///////////////////////////////////////////////////

        //画整个文本所占的区域(包裹top,bottom线的大矩形)
        //整个文本的高时top到bottom线的距离
        int width = (int) paint.measureText(content);//这个文本的宽
        Paint.Align align = paint.getTextAlign();
        paint.setColor(Color.parseColor("#7FFF4081"));//粉色
        if (align.equals(Paint.Align.LEFT)) {
            canvas.drawRect(new Rect(baseLineX, top, baseLineX + width, bottom), paint);
        } else if (align.equals(Paint.Align.CENTER)) {
            canvas.drawRect(new Rect(baseLineX - width / 2, top, baseLineX + width / 2, bottom), paint);
        } else if (align.equals(Paint.Align.RIGHT)) {
            canvas.drawRect(new Rect(baseLineX - width, top, baseLineX, bottom), paint);
        }

        ///////////////////////////////////////////////////

        //画包裹文本的最小矩形,比上面说的text所占区域小，四周都有缝隙
        paint.setColor(Color.parseColor("#7F15bb76"));//绿色
        //这样获取到的最小矩形minRect默认是以(0,0)绘制点、以x轴为基线、Align.Left的文本的矩形，
        // 注意这里最小矩形的left并不是0，因为最小矩形比文本所占区域的矩形小，有点缝隙,这一点很重要
        Rect minRect = new Rect();
        paint.getTextBounds(content,0,content.length(),minRect);
        //将默认最小矩形，转化为实际最小矩形
        int minLenght = minRect.right-minRect.left;//最小矩形的宽度
        int defaultMinLeft = minRect.left;//注意这里最小矩形的left并不是0，因为最小矩形比文本所占区域的矩形小，有点缝隙,这一点很重要
        DLog.eLog("最小矩形的长1："+(minRect.right-minRect.left)+","+minRect.toShortString()+","+defaultMinLeft);
        //默认最小矩形以x轴为基线，所以实际最小矩形的top和bottom需移动到baseLineY的位置
        minRect.top = minRect.top+baseLineY;
        minRect.bottom = minRect.bottom+baseLineY;
        if (align.equals(Paint.Align.LEFT)) {
            //因为 默认最小矩形以(0,0)为绘制点，而实际最小矩形以(baseLineX,baseLineY)为绘制点，所以这样
            minRect.left = minRect.left+baseLineX;
            minRect.right = minRect.right+baseLineX;
            DLog.eLog("最小矩形的长2："+(minRect.right-minRect.left)+","+minRect.toShortString());
        } else if (align.equals(Paint.Align.CENTER)) {
            minRect.left = baseLineX-minLenght/2;
            minRect.right = baseLineX+minLenght/2;
            DLog.eLog("最小矩形的长3："+(minRect.right-minRect.left)+","+minRect.toShortString());
        } else if (align.equals(Paint.Align.RIGHT)) {
            //注意这里最小矩形的left并不是0，因为最小矩形比文本所占区域的矩形小，有点缝隙,这一点很重要
            //width:这里用到了文本所占最大区域的矩形的width
            minRect.left = baseLineX-width+defaultMinLeft;
            minRect.right = baseLineX-width+defaultMinLeft+minLenght;
            DLog.eLog("最小矩形的长4："+(minRect.right-minRect.left)+","+minRect.toShortString());
        }
        canvas.drawRect(minRect,paint);

        ///////////////////////////////////////////////////

        //画文字
        paint.setColor(Color.BLACK);
        canvas.drawText(content,baseLineX,baseLineY,paint);
    }
}
