package com.yin.yzjcourse.Base

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.widget.ScrollView
import android.widget.Scroller
import androidx.annotation.RequiresApi

class MyScrollView : ScrollView {
    lateinit var scroller: Scroller

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
        scroller = Scroller(context)
    }

    fun smoothScrollToDiy(destX: Int, destY: Int) {
        //当前滚动了的距离
        val mScrollY = scrollY
        //目标要滚动的距离，减去当前已经滚动了的距离，等于还需要滚动的距离
        val deltaY = destY - mScrollY
        //第1，2个参数是x，y轴当前已经滚动了的距离；第3，4个参数是x，y轴还需要滚动的距离；第5个参数是动画时间
        scroller.startScroll(0, mScrollY, 0, deltaY, 2000)
        //去触发view的draw()，再由draw()触发computeScroll()
        invalidate()
    }


    /**
     * view的绘制draw()方法会调用computeScroll()
     */
    override fun computeScroll() {
        //computeScrollOffset用于计算随着时间的六十，每一个小的时间区间，需要滚动到的距离，然后有下面的scrollTo(scroller.currX, scroller.currY)去执行，这样连续起来就是动画了
        //computeScrollOffset()返回true说明还没滚动完目标距离，要继续执行滚动
        if (scroller.computeScrollOffset()) {
            scrollTo(scroller.currX, scroller.currY)
            //又会触发draw()，draw()又会触发computeScroll()因此形成连续的滚动
            postInvalidate()
        }
    }
}