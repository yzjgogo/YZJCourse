package com.dev.think.mylibrary;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by think on 2016/12/7.
 */

public class Utils {
    public static void methodInLibrary(Context context) {
        Log.e("yin", "调用library里的方法");
        Toast.makeText(context,"弹出来",Toast.LENGTH_SHORT).show();
    }
}
