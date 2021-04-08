package com.yin.yzjcourse.Jetpack

import android.content.Intent
import android.os.Bundle
import android.view.View
import butterknife.ButterKnife
import butterknife.OnClick
import com.yin.yzjcourse.BaseActivity
import com.yin.yzjcourse.R
import com.yin.yzjcourse.utils.DLog

class ServiceLifecycleActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_lifecycle)
        ButterKnife.bind(this)
    }

    @OnClick(R.id.bt_start_service, R.id.bt_stop_service)
    fun onViewClicked(view: View) {
        when (view.id) {
            R.id.bt_start_service -> {
                startService(Intent(this,MyLCService::class.java))
            }
            R.id.bt_stop_service -> {
                stopService(Intent(this,MyLCService::class.java))
            }
        }
    }
}