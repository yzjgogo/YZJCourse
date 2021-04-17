package com.yin.yzjcourse.Jetpack

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.yin.yzjcourse.BaseActivity
import com.yin.yzjcourse.R
import com.yin.yzjcourse.utils.DLog

class LiveDataActivity : BaseActivity() {
    lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_data)

        textView = findViewById(R.id.tv_content)

        //关联数据机器，viewModel用于产生Activity需要的业务数据
        val viewModel = ViewModelProvider(this).get(TimerWithLiveDataViewModel::class.java)

        //持有ViewModel里的LiveData,方便后面监听数据的变化
        val liveData = viewModel.currentSecond

        //给liveData添加一个观察者，当ViewModel里的数据发生变化时，被观察者LiveData会通知观察者
        //只有activity处于前台(onStart,onResume)时，liveData数据发生变化时才会通知观察者，否则即使liveData的数据有变化，也不会通知观察者，
        // 看observe(@NonNull LifecycleOwner owner, @NonNull Observer<? super T> observer) 的源码
        //第一个参数是LifecycleOwner,在方法的最后，将observer于activity(LifecycleOwner)的生命周期关联起来，从而实现之。
        liveData?.observe(this){
            DLog.eLog("收到来自liveData的数据变化：$it")
            textView.text = it.toString()
        }

        //更新数据
        findViewById<Button>(R.id.bt_restart).setOnClickListener {
            liveData?.value = 0
        }

        viewModel.startTiming()
    }
}