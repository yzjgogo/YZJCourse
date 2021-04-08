package com.yin.yzjcourse.Jetpack

import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.lifecycle.LifecycleService
import com.yin.yzjcourse.utils.DLog

class MyLCService : LifecycleService {
    constructor() : super(){
        val locationManager = ServiceLocationListener(this,object :ServiceLocationListener.ILocationChangeListener{
            override fun onChange(longitude: Float, latitude: Float) {
                DLog.eLog("服务监听到位置变化:$longitude , $latitude")
            }
        })
        lifecycle.addObserver(locationManager)
    }
}