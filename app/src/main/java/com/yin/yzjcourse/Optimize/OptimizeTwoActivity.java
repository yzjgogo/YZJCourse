package com.yin.yzjcourse.Optimize;

import android.os.Bundle;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;

public class OptimizeTwoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
            这里构造MyView时传入了OptimizeTwoActivity，然后MyView又存入WeakHashMap集合
            因此当OptimizeTwoActivity销毁时由于WeakHashMap是static类型的，所以WeakHashMap
            里面的MyView仍然持有当前OptimizeTwoActivity实例，因此OptimizeTwoActivity即使
            调用了onDestroy()仍然无法被销毁，造成内存泄露，且每多创建一次OptimizeTwoActivity
            实例都会多泄露一个OptimizeTwoActivity实例。
         */
        MyView myView = new MyView(this);
        setContentView(myView);

    }

    @Override
    protected void onStop() {
        super.onStop();
        //去掉这句就会造成内存泄露
        ListenerCollector.clearListeners();
    }
}
