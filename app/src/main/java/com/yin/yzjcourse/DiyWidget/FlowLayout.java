package com.yin.yzjcourse.DiyWidget;

import android.content.Context;
import android.os.Build;
import androidx.annotation.Px;
import androidx.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by think on 2017/8/14.
 */

public class FlowLayout extends ViewGroup {
    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public static class MyLayoutParams extends MarginLayoutParams {

        public MyLayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
        }

        public MyLayoutParams(@Px int width, @Px int height) {
            super(width, height);
        }

        public MyLayoutParams(MarginLayoutParams source) {
            super(source);
        }

        public MyLayoutParams(LayoutParams source) {
            super(source);
        }
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MyLayoutParams(p);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MyLayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MyLayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        int measureHeight = MeasureSpec.getSize(heightMeasureSpec);
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
        int lineWidth = 0;
        int lineHeight = 0;
        int width = 0;
        int height = 0;
        int count = getChildCount();
        for (int i=0;i<count;i++){
            View child = getChildAt(i);
            measureChild(child,widthMeasureSpec,heightMeasureSpec);
            MarginLayoutParams layoutParams = (MarginLayoutParams) child.getLayoutParams();
            int childWidth = child.getMeasuredWidth()+layoutParams.leftMargin+layoutParams.rightMargin;
            int childHeight = child.getMeasuredHeight()+layoutParams.topMargin+layoutParams.bottomMargin;
            if ((lineWidth+childWidth)>measureWidth) {
                //换行后记录下之前所有行的高，和最大宽
                width = Math.max(width,lineWidth);
                height += lineHeight;
                //最后一行在这往后
                lineWidth = childWidth;
                lineHeight = childHeight;
            }else {//没有换行时往后面摆，该行的高取最高的控件
                lineWidth += childWidth;
                lineHeight = Math.max(lineHeight,childHeight);
            }
            if (i == count-1) {//最后一行的宽高还没参与计算
                width = Math.max(lineWidth,width);
                height += lineHeight;
            }
        }
        setMeasuredDimension(modeWidth == MeasureSpec.EXACTLY?measureWidth:width,modeHeight == MeasureSpec.EXACTLY?measureHeight:height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int left = 0;
        int top = 0;
        int count = getChildCount();
        int lineHeight = 0;
        int lineWidth = 0;
        for(int i=0;i<count;i++){
            View child = getChildAt(i);
            MarginLayoutParams layoutParams = (MarginLayoutParams) child.getLayoutParams();
            int childWidth = child.getMeasuredWidth()+layoutParams.leftMargin+layoutParams.rightMargin;
            int childHeight = child.getMeasuredHeight()+layoutParams.topMargin+layoutParams.bottomMargin;
            //下面的if else语句用于动态计算当期那child的left和top
            if((lineWidth+childWidth)>getMeasuredWidth()){//换行
                //换行后left置0，top增加
                left = 0;
                top += lineHeight;
                //初始化新的一行的行宽和行高
                lineWidth = childWidth;
                lineHeight = childHeight;
            }else {
                //主要用于上面判断是否换行
                lineWidth += childWidth;
                //主要用于计算top
                lineHeight = Math.max(lineHeight,childHeight);
            }
            int cl = left+layoutParams.leftMargin;
            int ct = top+layoutParams.topMargin;
            int cr = cl+child.getMeasuredWidth();
            int cb = ct + child.getMeasuredHeight();
            child.layout(cl,ct,cr,cb);
            //同一行left累加
            left += childWidth;
        }
    }
}
