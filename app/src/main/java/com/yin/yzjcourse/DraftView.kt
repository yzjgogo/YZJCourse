package com.yin.yzjcourse

import android.content.Context
import android.graphics.*
import android.os.Build
import androidx.annotation.RequiresApi
import android.util.AttributeSet
import android.util.Pair
import android.view.View
import com.yin.yzjcourse.utils.DLog

class DraftView:View {
    val path:Path = Path()
    var paint:Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var group_left_shoulder_corner_scale:android.util.Pair<Float,Float>? = Pair(1.1f,2.2f)
    constructor(context:Context):super(context){
        initView(context)
    }

    constructor(context: Context,attrs: AttributeSet):super(context,attrs){
        initView(context)
    }
    constructor(context: Context, attrs: AttributeSet, defStyleAttr:Int):super(context,attrs,defStyleAttr){
        initView(context)
    }
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr:Int, defStyleRes:Int):super(context,attrs,defStyleAttr,defStyleRes){
        initView(context)
    }

    private fun initView(context: Context) {
        path.addCircle(200.0f,100.0f,100.0f,Path.Direction.CCW)
        paint.color = Color.BLUE
        paint.style = Paint.Style.FILL
    }

    override fun onFinishInflate() {
        DLog.eLog("执行onFinishInflate:$measuredWidth,$measuredHeight")
        super.onFinishInflate()
    }

    override fun onDraw(canvas: Canvas?) {
//        group_left_shoulder_corner_scale = null
//        DLog.eLog("dd:"+group_left_shoulder_corner_scale.toString())
////        println(group_left_shoulder_corner_scale)
//
//        val result = group_left_shoulder_corner_scale?.first
//        DLog.eLog("null的属性:$result")

        group_left_shoulder_corner_scale?.let {
            println(it.first)
        }

        group_left_shoulder_corner_scale?.first?.let {
            DLog.eLog("进入方法了:$it")
//            DLog.eLog(it.toString())}
        }
        DLog.eLog("执行onDraw")
        super.onDraw(canvas)
        canvas?.save()
        canvas?.clipPath(path,Region.Op.XOR)
        canvas?.restore()
        canvas?.drawRect(0.0f,0.0f,measuredWidth*1.0f,measuredHeight*1.0f,paint)
    }
}