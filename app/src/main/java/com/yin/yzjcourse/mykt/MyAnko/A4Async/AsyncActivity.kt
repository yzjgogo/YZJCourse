package com.yin.yzjcourse.mykt.MyAnko.A4Async

import android.os.Bundle
import com.yin.yzjcourse.BaseActivity
import com.yin.yzjcourse.R
import kotlinx.android.synthetic.main.activity_async.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

class AsyncActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_async)
        test()
    }

    /**
     * 这里涉及到Anko提供的三个方法。
     *
     * doAsync:会开启一个新的县城这行耗时的操作；
     *
     * uiThread:可以从子线程切换到UI线程，需要注意的是，Activity销毁后uiTread不会执行，所以不用在activity销毁时
     *          做特殊的处理，很方便。
     *
     * toast():输出Toast
     *
     */
    private fun test() {
        println("线程1：${Thread.currentThread().name}")//main线程
        btDo.setOnClickListener {
            doAsync {
                var result = doLongTask()
                uiThread {
                    println("线程3：${Thread.currentThread().name}")//main线程
                    toast(result)
                }
            }
        }
    }

    private fun doLongTask(): String {
        println("线程2：${Thread.currentThread().name}")//子线程
        //下载，访问数据库...等耗时操作
        return "耗时操作执行完成"
    }
}
