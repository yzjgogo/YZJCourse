package com.yin.yzjcourse.mykt.AndroidExtensions.E1UseInActivity

import android.os.Bundle
import com.yin.yzjcourse.BaseActivity
import com.yin.yzjcourse.R
import kotlinx.android.synthetic.main.activity_fir.*
import kotlinx.android.synthetic.main.activity_fir.view.*

/**
 * 省去findViewById()
 * 直接饮用布局中的控件
 *
 * 注意，需要导入：import kotlinx.android.synthetic.main.activity_fir.*
 */
class WeightActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fir)
        //直接引用控件的id作为控件
        tv.text = "我试试"//无需findViewById()。已经为你find好了。
        iv2.setImageResource(R.mipmap.ic_launcher)

        //也可以访问View的子View，不过一般不需要这样做，这里iv是rl子View
        rl.iv.setImageResource(R.mipmap.ic_launcher)
    }
}
