package com.yin.yzjcourse.Base;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;
import com.yin.yzjcourse.utils.DLog;

public class MessageSourceActivity extends BaseActivity {
    private int mCount = 0;
    private Handler mHandlerThr = null;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            DLog.eLog(">>>>>>>>>>>>>UI# mHandler--handleMessage--msg.what=" + msg.what);
            //接收发送到UI线程的消息，然后向线程中的Handler发送msg 1。
            mHandlerThr.sendEmptyMessage(1);
            mCount++;
            if (mCount >= 3) {
                DLog.eLog("mCount大于等于3:"+mCount);
                //由于mHandlerThr是在Child Thread创建，Looper手动死循环阻塞，所以需要quit。
                mHandlerThr.getLooper().quit();
            }else {
                DLog.eLog("mCount小于3:"+mCount);
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_source);
        initData();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //删除所有call与msg
        mHandler.removeCallbacksAndMessages(null);
    }

    private void initData() {
        DLog.eLog(">>>>>>>>>>>>>UI# begin start thread!!!");
        new Thread() {
            @Override
            public void run() {
                super.run();
                Looper.prepare();
                mHandlerThr = new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        DLog.eLog(">>>>>>>>>>>>>Child# mHandlerThr--handleMessage--msg.what=" + msg.what);
                        //接收发送到子线程的消息，然后向UI线程中的Handler发送msg 0。
                        mHandler.sendEmptyMessage(0);
                    }
                };

                DLog.eLog(">>>>>>>>>>>>>Child# begin start send msg!!!");
                //Activity中启动Thread，在Thread结束前发送msg 0到UI Thread。
                mHandler.sendEmptyMessage(0);

                Looper.loop(); //不能在这个后面添加代码，程序是无法运行到这行之后的。
            }
        }.start();
    }
}
