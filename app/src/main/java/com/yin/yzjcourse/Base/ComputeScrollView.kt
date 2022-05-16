package com.yin.yzjcourse.Base

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.Scroller
import androidx.annotation.RequiresApi
import com.yin.yzjcourse.utils.DLog

class ComputeScrollView : ScrollView {

    constructor(context: Context) : super(context) {
        initData(context)
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        initData(context)
    }

    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int) : super(context, attributeSet, defStyleAttr) {
        initData(context)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attributeSet, defStyleAttr, defStyleRes) {
        initData(context)
    }

    private fun initData(context: Context) {
    }

    override fun computeVerticalScrollRange(): Int {
        val count = childCount
        val contentHeight = height - paddingBottom - paddingTop
        if (count == 0) {
            return contentHeight
        }
        var scrollRange = getChildAt(0).bottom
        val scrollY = scrollY
        val overscrollBottom = Math.max(0,scrollRange - contentHeight)
        if(scrollY < 0){
            scrollRange -= scrollY
        }else if(scrollY > overscrollBottom){
            scrollRange += scrollY - overscrollBottom
        }
        return scrollRange
    }

    /**
     * 参考图片：view_compute介绍.jpg
     *
     * 这三个方式都是属于View的方法，子类可根据需要重写，用于计算内容可滚动时一些参数
     */
    fun doCompute(){
        //内容可滚动的view显示区域的高度
        val extent = computeVerticalScrollExtent()
        DLog.eLog("显示区域的高度：$extent，View的高度：$measuredHeight")

        //整个滚动范围的高度，包括没显示出来的，和显示出来的
        val range = computeVerticalScrollRange()
        DLog.eLog("整个滚动范围的高度：$range，第一个子元素(LinearLayout)的高度：${getChildAt(0).measuredHeight},第一个元素的bottom：${getChildAt(0).bottom}")

        //已经滚动的距离
        val offset = computeVerticalScrollOffset()
        DLog.eLog("已经滚动的距离：$offset，获取滚动的距离：$scrollY")
    }
}