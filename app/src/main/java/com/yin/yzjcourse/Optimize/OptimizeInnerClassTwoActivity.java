package com.yin.yzjcourse.Optimize;

import android.os.Bundle;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;

import java.util.Timer;
import java.util.TimerTask;

public class OptimizeInnerClassTwoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_optimize_inner_class_two);
        loadData();
    }

    /**
        用static修饰方法就不会造成内存泄露，否则会内存泄露
        参考：OptimizeInnerClassActivity.java
        也可以在onDestroy中把timer.cancel掉然后赋空
     */
//    private static void loadData() {
    private void loadData() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, 20000);//这个线程延迟5分钟执行
    }
}
