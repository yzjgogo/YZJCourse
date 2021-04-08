package com.yin.yzjcourse.Jetpack

import android.app.Activity
import android.app.Service
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.yin.yzjcourse.utils.DLog

class ServiceLocationListener : LifecycleObserver {
    private val service: Service
    private val changeListener: ILocationChangeListener

    constructor(service: Service, changeListener: ILocationChangeListener) {
        this.service = service
        this.changeListener = changeListener
        initLocationManager()
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun getLocation() {
        DLog.eLog("service执行onCreate时，开始获取位置")
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