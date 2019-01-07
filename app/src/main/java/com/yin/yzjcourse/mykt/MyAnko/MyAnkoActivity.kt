package com.yin.yzjcourse.mykt.MyAnko

import android.os.Bundle
import android.widget.Button
import com.yin.yzjcourse.BaseActivity
import com.yin.yzjcourse.R
import com.yin.yzjcourse.mykt.MyAnko.AlertAndProcess.AlertAndProcessActivity
import com.yin.yzjcourse.mykt.MyAnko.Async.AsyncActivity
import com.yin.yzjcourse.mykt.MyAnko.DB.MyDbActivity
import com.yin.yzjcourse.mykt.MyAnko.MyIntent.MyIntentActivity
import kotlinx.android.synthetic.main.activity_my_anko.*
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity

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
        btAsync.setOnClickListener {
            startActivity<AsyncActivity>()
        }
        btAlert.setOnClickListener {
            startActivity<AlertAndProcessActivity>()
        }
        btIntent.setOnClickListener {
            startActivity<MyIntentActivity>()
        }
        btDb.setOnClickListener {
            startActivity<MyDbActivity>()
        }
    }
}
