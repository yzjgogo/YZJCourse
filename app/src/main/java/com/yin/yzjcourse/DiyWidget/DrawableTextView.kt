package com.yin.yzjcourse.DiyWidget

import android.content.Context
import android.graphics.Canvas
import android.os.Build
import android.util.AttributeSet
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatTextView
import com.yin.yzjcourse.R
import com.yin.yzjcourse.utils.DLog

/**
 * 给自定义View使用自定义Drawable，参考MyDrawable.kt
 * 主要是在核实的位置，使用如下两步：
 * 第一步：设置drawable的边界
 * 第二步：绘制drawable
 */
class DrawableTextView:AppCompatTextView {
    lateinit var mDrawable:MyDrawable

    constructor(context: Context) : super(context) {
        initData(context)
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        initData(context)
    }

    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int) : super(context, attributeSet, defStyleAttr) {
        initData(context)
    }

    private fun initData(context: Context) {
        mDrawable = MyDrawable(resources.getColor(R.color.nav_bg))
    }

    /**
     * onMeasure之后能拿到宽高，但是拿不到left,top,right,bottom
     * 因此mDrawable.setBounds(0,0,measuredWidth,measuredHeight)设置边界可以
     * 注意，drawable的边界是相对View的，一次如果没有特殊需要前两个参数是(0,0)，这个drawable才能跟View的左上角重合
     */
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        DLog.eLog("view的4个参数-1:$left,$top,$right,$bottom,$measuredWidth,$measuredHeight")
//        mDrawable.setBounds(0,0,measuredWidth,measuredHeight)
    }

    /**
     * onLayout之后既能拿到宽高，又能拿到left,top,right,bottom
     * 因此mDrawable.setBounds(0,0,right,bottom-top)设置边界可以
     * 注意，drawable的边界是相对View的，一次如果没有特殊需要前两个参数是(0,0)，这个drawable才能跟View的左上角重合
     */
    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        DLog.eLog("view的4个参数-2:$left,$top,$right,$bottom,$measuredWidth,$measuredHeight")
        mDrawable.setBounds(0,0,right,bottom-top)
    }

    /**
     * 在绘制view时，出发drawable的绘制
     */
    override fun onDraw(canvas: Canvas?) {
        mDrawable.draw(canvas!!)
        super.onDraw(canvas)
    }
}