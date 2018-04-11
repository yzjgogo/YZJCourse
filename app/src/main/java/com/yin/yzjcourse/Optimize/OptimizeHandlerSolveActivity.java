package com.yin.yzjcourse.Optimize;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;
import com.yin.yzjcourse.utils.DLog;

import java.lang.ref.WeakReference;

public class OptimizeHandlerSolveActivity extends BaseActivity {
    private int a = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_optimize_handler_solve);
        sendData();
    }

    private void sendData() {
        new MyHandler(this).sendEmptyMessage(0);
        new MyHandler(this).sendEmptyMessageAtTime(0, 1000 * 1000 * 1000);
    }

    /**
     * 1：这里用static修饰，目的是使内部类不依附于具体的对象实例，而是依附于类。参考：OptimizeInnerClassActivity.java
     * 2:这里用弱引用的目的是：这里的内部类(MyHandler)想引用外部类(OptimizeHandlerSolveActivity)的资源(a)时，如果使用强
     * 引用，则activity准备销毁时，因为内部类强引用了该activity实例，如果内部类依然存活，会导致该activity无法被销毁导致内存泄露，
     * 因此用弱引用，这样在下次GC时，如果该activity关闭了则可直接回收该activity实例，而既然该activity实例销毁了，则该handler也
     * 没用工作了必要了。
     */
    private static class MyHandler extends Handler {
        //        private MainActivity mainActivity;//直接强引用一个外部类的强引用，会内存泄露
        //弱引用的用法
        private WeakReference<OptimizeHandlerSolveActivity> handlerSolveActivity;

        public MyHandler(OptimizeHandlerSolveActivity activity) {
            this.handlerSolveActivity = new WeakReference<OptimizeHandlerSolveActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            OptimizeHandlerSolveActivity myActivity = handlerSolveActivity.get();
            if (myActivity == null || myActivity.isFinishing()) {
                return;
            }
            int b = myActivity.a;
            DLog.eLog("接收消息：" + b);
        }
    }
}
