package com.yin.yzjcourse.Base

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import butterknife.ButterKnife
import butterknife.OnClick
import com.yin.yzjcourse.BaseActivity
import com.yin.yzjcourse.Jetpack.MyLCService
import com.yin.yzjcourse.R
import com.yin.yzjcourse.utils.DLog
import kotlinx.android.synthetic.main.activity_vew_compute.*

/**
 */
class ViewComputeActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vew_compute)
        ButterKnife.bind(this)
    }


    @OnClick(R.id.bt_compute,R.id.bt_can_scroll)
    fun onViewClicked(view: View) {
        when (view.id) {
            R.id.bt_compute -> {
                my_sv.doCompute()
            }
            R.id.bt_can_scroll -> {
                /**
                 * canScrollVertically(int direction)
                 * direction > 0时，判断内容是否可以向上滚动
                 * direction < 0时，判断内容是否可以向下滚动
                 */
                DLog.eLog("是否能滚动：大于0时：${my_sv.canScrollVertically(1)} , 小于0时：${my_sv.canScrollVertically(-1)}")
            }
        }
    }
}