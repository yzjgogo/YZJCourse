package com.yin.yzjcourse.mykt.MyAnko.A5Layouts.L5IncludeXMLLayout

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.yin.yzjcourse.BaseActivity
import com.yin.yzjcourse.R
import org.jetbrains.anko.*

class IncludeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /**
         * 加载外部的xml布局到anko布局，泛型可以使View，也可以根据情况是具体的类型，例如
         * 下面的泛型是TextView
         */
        verticalLayout {
            include<View>(R.layout.in_group_item) {
                backgroundColor = Color.RED
            }.lparams(width = matchParent) {
                margin = dip(30)
            }

            include<TextView>(R.layout.in_view_item) {
                text = "我从外部加载而来的哈"
            }
        }
    }
}
