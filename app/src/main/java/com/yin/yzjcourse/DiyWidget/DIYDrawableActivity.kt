package com.yin.yzjcourse.DiyWidget

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import com.yin.yzjcourse.BaseActivity
import com.yin.yzjcourse.R
import kotlinx.android.synthetic.main.activity_diy_drawable.*
import org.jetbrains.anko.backgroundDrawable

/**
 */
class DIYDrawableActivity:BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diy_drawable)

        bt_start.setOnClickListener {
            my_view.backgroundDrawable = MyDrawable(resources.getColor(R.color.actionsheet_blue))
        }

        //二：自定义drawable里使用别的drawable资源
        val useOtherDrawable = UseOtherDrawable(resources.getDrawable(R.drawable.timg))
//        val useOtherDrawable = UseOtherDrawable(resources.getDrawable(R.drawable.timg).mutate())//如果有别的地方也用了timg，为了避免一个地方对timg修改，多个地方被影响，可如此，参考：https://blog.csdn.net/yanbober/article/details/56844869
        imageview6.setImageDrawable(useOtherDrawable)

        //三：基于StateListDrawable(selector)自定义自己的状态
        tv_custom_state.setOnClickListener {
            startActivity(Intent(this, CustomStateDrawableActivity::class.java))
        }

        //四：Drawable关于Callback的学习
        bt_callback.setOnClickListener {
            startActivity(Intent(this, DrawableCallbackActivity::class.java))
        }

    }
}