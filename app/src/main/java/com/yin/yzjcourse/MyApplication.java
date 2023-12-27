package com.yin.yzjcourse;

import android.content.Context;

import androidx.lifecycle.ProcessLifecycleOwner;
import androidx.multidex.MultiDexApplication;

import com.dev.think.mylibrary.PushUtils;
import com.tencent.bugly.crashreport.CrashReport;
import com.yin.yzjcourse.Jetpack.AppLocationListener;
import com.yin.yzjcourse.Net.PocOp;

import zhl.common.oauth.OauthApplicationLike;
import zhl.common.request.RequestManager;

/**
 * Created by think on 2016/11/21.
 */

public class MyApplication extends OauthApplicationLike {
    public static Context appContext;
    public static long startTimes = 0;//时间戳，毫秒
    public static long durationTime = 0;//想播放多久就停止播放，分钟
    public static int intervalTime = 1;//两句之间的时间间隔，秒
    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
        ProcessLifecycleOwner.get().getLifecycle().addObserver(new AppLocationListener());

//        ARouter.openLog();
//        ARouter.openDebug();
//        ARouter.init(this);

        initBugly();
        PushUtils.register(this);

        RequestManager.initialize(getApplication());
        RequestManager.registerRequestConfigClass(PocOp.class);
    }

    private void initBugly() {
        //集成腾讯Bugly开始
        CrashReport.initCrashReport(getApplicationContext(), "900059109", false);
        //集成腾讯Bugly结束
    }
}
