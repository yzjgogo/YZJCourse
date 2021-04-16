package com.yin.yzjcourse.Jetpack

import androidx.lifecycle.ViewModel
import com.yin.yzjcourse.utils.DLog
import java.util.*

class TimerViewModel:ViewModel() {
    var timer:Timer?=null
    var currentSecond:Int = 0
    lateinit var onTimeChangedListener:OnTimeChangeListener

    fun startTiming(){
        if(timer == null){
            currentSecond = 0
            timer = Timer()
            val timerTask = object:TimerTask(){
                override fun run() {
                    currentSecond++
                    onTimeChangedListener.onTimeChanged(currentSecond)
                }
            }
            timer?.schedule(timerTask,1000,1000)
        }
    }

    override fun onCleared() {
        DLog.eLog("自动执行onCleared")
        timer?.cancel()
        super.onCleared()
    }

    interface OnTimeChangeListener{
        fun onTimeChanged(second:Int)
    }
}