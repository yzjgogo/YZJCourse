package com.yin.yzjcourse.DiyWidget;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Px;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.yin.yzjcourse.utils.DLog;

public class MyMarginLinLayout extends ViewGroup {
    public MyMarginLinLayout(Context context) {
        super(context);
    }

    public MyMarginLinLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyMarginLinLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyMarginLinLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    /**
     LayoutParams存储了子View在加入ViewGroup中时的一些参数信息，是给子View用的，比如button.setLayoutParams,在继承ViewGroup类时，一般也需要新建一个新的LayoutParams类，
     就像SDK中我们熟悉的LinearLayout.LayoutParams，RelativeLayout.LayoutParams类等一样
     */
    public static class LayoutParams extends ViewGroup.MarginLayoutParams {

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
        }

        public LayoutParams(@Px int width, @Px int height) {
            super(width, height);
        }

        public LayoutParams(MarginLayoutParams source) {
            super(source);
        }

        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
        }
    }

    @Override
    protected android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams p) {
        DLog.eLog("generate_p");
        return new MyMarginLinLayout.LayoutParams(p);
    }

    /**
     * 执行了这个方法
     *
     * @param attrs
     * @return
     */
    @Override
    public android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        DLog.eLog("generate_attrs");
        return new MyMarginLinLayout.LayoutParams(getContext(), attrs);
    }

    @Override
    protected android.view.ViewGroup.LayoutParams generateDefaultLayoutParams() {
        DLog.eLog("generate_default");
        return new MarginLayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT);
    }

    /**
     * 判断当前LayoutParams是不是当前ViewGroup的LayoutParams的实例
     * @param p
     * @return
     */
    @Override
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return p instanceof MyMarginLinLayout.LayoutParams;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        DLog.eLog("onMeasure");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        int measureHeight = MeasureSpec.getSize(heightMeasureSpec);
        int measureWidthMode = MeasureSpec.getMode(widthMeasureSpec);
        int measureHeightMode = MeasureSpec.getMode(heightMeasureSpec);

        int height = 0;
        int width = 0;
        int count = getChildCount();
        for (int i = 0; i < count; i++) {

            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            //获取margin
            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();//看上面我们定义的LayoutParams
            int childHeight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
            int childWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;

            height += childHeight;
            width = Math.max(childWidth, width);
        }
        setMeasuredDimension((measureWidthMode == MeasureSpec.EXACTLY) ? measureWidth : width, (measureHeightMode == MeasureSpec.EXACTLY) ? measureHeight : height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        DLog.eLog("onLayout");
        int top = 0;
        int count = getChildCount();
        for (int i = 0; i < count; i++) {

            View child = getChildAt(i);

            //获取margin
            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();//看上面我们定义的LayoutParams
            int childHeight = child.getMeasuredHeight();
            int childWidth = child.getMeasuredWidth();
            top += lp.topMargin;//margin区域不是控件的区域因此不能覆盖该区域
            child.layout(lp.leftMargin, top, childWidth + lp.leftMargin, top + childHeight);
            top += childHeight;
        }
    }
}
