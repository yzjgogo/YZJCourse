package com.yin.yzjcourse.OfficialWeight

import android.os.Bundle
import android.view.View
import android.widget.Space
import com.google.android.flexbox.FlexboxLayout
import com.yin.yzjcourse.BaseActivity
import com.yin.yzjcourse.R
import kotlinx.android.synthetic.main.activity_flex_box.*

class FlexBoxActivity:BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flex_box)
        val params1 = FlexboxLayout.LayoutParams(300,400)
        val view1 = View(this)
        view1.setBackgroundColor(resources.getColor(R.color.actionsheet_blue))
        view1.layoutParams = params1
        fbl.addView(view1)

        val params2 = FlexboxLayout.LayoutParams(0,400)
        params2.flexGrow = 1.0f
        val view2 = Space(this)
//        view2.setBackgroundColor(resources.getColor(R.color.strong_red_hint_text_color))
        view2.layoutParams = params2
        fbl.addView(view2)

        val params3 = FlexboxLayout.LayoutParams(300,400)
        val view3 = View(this)
        view3.setBackgroundColor(resources.getColor(R.color.money_deposit_color))
        view3.layoutParams = params3
        fbl.addView(view3)
    }
}