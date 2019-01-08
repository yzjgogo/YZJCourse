package com.yin.yzjcourse.mykt.MyAnko

import android.os.Bundle
import android.widget.Button
import com.yin.yzjcourse.BaseActivity
import com.yin.yzjcourse.R
import com.yin.yzjcourse.mykt.MyAnko.A1MyIntent.MyIntentActivity
import com.yin.yzjcourse.mykt.MyAnko.A2AlertAndProcess.AlertAndProcessActivity
import com.yin.yzjcourse.mykt.MyAnko.A3Logging.MyLoggingActivity
import com.yin.yzjcourse.mykt.MyAnko.A4Async.AsyncActivity
import com.yin.yzjcourse.mykt.MyAnko.A5Layouts.LayoutActivity
import com.yin.yzjcourse.mykt.MyAnko.A6DB.MyDbActivity
import kotlinx.android.synthetic.main.activity_my_anko.*
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity

/**
 * 官网：
 * https://github.com/Kotlin/anko/wiki
 *
 * Anko对如下方面提供了支持（包括但不限于）：
 * Intent相关:[com.yin.yzjcourse.mykt.MyAnko.A1MyIntent]
 * 对话框和进度条：[com.yin.yzjcourse.mykt.MyAnko.A2AlertAndProcess]
 * 输出日志：[com.yin.yzjcourse.mykt.MyAnko.A3Logging]
 * 异步操作：[com.yin.yzjcourse.mykt.MyAnko.A4Async]
 * 布局相关：[com.yin.yzjcourse.mykt.MyAnko.A5Layouts]
 * 数据库操作：[com.yin.yzjcourse.mykt.MyAnko.A6DB]
 *
 */
class MyAnkoActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_anko)
        //Anko提供的findViewById()的替代方法，但是没有kotlin-android-extensions提供的简洁
        val btAsync = find<Button>(R.id.btAsync)
        val btAlert: Button = find(R.id.btAlert)

        initView()
    }

    private fun initView() {
        btIntent.setOnClickListener {
            startActivity<MyIntentActivity>()
        }
        btAlert.setOnClickListener {
            startActivity<AlertAndProcessActivity>()
        }
        btLogging.setOnClickListener {
            startActivity<MyLoggingActivity>()
        }
        btAsync.setOnClickListener {
            startActivity<AsyncActivity>()
        }
        btLayout.setOnClickListener {
            startActivity<LayoutActivity>()
        }
        btDb.setOnClickListener {
            startActivity<MyDbActivity>()
        }
    }
}
