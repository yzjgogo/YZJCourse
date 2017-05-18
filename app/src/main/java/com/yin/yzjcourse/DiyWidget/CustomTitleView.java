package com.yin.yzjcourse.DiyWidget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.yin.yzjcourse.R;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by think on 2017/5/15.
 */

public class CustomTitleView extends View {
    /**
     * 文本
     */
    private String mTitleText;
    /**
     * 文本的颜色
     */
    private int mTitleTextColor;
    /**
     * 文本的大小
     */
    private int mTitleTextSize;

    /**
     * 绘制时控制文本绘制的范围
     */
    private Rect mBound;
    private Paint mPaint;

    public CustomTitleView(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public CustomTitleView(Context context)
    {
        this(context, null);
    }

    /**
     * 获得我自定义的样式属性
     *
     * @param context
     * @param attrs
     * @param defStyle
     */
    public CustomTitleView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        /**
         * 获得我们所定义的自定义样式属性
         */
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomTitleView, defStyle, 0);
        int n = a.getIndexCount();//获取属性的数量
        for (int i = 0; i < n; i++)
        {
            int attr = a.getIndex(i);
            switch (attr)
            {
                case R.styleable.CustomTitleView_titleText1:
                    mTitleText = a.getString(attr);
                    break;
                case R.styleable.CustomTitleView_titleTextColor1:
                    // 默认颜色设置为黑色
                    mTitleTextColor = a.getColor(attr, Color.BLACK);
                    break;
                case R.styleable.CustomTitleView_titleTextSize1:
                    // 默认设置为16sp，TypeValue.applyDimension可以把任何单位转化为px，第一个参数是单位，第二个参数是第一个参数下的值，返回值就是px
                    mTitleTextSize = a.getDimensionPixelSize(attr,
                            (int) TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_SP, 8, getResources().getDisplayMetrics())
                    );//同样是获取布局文件中指定的尺寸，不是8
                    break;

            }

        }
        a.recycle();//不要忘记回收资源

        /**
         * 获得绘制文本的宽和高
         */
        mPaint = new Paint();
        mPaint.setTextSize(mTitleTextSize);
        // mPaint.setColor(mTitleTextColor);
        mBound = new Rect();//Rect表示一块矩形区域
        //用最小的矩形包裹字符串，第一个参数是要测量包裹的字符串；第二个参数是开始测量的字符串的第一个字符的索引；
        // 第三个参数是字符串中最后一个字符的索引加1；第四个参数用于包裹字符串的矩形
        mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mBound);

        this.setOnClickListener(new OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                mTitleText = randomText();
                //界面刷新,可在任意工作线程调用，但是不保证立刻刷新，只是尽可能快的刷新，类似的有invalidate()只能在UI线程调用
                postInvalidate();
            }

        });

    }
    private String randomText()
    {
        Random random = new Random();
        Set<Integer> set = new HashSet<Integer>();
        while (set.size() < 4)
        {
            int randomInt = random.nextInt(10);
            set.add(randomInt);
        }
        StringBuffer sb = new StringBuffer();
        for (Integer i : set)
        {
            sb.append("" + i);
        }

        return sb.toString();
    }
    /**
     * 当我们设置明确的宽度和高度时，系统帮我们测量的结果就是我们设置的结果，
     * 当我们设置为WRAP_CONTENT,或者MATCH_PARENT系统帮我们测量的结果就是MATCH_PARENT的长度。
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        Log.e("yin","执行onMeasure");
        // super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = 0;
        int height = 0;
        /**
         * MeasureSpec封装了父布局传递给子布局的布局要求，每个MeasureSpec代表了一组宽度和高度的要求。
         * MeasureSpec.getMode()的返回值分别是:
         *      EXACTLY：一般是在布局文件中设置了明确的值或MATCH_PARENT;
         *      AT_MOST：表示子布局限制在一个最大值内，一般为WARP_CONTENT;
         *      UNSPECIFIED：表示子布局想要多大就多大，很少使用;
         * MeasureSpec.getSize()获取宽或高的大小。
         *
         * padding：视图内容和视图边框的距离叫补距，测量视图是需考虑上下左右的补距离
         */

        /**
         * 设置宽度
         */
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);//单位是像素，不包括补距离padding
        Log.e("yin","宽的："+widthSpecMode+","+MeasureSpec.EXACTLY+","+MeasureSpec.AT_MOST);
        switch (widthSpecMode)
        {
            case MeasureSpec.EXACTLY:// 明确指定了
                Log.e("yin","执行宽EXACTLY");
                width = getPaddingLeft() + getPaddingRight() + widthSpecSize;
                break;
            case MeasureSpec.AT_MOST:// 一般为WARP_CONTENT
                Log.e("yin","执行宽AT_MOST");
                width = getPaddingLeft() + getPaddingRight() + mBound.width();//用于存放字符串的矩形的宽
                break;
        }
        Log.e("yin","第1");
        /**
         * 设置高度
         */
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        Log.e("yin","第2："+heightSpecMode+","+MeasureSpec.EXACTLY+","+MeasureSpec.AT_MOST+","+MeasureSpec.UNSPECIFIED);
        switch (heightSpecMode)
        {
            case MeasureSpec.EXACTLY:// 明确指定了
                Log.e("yin","执行高EXACTLY");
                height = getPaddingTop() + getPaddingBottom() + heightSpecSize;
                break;
            case MeasureSpec.AT_MOST:// 一般为WARP_CONTENT
                Log.e("yin","执行高AT_MOST");
                height = getPaddingTop() + getPaddingBottom() + mBound.height();
                break;
        }
        //设置view的大小,方法内部会给measuredWidth和measuredHeight赋值
        setMeasuredDimension(width, height);

    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        Log.e("yin","执行onDraw");
        mPaint.setColor(Color.YELLOW);
//      一般在自定义控件的时候getMeasuredWidth/getMeasuredHeight它的赋值在View的setMeasuredDimension中，
//      所以有时可以在onMeasure方法中看到利用getMeasuredWidth/getMeasuredHeight初始化别的参数。
//      而getWidth/getHeight一直在onLayout完成后才会被赋值。一般情况下，如果都完成了赋值，两者值是相同的
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);

        mPaint.setColor(mTitleTextColor);
        //在当前view中画出文本，drawText画文本的起点坐标是文本的左下角
        canvas.drawText(mTitleText, getWidth() / 2 - mBound.width() / 2, getHeight() / 2 + mBound.height() / 2, mPaint);
    }
}
