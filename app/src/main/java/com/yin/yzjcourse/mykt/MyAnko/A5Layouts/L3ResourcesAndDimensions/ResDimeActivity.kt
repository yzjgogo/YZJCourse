package com.yin.yzjcourse.mykt.MyAnko.A5Layouts.L3ResourcesAndDimensions

import android.os.Bundle
import com.yin.yzjcourse.BaseActivity
import com.yin.yzjcourse.R

/**
    颜色：
    0xff0000.opaque --> 不透明的红色
    0x99.gray.opaque --> non-transparent #999999 gray

    尺寸：
    dip(dipValue) --> 返回dip值，例如:dip(20)返回20dp
    sp(spValue) --> 返回sp值，例如：sp(20)返回20sp

    textSize属性：
    给textSize赋值时，无需带单位，默认 已经是sp，例如tv.textSize = 20f,就是20sp，f应该是Float

    单位转换：
    px2dip(20) --> 20px转为dip
    px2sp(20) --> 20px转为sp

 */
class ResDimeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_res_dime)
    }
}
