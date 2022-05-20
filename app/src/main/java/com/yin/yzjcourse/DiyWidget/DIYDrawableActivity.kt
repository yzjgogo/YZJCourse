package com.yin.yzjcourse.DiyWidget

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
    }
}