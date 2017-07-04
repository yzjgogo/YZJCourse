package com.yin.yzjcourse.utils;

import android.util.Log;

import com.orhanobut.logger.Logger;
import com.yin.yzjcourse.BuildConfig;

/**
 * Created by kougin on 2016/3/16.
 * 日志打印工具类
 */
public class DLog {
    private static final boolean sDebug = BuildConfig.DEBUG;

    public static void d(String message, Object... args) {
        if (sDebug) {
            Logger.d(message, args);
        }
    }

    public static void i(String message, Object... args) {
        if (sDebug) {
            Logger.i(message, args);
        }
    }

    public static void e(String message, Object... args) {
        if (sDebug) {
            Logger.e(message, args);
        }
    }
    public static void eLog(String content){
        if(sDebug){
            Log.e("yin",content);
        }
    }
}
