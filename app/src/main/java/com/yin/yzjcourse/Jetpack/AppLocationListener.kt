package com.yin.yzjcourse.Jetpack

import android.app.Service
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.yin.yzjcourse.utils.DLog

class AppLocationListener : LifecycleObserver {
    private var service: Service? = null
    private var changeListener: ILocationChangeListener? = null

    constructor() {
        initLocationManager()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun doCreate() {
        DLog.eLog("app执行onCreate时")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun doStart() {
        DLog.eLog("app执行onStart时")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun doResume() {
        DLog.eLog("app执行onResume时")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun doPause() {
        DLog.eLog("app执行onPause时")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun doStop() {
        DLog.eLog("app执行onStop时")
    }

    /**
     * 永远不会调用，Application不会下发onDestroy事件
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun doDestroy() {
        DLog.eLog("app执行onDestroy时")
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