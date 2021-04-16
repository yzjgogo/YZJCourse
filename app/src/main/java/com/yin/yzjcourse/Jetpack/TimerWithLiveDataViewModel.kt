package com.yin.yzjcourse.Jetpack

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class TimerWithLiveDataViewModel : ViewModel() {
    var timer: Timer? = null

    /**
     * LiveData是一个被观察者，它将ViewModel的数据封装起来，当这些数据有变化时，会通知观察者数据的变化
     * setValue(data) 在UI线程刷新LiveData所维护的ViewModel的数据
     * postValue(data) 在子线程刷新LiveData所维护的ViewModel的数据
     */
    var currentSecond: MutableLiveData<Int>? = null
        get() {
            if (field == null) {
                field = MutableLiveData<Int>()
            }
            return field as MutableLiveData<Int>
        }


    /**
     * ViewModel的数据来源逻辑，可以是网络请求，可以是数据库访问，可以来自sd卡等等，这里模拟数据来自Timer
     */
    fun startTiming() {
        if (timer == null) {
            currentSecond?.value = 0
            timer = Timer()
            val timerTask = object : TimerTask() {
                override fun run() {
                    //数据有更新，因为在子线程所以postValue()
                    currentSecond?.postValue((currentSecond?.value ?: 0) + 1)
                }
            }
            timer?.schedule(timerTask, 1000, 1000)
        }
    }


    /**
     * 与ViewModel关联的Activity最终销毁时会调用该方法，释放资源
     */
    override fun onCleared() {
        timer?.cancel()
        super.onCleared()
    }
}