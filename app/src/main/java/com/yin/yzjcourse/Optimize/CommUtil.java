package com.yin.yzjcourse.Optimize;

import android.content.Context;
import android.telecom.Connection;

/**
 * Created by ricky on 2016/11/2.
 */

public class CommUtil {
    private static CommUtil commonInstance;
    private Context commonContext;
    private CommUtil(Context context){
        this.commonContext = context;
    }

    /**
        第一次调用该方法，会将activity传入用于构建commonInstance实例。
        且commonInstance是static类型的，会常驻与内存，
        因此在activity销毁时虽然也会调用onDestroy()方法，但是因为有static类型
        的commonInstance持有该activity实例，所以该activity仍然无法销毁，无法被gc
        回收，造成内存泄露
     */
    public static CommUtil getInstance(Context activity){
        if(commonInstance == null){
            commonInstance = new CommUtil(activity);
        }
        return commonInstance;
    }

}
