package com.yin.yzjcourse.mykt.MyAnko.A5Layouts.L2Separate

import android.os.Bundle
import com.yin.yzjcourse.BaseActivity
import org.jetbrains.anko.setContentView

/**
 * 与[com.yin.yzjcourse.mykt.MyAnko.A5Layouts.L1Direcyly.MyLayoutDirectlyActivity]的区别：
 *
 * 将布局代码抽离出来放到一个独立的布局类[MyActivityUI]类中，再在activity中加载该布局类，无需再用原来的
 * setContentView().
 */
class ComponentActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_componen) 这个不用
        MyActivityUI().setContentView(this)
    }
}
