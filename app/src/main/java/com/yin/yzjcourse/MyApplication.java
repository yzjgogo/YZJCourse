package com.yin.yzjcourse;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.alibaba.sdk.android.push.CloudPushService;
import com.alibaba.sdk.android.push.CommonCallback;
import com.alibaba.sdk.android.push.noonesdk.PushServiceFactory;
import com.tencent.bugly.crashreport.CrashReport;

/**
 * Created by think on 2016/11/21.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initBugly();
        initCloudChannel(this);
    }

    private void initBugly() {
        //集成腾讯Bugly开始
        CrashReport.initCrashReport(getApplicationContext(), "900059109", false);
        //集成腾讯Bugly结束
    }
    /**
     * 初始化云推送通道
     * @param applicationContext
     */
    private void initCloudChannel(Context applicationContext) {
        PushServiceFactory.init(applicationContext);
        final CloudPushService pushService = PushServiceFactory.getCloudPushService();
        pushService.register(applicationContext, new CommonCallback() {
            @Override
            public void onSuccess(String response) {
                Log.e("yin", "init cloudchannel success:"+pushService.getDeviceId());
            }
            @Override
            public void onFailed(String errorCode, String errorMessage) {
                Log.e("yin", "init cloudchannel failed -- errorcode:" + errorCode + " -- errorMessage:" + errorMessage);
            }
        });
    }
}
