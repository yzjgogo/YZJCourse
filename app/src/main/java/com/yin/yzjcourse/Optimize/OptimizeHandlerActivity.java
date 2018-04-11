package com.yin.yzjcourse.Optimize;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;

public class OptimizeHandlerActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_optimize_handler);
        sendData();
    }

    /**
        这里，activity在突出需要销毁的时候，因为mHandler仍然活着，mHandler仍然持有activity实例，导致activity实例无法销毁
        造成内存泄露
     */
    private void sendData() {
        mHandler.sendEmptyMessageAtTime(0,1000*1000*1000);
    }

    //    错误的示范：
//    mHandler是匿名内部类的实例，会引用外部对象MainActivity.this。如果Handler在Activity退出的时候，它可能还活着，这时候就会一直持有Activity。
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    //加载数据
                    break;

            }
        }
    };
}
