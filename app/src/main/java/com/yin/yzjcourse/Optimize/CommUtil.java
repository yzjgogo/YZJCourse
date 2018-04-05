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

    public static CommUtil getInstance(Context context){
        if(commonInstance == null){
            commonInstance = new CommUtil(context);
        }
        return commonInstance;
    }

}
