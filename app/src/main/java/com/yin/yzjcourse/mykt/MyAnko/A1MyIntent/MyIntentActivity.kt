package com.yin.yzjcourse.mykt.MyAnko.A1MyIntent

import android.os.Bundle
import com.yin.yzjcourse.BaseActivity
import com.yin.yzjcourse.R
import kotlinx.android.synthetic.main.activity_my_intent.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.singleTop
import org.jetbrains.anko.startActivity

/**
 * github官方文档
 * https://github.com/Kotlin/anko/wiki/Anko-Commons-%E2%80%93-Intents
 */
class MyIntentActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_intent)
        initView()
    }

    private fun initView() {
        /**
        正常来说，启动activity的一般写法是这样：
        val intent = Intent(this, SomeOtherActivity::class.java)
        intent.putExtra("id", 5)
        intent.setFlag(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        startActivity(intent)

        Anko可以极大的简化启动Activity的代码，看下面的两个实例：
         */
        //带设置Flag的启动方式，这个startActivity()还是Android API的java方法，只是intentFor()返回Intent实例
        btStart.setOnClickListener {
            startActivity(intentFor<SecontIntentActivity>("one" to 100).singleTop())
        }
        //不用设置Flag时更为简洁，这个startActivity()是一个Context的扩展函数
        btStart2.setOnClickListener {
            startActivity<SecontIntentActivity>("one" to 200)//传参时用到了kotlin的类型推导，因此无需制定传参类型。
            //如果你想传递多个参数可以通过如下方式，逗号分割即可
//            startActivity<SecontIntentActivity>("one" to 200,"name" to "fuck")
        }


//            makeCall("18511625598")//打电话 返回Boolean
//            sendSMS("number", [text])//发短信，返回Boolean
//            browse("http://www.baidu.com")//打开浏览器，返回Boolean
//            share("我是内容","我是子内容")//分享，返回Boolean
//            email(email, [subject], [text])//发邮件
    }
}
