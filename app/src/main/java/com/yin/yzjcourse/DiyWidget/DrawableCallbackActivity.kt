package com.yin.yzjcourse.DiyWidget

import android.os.Bundle
import com.yin.yzjcourse.BaseActivity
import com.yin.yzjcourse.R
import com.yin.yzjcourse.utils.DLog
import kotlinx.android.synthetic.main.activity_drawable_callback.*
import java.util.*

/**
 */
class DrawableCallbackActivity : BaseActivity() {
    lateinit var mTimer:Timer
    lateinit var mTimerTask:TimerTask
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawable_callback)
        val bar = CircleLoadingBar()
        mTimer = Timer()
        mTimerTask = object : TimerTask(){
            override fun run() {
//                DLog.eLog("执行了哈哈")
                runOnUiThread{
                    bar.doAnim()
                }
            }
        }
        mTimer.schedule(mTimerTask,0,500)
//        val bar = CircleLoadingBar()
//        Thread(bar).start()
        view_call.setBackgroundDrawable(bar)
    }
}