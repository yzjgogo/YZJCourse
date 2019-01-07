package com.yin.yzjcourse.mykt.AndroidExtensions.E3UseInDiyView

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.yin.yzjcourse.R
import kotlinx.android.synthetic.main.my_diy_view.view.*

/**
 * 自定义控件时，自己的类继承已存在的java中的View类时，可通过注解继承，自动生成继承的代码，
 * 具体请参考:kot_ext_java.png
 *
 * 自定义控件中使用kotlin-android-extensions,与activity中类似，也需要导入。
 * 具体参考java
 *
 * 这里用到了init()很方便。
 */
class MyDiyView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.my_diy_view, this, true)
        tvDiy.text = "Hello Kotlin!"
    }
}