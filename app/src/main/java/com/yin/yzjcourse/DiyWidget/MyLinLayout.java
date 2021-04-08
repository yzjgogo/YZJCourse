package com.yin.yzjcourse.DiyWidget;

import android.content.Context;
import android.os.Build;
import androidx.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * ViewGroup绘制流程:
 绘制流程分为三步：测量、布局、绘制
 分别对应：onMeasure()、onLayout()、onDraw()

 onMeasure()：测量自己的大小，为正式布局提供建议。（注意，只是建议，至于用不用，要看onLayout）;
 onLayout():使用layout()函数对所有子控件布局；
 onDraw():根据布局的位置绘图；



 getMeasuredWidth()与getWidth()的区别：
 getMeasureWidth()方法在measure()过程结束后就可以获取到了，而getWidth()方法要在layout()过程结束后才能获取到。
 getMeasureWidth()方法中的值是通过setMeasuredDimension()方法来进行设置的，而getWidth()方法中的值则是通过layout(left,top,right,bottom)方法设置的。
 */

public class MyLinLayout extends ViewGroup {
    public MyLinLayout(Context context) {
        super(context);
    }

    public MyLinLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLinLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyLinLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    /**
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     * 这两个参数是父类传递过来给当前view的一个建议值,即想把当前view的尺寸设置为宽widthMeasureSpec,高heightMeasureSpec
     * 虽然表面上看起来他们是int类型的数字，其实他们是由mode+size两部分组成的。
    widthMeasureSpec和heightMeasureSpec转化成二进制数字表示，他们都是32位的。前两位代表mode(测量模式)，后面30位才是他们的实际数值（size）
     mode有三种：
           EXACTLY：一般是在布局文件中设置了明确的值或MATCH_PARENT;
           AT_MOST：表示子布局限制在一个最大值内，一般为WARP_CONTENT;
           UNSPECIFIED：表示子布局想要多大就多大，很少使用;
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //获取尺寸
        int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        int measureHeight = MeasureSpec.getSize(heightMeasureSpec);
        //获取Mode
        int measureWidthMode = MeasureSpec.getMode(widthMeasureSpec);
        int measureHeightMode = MeasureSpec.getMode(heightMeasureSpec);

        int height = 0;
        int width = 0;
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            //测量子控件
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            //获得子控件的高度和宽度
            int childHeight = child.getMeasuredHeight();
            int childWidth = child.getMeasuredWidth();
            //得到最大宽度，并且累加高度
            height += childHeight;
            width = Math.max(childWidth, width);
        }
        //onMeasure()是用来测量当前控件大小的，给onLayout（）提供数值参考，需要特别注意的是：测量完成以后通过setMeasuredDimension(int,int)设置给系统
        setMeasuredDimension((measureWidthMode == MeasureSpec.EXACTLY) ? measureWidth : width, (measureHeightMode == MeasureSpec.EXACTLY) ? measureHeight : height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int top = 0;
        int count = getChildCount();
        for (int i=0;i<count;i++) {

            View child = getChildAt(i);

            int childHeight = child.getMeasuredHeight();
            int childWidth = child.getMeasuredWidth();
            //进去发现，ViewGroup(View)的onLayout方法是在layout方法里被调用的(先设置自身在其父控件的位置，在设置自己的子控件的位置(onLayout))
            child.layout(0, top, childWidth, top + childHeight);
            top += childHeight;
        }
    }
}
