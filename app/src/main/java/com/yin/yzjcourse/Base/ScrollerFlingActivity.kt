package com.yin.yzjcourse.Base

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import butterknife.ButterKnife
import butterknife.OnClick
import com.yin.yzjcourse.BaseActivity
import com.yin.yzjcourse.R
import kotlinx.android.synthetic.main.activity_scroller_fling.*

class ScrollerFlingActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scroller_fling)
        ButterKnife.bind(this)
    }


    @OnClick(R.id.bt_scroll)
    fun onViewClicked(view: View) {
        when (view.id) {
            R.id.bt_scroll -> {
                my_sv.smoothScrollToDiy(0,500)
//                my_sv.scrollTo(0,500)
            }
        }
    }
}