package com.yin.yzjcourse.Base;


import android.app.IntentService;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;

/**
 * 主要看看IntentService的源码，很简单
 * 1：IntentService的onCreate()方法：
 * 创建一个HandlerThread,获取该HandlerThread的Looper,使用这个Looper创建一个Handler,这个Handler就会在HandlerThread的线程
 * 处理消息。
 * 2：IntentService的onStartCommend()方法
 * 注意该方法有一个startId参数，每启动一次其startId加1。后面顺序执行后台任务时，stopSelf(startId)会根据次判断是否销毁IntentService.
 * onStartCommend()会调用onStart()，在onStart里会将startId和Intent原封不动的放入Message通过Handler发送出去，Handler收到
 * 消息后会在其所在线程(子线程)处理该消息，调用onHandleIntent()，因此onHandleIntent()就运行在子线程，onHandleIntent()
 * 执行完成后会调用stopSelf(startId),startId主要是在多任务的情况，如果当前startId和最新的startId相同则直接销毁IntentService,
 * 否则等待其它任务执行完成后再销毁。
 */
public class LocalIntentService extends IntentService {
    private static final String TAG = "LocalIntentService";

    public LocalIntentService() {
        super(TAG);
    }

    /**
     * 运行于子线程
     * @param intent
     */
    @Override
    protected void onHandleIntent(Intent intent) {
        String action = intent.getStringExtra("task_action");
        Log.d(TAG, "receive task :" +  action);
        SystemClock.sleep(3000);
        if ("com.ryg.action.TASK1".equals(action)) {
            Log.d(TAG, "handle task: " + action);
        }
    }

    /**
     * 如果时多任务，则最后一个任务的onHandleIntent()执行完后,销毁IntentService.
     */
    @Override
    public void onDestroy() {
        Log.d(TAG, "service destroyed.");
        super.onDestroy();
    }
}