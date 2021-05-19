package com.yin.yzjcourse.DiyWidget

import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.annotation.RequiresApi
import com.yin.yzjcourse.Jetpack.MyLCService
import com.yin.yzjcourse.R
import com.yin.yzjcourse.utils.DLog

class MultiPointView :View {
    constructor(context:Context):super(context){
        initData(context);
    }

    constructor(context:Context,attrs: AttributeSet):super(context,attrs){
        initData(context);
    }
    constructor(context:Context,attrs: AttributeSet,defStyleAttr:Int):super(context,attrs,defStyleAttr){
        initData(context);
    }
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context:Context, attrs: AttributeSet, defStyleAttr:Int, defStyleRes:Int):super(context,attrs,defStyleAttr,defStyleRes){
        initData(context);
    }


    private fun initData(context: Context) {

    }

    /**
     * actionIndex:
     * 此时此刻这个event对应的手指在屏幕上所有手指的索引，此时此刻屏幕上有n个手指，actionIndex范围就是0-(n-1),可见对于这个手指从action_down到action_up,actionIndex的值可能会发生改变；
     * 因为在这个手指在action_down到action_up之间可能有其它手指action_up或action_down,这样这个手指的actionIndex就会改变；
     * 特点：
     * 1：在action_move中，actionIndex始终是0；因此在action_move中不要使用actionIndex区分手指；
     * 2：无论什么时候获取actionIndex,其值总是此时此刻，对应手指在屏幕上所有手指的索引；
     * 3：当有新的手指action_down时，如果之前有某一个或多个手指离开过，则这个新的手指会复用离开的手指中actionIndex靠近0的那个actionIndex、pointerId;否则actionIndex、pointerId往后追加；
     *
     * pointerIndex：
     * 与actionIndex相同，区别就是在action_move中pointerIndex仍然能获取正确的值
     *
     * pointerId：
     * 从action_down到action_up(action_cancel)，对应的手指的pointerId是不变的，不会随着有其它手指的加入或离开而改变，这是与actionIndex的区别；
     */
