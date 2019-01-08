package com.yin.yzjcourse.mykt.MyAnko.A1MyIntent

import android.os.Bundle
import com.yin.yzjcourse.BaseActivity
import com.yin.yzjcourse.R
import org.jetbrains.anko.toast

class SecontIntentActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_secont_intent)
        initView()
    }

    private fun initView() {
        val intent = intent
        toast("接收的数据:${intent.getIntExtra("one",900)}")
    }
}
