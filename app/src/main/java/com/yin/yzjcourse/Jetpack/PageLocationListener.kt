package com.yin.yzjcourse.Jetpack

import android.app.Activity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.yin.yzjcourse.utils.DLog

class PageLocationListener : LifecycleObserver {
    private val activity: Activity
    private val changeListener: ILocationChangeListener

    constructor(activity: Activity, changeListener: ILocationChangeListener) {
        this.activity = activity
        this.changeListener = changeListener
        initLocationManager()
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun getLocation() {
        DLog.eLog("activity执行onResume时，开始获取位置")
        changeListener.onChange(11.11f, 22.22f)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun stopLocation() {
        release()
    }


    interface ILocationChangeListener {
        fun onChange(longitude: Float, latitude: Float)
    }

    private fun initLocationManager() {
        DLog.eLog("初始化位置管理器")
    }

    private fun release() {
        DLog.eLog("停止获取位置，回收资源")
    }
}