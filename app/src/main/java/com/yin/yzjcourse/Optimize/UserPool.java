package com.yin.yzjcourse.Optimize;

import androidx.core.util.Pools;

import com.yin.yzjcourse.utils.DLog;

/**
 * Created by think on 2018/4/18.
 */

public class UserPool {

    public String name = "UserPool";

    private static final Pools.SynchronizedPool<UserPool> sPool = new Pools.SynchronizedPool<UserPool>(5);//对象池

    //获取对象中数据
    public static UserPool obtain() {
        UserPool instance = sPool.acquire();
        if (instance == null) {
            DLog.eLog("此时为null");
            return new UserPool();
        }
        DLog.eLog("此时不为null");
        return instance;
    }

    //清空对象
    public void recycle() {
        sPool.release(this);
    }
}
