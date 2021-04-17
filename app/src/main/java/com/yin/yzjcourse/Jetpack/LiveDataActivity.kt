package com.yin.yzjcourse.Jetpack

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.yin.yzjcourse.BaseActivity
import com.yin.yzjcourse.R
import com.yin.yzjcourse.utils.DLog

class LiveDataActivity : BaseActivity() {
    lateinit var textView: TextView
    var liveData: MutableLiveData<Int>? = null
    var mObserver: Observer<Int> = Observer<Int> {
        DLog.eLog("当activity处于前台时，才能收到来自liveData的数据变化：$it")
        textView.text = it.toString()
    }

    //    lateinit var mForeverObserver:(Int)->Unit//这是函数类型的实例，不是Observer类型的实例，虽然Observer是sam接口也不行的
    var mForeverObserver: Observer<Int> = Observer<Int> {
        DLog.eLog("无论activity处于什么状态都监听LiveData的变化---$it")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_data)

        textView = findViewById(R.id.tv_content)

        //关联数据机器，viewModel用于产生Activity需要的业务数据
        val viewModel = ViewModelProvider(this).get(TimerWithLiveDataViewModel::class.java)

        //持有ViewModel里的LiveData,方便后面监听数据的变化
        liveData = viewModel.currentSecond

        //给liveData添加一个观察者，当ViewModel里的数据发生变化时，被观察者LiveData会通知观察者
        //只有activity处于前台(onStart,onResume)时，liveData数据发生变化时才会通知观察者，否则即使liveData的数据有变化，也不会通知观察者，
        // 看observe(@NonNull LifecycleOwner owner, @NonNull Observer<? super T> observer) 的源码
        //第一个参数是LifecycleOwner,在方法的最后，将observer于activity(LifecycleOwner)的生命周期关联起来，从而实现之。
        liveData?.observe(this, mObserver)


        ////这是函数类型的实例，不是Observer类型的实例，虽然Observer是sam接口也不行的
//        mForeverObserver = {p:Int ->  DLog.eLog("无论activity处于什么状态都监听LiveData的变化---$p")}
        liveData?.observeForever(mForeverObserver)

        //更新数据
        findViewById<Button>(R.id.bt_restart).setOnClickListener {
            liveData?.value = 0
        }

        viewModel.startTiming()
    }

    override fun onDestroy() {
        DLog.eLog("有没有观察者1:${liveData?.hasObservers()} ,${liveData?.hasActiveObservers()} , $liveData")

        //只能移除与LifecycleOwner(这里是activity)关联的observer；observeForever(observer)里的observer无法移除
//        liveData?.removeObservers(this)

        //既可以移除与LifecycleOwner(这里是activity)关联的observer也可以移除observeForever(observer)里的observer
        liveData?.removeObserver(mObserver)
        liveData?.removeObserver(mForeverObserver)

        DLog.eLog("有没有观察者2:${liveData?.hasObservers()} ,${liveData?.hasActiveObservers()} , $liveData")
        super.onDestroy()
    }
}