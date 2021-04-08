package com.yin.yzjcourse.Jetpack

import android.os.Bundle
import com.yin.yzjcourse.BaseActivity
import com.yin.yzjcourse.R
import com.yin.yzjcourse.utils.DLog

class PageLifecycleActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page_lifecycle)
        bindLocationManager()
    }

    private fun bindLocationManager() {
        val locationManager = PageLocationListener(this, object : PageLocationListener.ILocationChangeListener {
            override fun onChange(longitude: Float, latitude: Float) {
                DLog.eLog(" 打印出位置：$longitude , $latitude")
            }
        })
        lifecycle.addObserver(locationManager)
    }
}