package com.yin.yzjcourse.Optimize;

import android.os.Bundle;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;

public class OptimizeInnerClassActivity extends BaseActivity {
    int a = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_optimize_inner_class);
        loadData();
    }

    /**
        这里非静态方法中的内部类是个线程，在整个线程持续的过程中，因为是非静态的实际上该内部类是依附于
        当前OptimizeInnerClassActivity的实例的。就是说这个内部类属于对象实例而不是属于类。因此该实例销毁时
        因为被该内部类持有造成内存泄漏。
        当将方法设置为静态后，则该内部类就属于OptimizeInnerClassActivity这个类，而不是属于某个类的实例，因此
        也不会持有某个类的实例。因此不会影响OptimizeInnerClassActivity实例的销毁。
     */
//    public static void loadData() {//没有内存泄露，隐士持有OptimizeInnerClassActivity实例。MainActivity.this.a
    public void loadData() {//造成内存泄露，隐士持有OptimizeInnerClassActivity实例、MainActivity.this.a
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
//                        int b=a;
                        Thread.sleep(1000 * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