//    /*
    override fun onTouchEvent(event: MotionEvent?): Boolean {
//        与 getAction() 类似，多点触控必须使用这个方法获取事件类型。
        when (event?.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                DLog.eLog("执行        ACTION_DOWN:actionIndex："+event.actionIndex+"，pointerId:"+event.getPointerId(event.actionIndex)+" ，pointerCount："
                        +event.pointerCount+"，RawX："+event.rawX+"，RawY："+event.rawY+"，x："+event.x+"，y："+event.y+"，x-p："+event.getX(event.actionIndex)+"，y-p："+event.getY(event.actionIndex))
//                DLog.eLog("执行ACTION_DOWN:actionIndex："+event.actionIndex+"，pointerIndex:"+event.findPointerIndex(event.getPointerId(event.actionIndex))+"，pointerId:"+event.getPointerId(event.actionIndex)+" ，pointerCount："
//                        +event.pointerCount+"，RawX："+event.rawX+"，RawY："+event.rawY+"，x："+event.x+"，y："+event.y+"，x-p："+event.getX(event.actionIndex)+"，y-p："+event.getY(event.actionIndex))
//                DLog.eLog("执行ACTION_DOWN:actionIndex："+event.actionIndex+"，pointerIndex:"+event.findPointerIndex(event.getPointerId(event.actionIndex))+"，pointerId:"+event.getPointerId(event.actionIndex)+" ，pointerCount："
//                        +event.pointerCount+"，historySize："+event.historySize+"，DownTime："+event.downTime+"，EventTime："+event.eventTime)
            }
            MotionEvent.ACTION_POINTER_DOWN -> {
                DLog.eLog("执行ACTION_POINTER_DOWN:actionIndex："+event.actionIndex+"，pointerId:"+event.getPointerId(event.actionIndex)+" ，pointerCount："
                        +event.pointerCount+"，RawX："+event.rawX+"，RawY："+event.rawY+"，x："+event.x+"，y："+event.y+"，x-p："+event.getX(event.actionIndex)+"，y-p："+event.getY(event.actionIndex))
//                DLog.eLog("执行ACTION_POINTER_DOWN:actionIndex："+event.actionIndex+"，pointerIndex:"+event.findPointerIndex(event.getPointerId(event.actionIndex))+"，pointerId:"+event.getPointerId(event.actionIndex)+" ，pointerCount："
//                        +event.pointerCount+"，historySize："+event.historySize+"，DownTime："+event.downTime+"，EventTime："+event.eventTime)
            }
            MotionEvent.ACTION_MOVE -> {
//                DLog.eLog("执行ACTION_MOVE:actionIndex："+event.actionIndex+"，pointerId:"+event.getPointerId(event.pointerCount-1)+" ，pointerCount："
//                        +event.pointerCount+"，historySize："+event.historySize+"，DownTime："+event.downTime+"，EventTime："+event.eventTime)
//                DLog.eLog("-----------------------------------------------------")
//                  for(index in 0 until event.pointerCount){
//                    DLog.eLog("pointIndex:$index , pointerId:${event.getPointerId(index)}")
//                  }
            }
            MotionEvent.ACTION_UP -> {
                DLog.eLog("执行          ACTION_UP:actionIndex："+event.actionIndex+"，pointerId:"+event.getPointerId(event.actionIndex)+" ，pointerCount："
                        +event.pointerCount+"，RawX："+event.rawX+"，RawY："+event.rawY+"，x："+event.x+"，y："+event.y+"，x-p："+event.getX(event.actionIndex)+"，y-p："+event.getY(event.actionIndex))
//                DLog.eLog("执行ACTION_UP:actionIndex："+event.actionIndex+"，pointerIndex:"+event.findPointerIndex(event.getPointerId(event.actionIndex))+"，pointerId:"+event.getPointerId(event.actionIndex)+" ，pointerCount："
//                        +event.pointerCount+"，historySize："+event.historySize+"，DownTime："+event.downTime+"，EventTime："+event.eventTime)
            }
            MotionEvent.ACTION_POINTER_UP -> {
                DLog.eLog("执行  ACTION_POINTER_UP:actionIndex："+event.actionIndex+"，pointerId:"+event.getPointerId(event.actionIndex)+" ，pointerCount："
                        +event.pointerCount+"，RawX："+event.rawX+"，RawY："+event.rawY+"，x："+event.x+"，y："+event.y+"，x-p："+event.getX(event.actionIndex)+"，y-p："+event.getY(event.actionIndex))
//                DLog.eLog("执行ACTION_POINTER_UP:actionIndex："+event.actionIndex+"，pointerIndex:"+event.findPointerIndex(event.getPointerId(event.actionIndex))+"，pointerId:"+event.getPointerId(event.actionIndex)+" ，pointerCount："
//                        +event.pointerCount+"，historySize："+event.historySize+"，DownTime："+event.downTime+"，EventTime："+event.eventTime)
            }
        }
        return true
    }
//    */
    /*
    var mLastTouchX:Float = 0.0f;
    var mLastTouchY:Float = 0.0f;
    override fun onTouchEvent(event: MotionEvent?): Boolean {
//        与 getAction() 类似，多点触控必须使用这个方法获取事件类型。
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                mLastTouchX = event.x
                mLastTouchY = event.y
//                mLastTouchX = event.rawX
//                mLastTouchY = event.rawY
            }
            MotionEvent.ACTION_MOVE -> {
                setTranslationX(getTranslationX()+(event.x-mLastTouchX))
                setTranslationY(getTranslationY()+(event.y-mLastTouchY))

                /*
                var curRawX = event.rawX
                var curRawY = event.rawY

                var xDistance = curRawX - mLastTouchX
                var yDistance = curRawY - mLastTouchY

                mLastTouchX =curRawX
                mLastTouchY = curRawY

                setTranslationX(getTranslationX()+xDistance)
                setTranslationY(getTranslationY()+yDistance)
                */



            }
            MotionEvent.ACTION_UP -> {

            }
        }
        return true
    }
    */
}