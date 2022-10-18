package com.yin.yzjcourse;

import android.content.Context;

import androidx.lifecycle.ProcessLifecycleOwner;
import androidx.multidex.MultiDexApplication;

import com.dev.think.mylibrary.PushUtils;
import com.tencent.bugly.crashreport.CrashReport;
import com.yin.yzjcourse.Jetpack.AppLocationListener;

import zhl.common.oauth.OauthApplicationLike;

/**
 * Created by think on 2016/11/21.
 */

public class MyApplication extends OauthApplicationLike {
    public static Context appContext;
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
    }

    private void initBugly() {
        //集成腾讯Bugly开始
        CrashReport.initCrashReport(getApplicationContext(), "900059109", false);
        //集成腾讯Bugly结束
    }
}
