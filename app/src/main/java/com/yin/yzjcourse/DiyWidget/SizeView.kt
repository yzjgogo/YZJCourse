package com.yin.yzjcourse.DiyWidget

import android.content.Context
import android.graphics.Canvas
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import com.yin.yzjcourse.utils.DLog
import org.jetbrains.anko.attr

class SizeView: LinearLayout {
    constructor(context:Context):super(context){

    }
    constructor(context: Context,attrs:AttributeSet):super(context,attrs){

    }
    constructor(context: Context,attrs:AttributeSet,defStyleAttr:Int):super(context,attrs,defStyleAttr){

    }
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs:AttributeSet, defStyleAttr:Int, defStyleRes:Int):super(context,attrs,defStyleAttr,defStyleRes){

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        DLog.eLog("执行：onMeasure")
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)
        DLog.eLog("执行：onLayout")
    }
//    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
////        TODO("Not yet implemented")
//        DLog.eLog("执行：onLayout")
//    }
//    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
//        super.onLayout(changed, left, top, right, bottom)
//        DLog.eLog("执行：onLayout")
//    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        DLog.eLog("执行：onDraw")
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        DLog.eLog("执行：onSizeChanged")
    }
}