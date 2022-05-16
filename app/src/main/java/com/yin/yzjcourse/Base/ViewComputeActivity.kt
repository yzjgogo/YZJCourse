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
import kotlinx.android.synthetic.main.activity_vew_compute.*

/**
 */
class ViewComputeActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vew_compute)
        ButterKnife.bind(this)
    }


    @OnClick(R.id.bt_compute)
    fun onViewClicked(view: View) {
        when (view.id) {
            R.id.bt_compute -> {
                my_sv.doCompute()
            }
        }
    }
}