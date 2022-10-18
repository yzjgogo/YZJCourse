package zhl.common.utils;

import android.util.Log;

import java.io.File;

public class MLog {
    public final static Boolean DEBUG_TRUE;

    static {
        boolean isOn = false;
        try {
            File devFile = new File(Tools.getSDPath() + "/mlog.on");
            if (devFile.exists()) {
                isOn = true;
            }
        } catch (Exception e) {

        }
        if (isOn)
            DEBUG_TRUE = true;
        else
            DEBUG_TRUE = false;
    }

    public static void e(String tag, String msg) {
        if (DEBUG_TRUE)
            Log.e(tag, msg);
    }

    public static void e(String msg) {
        if (DEBUG_TRUE)
            Log.e("dd", msg);
    }

    public static void e(String tag, String msg, Throwable e) {
        if (DEBUG_TRUE)
            Log.e(tag, msg, e);
    }

    public static void i(String tag, String msg) {
        if (DEBUG_TRUE)
            Log.i(tag, msg);
    }

    public static void i(String tag, String msg, Throwable e) {
        if (DEBUG_TRUE)
            Log.i(tag, msg, e);
    }

    public static void d(String tag, String msg) {
        if (DEBUG_TRUE)
            Log.d(tag, msg);
    }

    public static void d(String msg) {
        if (DEBUG_TRUE)
            Log.d("dd", msg);
    }

    public static void v(String tag, String msg) {
        if (DEBUG_TRUE)
            Log.v(tag, msg);
    }

    public static void w(String tag, String msg) {
        if (DEBUG_TRUE)
            Log.w(tag, msg);
    }

    public static void w(String tag, String msg, Throwable e) {
        if (DEBUG_TRUE)
            Log.w(tag, msg, e);
    }
}
