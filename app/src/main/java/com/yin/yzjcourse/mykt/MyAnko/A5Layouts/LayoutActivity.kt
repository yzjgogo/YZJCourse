package com.yin.yzjcourse.mykt.MyAnko.A5Layouts

import android.os.Bundle
import com.yin.yzjcourse.BaseActivity
import com.yin.yzjcourse.R
import com.yin.yzjcourse.mykt.MyAnko.A5Layouts.L1Direcyly.MyLayoutDirectlyActivity
import com.yin.yzjcourse.mykt.MyAnko.A5Layouts.L2Separate.ComponentActivity
import com.yin.yzjcourse.mykt.MyAnko.A5Layouts.L4LayoutParams.ParamsActivity
import com.yin.yzjcourse.mykt.MyAnko.A5Layouts.L5IncludeXMLLayout.IncludeActivity
import com.yin.yzjcourse.mykt.MyAnko.A5Layouts.L3ResourcesAndDimensions.ResDimeActivity
import kotlinx.android.synthetic.main.activity_layoutctivity.*
import org.jetbrains.anko.ctx
import org.jetbrains.anko.startActivity


/**
正常情况下在代码中写布局类似下面这样，很复杂，也不易阅读：
val act = this
val layout = LinearLayout(act)
layout.orientation = LinearLayout.VERTICAL
val name = EditText(act)
val button = Button(act)
button.text = "Say Hello"
button.setOnClickListener {
Toast.makeText(act, "Hello, ${name.text}!", Toast.LENGTH_SHORT).show()
}
layout.addView(name)
layout.addView(button)

但是用Anko，可以很优雅很简洁很易读的这样写：
verticalLayout {
val name = editText()
button("Say Hello") {
onClick { toast("Hello, ${name.text}!") }
}
}

上面是在没有布局的情况下，如果有已经存在的布局，也可以引用已存在的控件：
val name = find<TextView>(R.id.name) //类似findViewById()，没有kotlin-android-extensions简洁
name.hint = "Enter your name"
name.onClick { /*do something*/ }


 更多布局方面控件方面的举例，参考github官方：https://github.com/Kotlin/anko/wiki/Anko-Layouts-Examples

 anko的在布局方面提供了如下支持:
 直接在Activity的onCreate()加载布局:[com.yin.yzjcourse.mykt.MyAnko.A5Layouts.L1Direcyly]
 单独的布局类，再在Activity中加载该布局类：[com.yin.yzjcourse.mykt.MyAnko.A5Layouts.L2Separate]
 布局参数：[com.yin.yzjcourse.mykt.MyAnko.A5Layouts.L4LayoutParams]
 加载xml布局到anko布局：[com.yin.yzjcourse.mykt.MyAnko.A5Layouts.L5IncludeXMLLayout]
 Context的获取方式以及Fragement中Activity的获取方式(本也的button写了一下，没有页面)

 以上设计到了操作控件的各个方面，就好像是一套anko版本AndroidUI API。
 */
class LayoutActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layoutctivity)
        initView()
    }

    private fun initView() {
        btDirect.setOnClickListener {
            startActivity<MyLayoutDirectlyActivity>()
        }
        btSeparate.setOnClickListener {
            startActivity<ComponentActivity>()
        }
        btRes.setOnClickListener {
            startActivity<ResDimeActivity>()
        }
        btParas.setOnClickListener {
            startActivity<ParamsActivity>()
        }
        btInclude.setOnClickListener {
            startActivity<IncludeActivity>()
        }
    }
}
