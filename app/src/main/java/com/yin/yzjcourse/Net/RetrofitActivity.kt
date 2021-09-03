package com.yin.yzjcourse.Net

import android.content.Intent
import android.os.Bundle
import android.view.View
import butterknife.ButterKnife
import butterknife.OnClick
import com.yin.yzjcourse.BaseActivity
import com.yin.yzjcourse.R

class RetrofitActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)
        ButterKnife.bind(this)
    }

    @OnClick(R.id.bt_get)
    fun onViewClicked(view: View) {
        when (view.id) {
            R.id.bt_get -> {
                startActivity(Intent(this, RetrofitGetActivity::class.java))
            }
//            R.id.bt_stop_service -> {
//                stopService(Intent(this, MyLCService::class.java))
//            }
        }
    }
}