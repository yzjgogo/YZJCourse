package com.yin.yzjcourse.mykt

import android.content.Intent
import android.os.Bundle
import com.yin.yzjcourse.BaseActivity
import com.yin.yzjcourse.R
import com.yin.yzjcourse.mykt.AndroidExtensions.ExtensionActivity
import kotlinx.android.synthetic.main.activity_my_kt.*

class MyKtActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_kt)
        initView()
    }

    private fun initView() {
        btExtension.setOnClickListener {
            startActivity(Intent(this, ExtensionActivity::class.java))
        }
    }
}
