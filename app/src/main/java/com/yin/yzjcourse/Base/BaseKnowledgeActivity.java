package com.yin.yzjcourse.Base;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;
import com.yin.yzjcourse.Utils;
import com.yin.yzjcourse.utils.DLog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 子线程切换到handler所在线程的几种方式(也可以说子线程切换到UI线程的几种方式，因为handler一般在UI线程);
 * 1：发送Message，handler处理messgae；
 * 2：handler.post(runnbale);
 * 3: runOnUiThread(runnable),其实内部调用的还是handler.post(runnbale);
 * 4：view.post(runnable),其实内部调用的还是handler.post(runnbale);
 */
public class BaseKnowledgeActivity extends BaseActivity {
    @BindView(R.id.tv_content1)
    TextView tvContent1;
    @BindView(R.id.tv_content2)
    TextView tvContent2;
    @BindView(R.id.tv_content3)
    TextView tvContent3;
    @BindView(R.id.tv_content4)
    TextView tvContent4;
    @BindView(R.id.tv_content5)
    TextView tvContent5;
    @BindView(R.id.tv_content6)
    TextView tvContent6;
    //handler接收到子线程发过来的信息在主线程处理
    private Handler mHandler1 = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                String content = (String) msg.obj;
                tvContent1.setText("obj:" + content +
                        ",arg1:" + msg.arg1 +
                        ",arg2:" + msg.arg2 +
                        ",bundle:" + msg.getData().getString("setData"));
            }
        }
    };
    //参考mHandler1
    Handler mHandler2 = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            DLog.eLog("执行第一个");
            tvContent2.setText(String.valueOf(msg.what));
            //如果返回false则会继续执行下面的handleMessage，如果返回true则不会执行，进去看源码
            return false;
        }
    }) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            tvContent2.setText(String.valueOf(msg.what));
            DLog.eLog("执行第二个");
        }
    };
    //mHandler3
    private Handler mHandler3 = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 3) {
                String content = (String) msg.obj;
                tvContent3.setText("mHandler3" + content);
            }
        }
    };
    //mHandler4
    private Handler mHandler4 = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 4) {
                String content = (String) msg.obj;
                tvContent4.setText("mHandler4" + content);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_knowledge);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.bt_message_handle})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_message_handle:
                sendMessage();
                break;
        }
    }

    /**
     * 一般都是在子线程做耗时的操作，操作完成后通过handler发送到主线程，由handler在主线程更新UI；
     * 因为Android不允许在子线程更新UI
     */
    private void sendMessage() {
        //TODO :  为什么Android不允许在子线程更新UI
        new Thread() {
            @Override
            public void run() {
                super.run();
                DLog.eLog("在哪个线程发送的消息：" + Thread.currentThread().getName());
                Message msg = new Message();
                msg.what = 1;
                msg.obj = String.valueOf(System.currentTimeMillis());
                //如果只需要传输几个整形数据
                msg.arg1 = 1;
                msg.arg2 = 2;
                //传输Bundle
                Bundle bundle = new Bundle();
                bundle.putString("setData", "bundle");
                msg.setData(bundle);
                mHandler1.sendMessage(msg);
                ////////////////////////////////////////////////////////////////////////////////////////////////////
                mHandler2.sendEmptyMessage(9527);
//                mHandler2.sendEmptyMessageDelayed(9527, 2000);延迟两秒发送
                ////////////////////////////////////////////////////////////////////////////////////////////////////
                Message msg3 = mHandler3.obtainMessage();
                msg3.what = 3;
                msg3.obj = "msg3";
                mHandler3.sendMessage(msg3);
                ////////////////////////////////////////////////////////////////////////////////////////////////////
                //这里new Message()的不可以必须是obtainMessage()才能用sendToTarget()
                Message msg4 = mHandler4.obtainMessage();//注意obtainMessage()有几个参数不一样的方法
                msg4.what = 4;
                msg4.obj = "msg4";
                msg4.sendToTarget();
            }
        }.start();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////

    //mHandler5
    private Handler mHandler5 = new Handler();

    /**
     * boolean postDelayed(Runnable r, long delayMillis)
     * 可以实现定时器，延迟多少秒后执行
     * 这里的效果就是每2秒刷新一下tvContent5
     * 通过Thread.currentThread().getName()验证发现：Runnable是在Handler对象所在的线程执行的
     * 如果其所在的线程是UI线程的话，Runnable中还是不能执行耗时操作，不然会ANR。
     */
    @OnClick(R.id.bt_post_delay)
    public void onClickDelay() {
        DLog.eLog("主线程：" + Thread.currentThread().getName());
        mHandler5.postDelayed(delayAble, 2000);
    }

    //delayAble在mHandler5所在的线程执行，因为并没有调用start方法而是调用的run方法，所以没有开启新的线程
    Runnable delayAble = new Runnable() {
        @Override
        public void run() {
            DLog.eLog("哪个线程：" + Thread.currentThread().getName());
            tvContent5.setText("postDelay延迟执行:" + System.currentTimeMillis());
            mHandler5.postDelayed(this, 2000);
        }
    };

    //关闭定时器
    @OnClick(R.id.bt_stop_delay)
    public void onStopDelay() {
        Utils.showToast(this, "关闭定时器");
        mHandler5.removeCallbacks(delayAble);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    Handler mHandler6 = new Handler();

    /**
     * 同样证明了Runnable会在Handler所在线程执行，并不是开启新的线程，因为没有调用start方法而是调用的run方法
     * 但是没必要这样搞，runnable肯定和mHandler6在同一个线程，一般都是在子线程调用
     */
    @OnClick(R.id.bt_post)
    public void onClickPost() {
        String mainThread = Thread.currentThread().getName();
        mHandler6.post(new Runnable() {
            @Override
            public void run() {
                tvContent6.setText("直接调用handler.post(r):"+mainThread + "," + Thread.currentThread().getName());
            }
        });
    }

    /**
     * 再次证明了，无论你在哪个线程调用handler.post(r)方法，r肯定是在handler所在的线程执行;
     * 这里我们是在子线程调用的handler.post(r)方法，但是r仍然是在handler所在的mian线程执行
     * 所以当我们需要在子线程执行耗时操作时，操作完成后，我们也可以不通过message发送消息给handler
     * 执行，而是采用handler.post(r)方法同样可以做到在main更新UI
     *
     * 这种用法才是正常的用法
     */
    @OnClick(R.id.bt_post_second)
    public void onClickPostSecond() {
        String mainThread = Thread.currentThread().getName();
        new Thread(new Runnable() {
            @Override
            public void run() {
                String secondThread = Thread.currentThread().getName();
                mHandler6.post(new Runnable() {
                    @Override
                    public void run() {
                        //看似是在子线程更新UI，其实是在handler所在的线程
                        tvContent6.setText("子线程调用handler.post(r)更新UI："+mainThread + "," + secondThread + "," + Thread.currentThread().getName());
                    }
                });
            }
        }).start();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * runOnUiThread:
     * 无论在哪个线程调用，runOnUiThread都会在UI线程执行
     * 所以也适合在子线程执行耗时的操作后，调用runOnUiThread直接在UI线程更新UI
     * 进runOnUiThread内部看源码发现：runOnUiThread其实最终还是调用的handler.post(runnable)方法
     */
    @OnClick(R.id.bt_run_ui)
    public void onRunUI() {
        String mainThread = Thread.currentThread().getName();
        new Thread(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvContent6.setText("runOnUiThread:UI线程"+mainThread+",runOnUiThread执行时所在线程："+Thread.currentThread().getName());
                    }
                });
            }
        }).start();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * view.post(Runnable action):
     * 无论在哪个线程调用，view.post(Runnable action)都会在UI线程执行
     * 所以也适合在子线程执行耗时的操作后，调用view.post(Runnable action)直接在UI线程更新UI
     * 进view.post(Runnable action)内部看源码发现：view.post(Runnable action)其实最终还是调用的handler.post(runnable)方法
     *
     */
    @OnClick(R.id.bt_view_post)
    public void onViewPost() {
        String mainThread = Thread.currentThread().getName();
        new Thread(new Runnable() {
            @Override
            public void run() {
                tvContent6.post(new Runnable() {
                    @Override
                    public void run() {
                        tvContent6.setText("view.post(runnable):UI线程"+mainThread+",view.post(runnable)执行时所在线程："+Thread.currentThread().getName());
                    }
                });
            }
        }).start();
    }
}
