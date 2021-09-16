package com.yin.yzjcourse.Net

import android.os.Bundle
import android.view.View
import androidx.work.WorkManager
import butterknife.ButterKnife
import butterknife.OnClick
import com.yin.yzjcourse.BaseActivity
import com.yin.yzjcourse.Jetpack.work.WORK_TAG
import com.yin.yzjcourse.R
import com.yin.yzjcourse.utils.DLog

class ThreadActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thread)
        ButterKnife.bind(this)
    }

    @OnClick(R.id.bt_stop)
    fun onViewClicked(view: View) {
        when (view.id) {
            R.id.bt_stop -> {
                Thread {
                    DLog.eLog("")
                }.start()
            }
//            R.id.bt_work_info -> {
//
//            }
        }
    }
}