package com.yin.yzjcourse.Base;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;
import com.yin.yzjcourse.utils.DLog;

/**
 * HandlerThread：
 * 可以直接使用handler的thread，因为其run()方法内部通过Looper.prepare()创建了消息队列，也通过Looper.loop()开启了消息循环；
 * 因此可以直接在HandlerThread线程创建Handler。
 *
 * 因为HandlerThread的run方法是一个无限循环(loop()),所以当不需要使用HandlerThread时应调用其quit()或quitSafety()方法来终止线程的执行;
 */
public class HThreadActivity extends BaseActivity {
    private HandlerThread myHandlerThread;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hthread);

        //创建一个线程,线程名字：handler-thread
        myHandlerThread = new HandlerThread("handler-thread");
        //开启一个线程
        myHandlerThread.start();
        //在这个线程中创建一个handler对象，参数时Looper对象
        handler = new Handler(myHandlerThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                //这个方法是运行在 handler-thread 线程中的 ，可以执行耗时操作
                DLog.e("handler ", "消息： " + msg.what + "  线程： " + Thread.currentThread().getName());

            }
        };

        //在主线程给handler发送消息
        handler.sendEmptyMessage(1);

        new Thread(new Runnable() {
            @Override
            public void run() {
                //在子线程给handler发送数据
                handler.sendEmptyMessage(2);
            }
        }).start();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //释放资源，退出无限循环，终止线程的执行
        myHandlerThread.quit();
    }
}
