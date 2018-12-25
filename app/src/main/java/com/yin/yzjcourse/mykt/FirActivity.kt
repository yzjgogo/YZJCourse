package com.yin.yzjcourse.mykt

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import butterknife.ButterKnife
import butterknife.OnClick
import com.yin.yzjcourse.R
import kotlinx.android.synthetic.main.activity_fir.*

//import kotlinx.android.synthetic.main.activity_fir.*

class FirActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fir)
//        ButterKnife.bind(this)
        tv.text = "我试试"
    }

//    @OnClick(R.id.bt1,R.id.bt2)
//    fun onViewClick(view:View){
//        when(view.id){
//            R.id.bt1 -> Toast.makeText(this,"我是bt1",Toast.LENGTH_SHORT).show()
//            R.id.bt2 -> Toast.makeText(this,"我是bt2",Toast.LENGTH_SHORT).show()
//        }
//    }
}
