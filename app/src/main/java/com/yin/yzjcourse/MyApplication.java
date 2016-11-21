package com.yin.yzjcourse;

import android.app.Application;

import com.tencent.bugly.crashreport.CrashReport;

/**
 * Created by think on 2016/11/21.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //集成腾讯Bugly开始
        CrashReport.initCrashReport(getApplicationContext(), "900059109", false);
        //集成腾讯Bugly结束
    }
}
