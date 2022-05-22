package com.yin.yzjcourse.Optimize;

import androidx.core.util.Pools;

import com.yin.yzjcourse.utils.DLog;

/**
 * 对象池由数组来维护，但是是按照栈解构的逻辑进出数据，即先进后出，后进先出
 * 看Pools.java的源码即可，代码很简单
 * SynchronizedPool继承SimplePool，SynchronizedPool是线程安全的，只是把acquire(),release()
 * 加上synchronized关键字而已，核心逻辑还是SimplePool的
 */

public class UserPool {

    public String name = "UserPool";

    private static final Pools.SynchronizedPool<UserPool> sPool = new Pools.SynchronizedPool<UserPool>(5);//对象池

    /**
     * T acquire():
     * 如果数组长度大于0，则返回数组的最后一个元素，然后立刻将最后一个元素赋值为null
     * 如果数组长度为0，则返回null
     */
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


    /**
     * boolean release(T instance)
     * 如果instance已经在对象池的数组里，则抛出异常"Already in the pool!"
     * 如果此时数组的length小于线程池的尺寸，则把instance追加到数组的末尾，然后返回true
     * 否则返回false
     */
    //清空对象
    public void recycle() {
        sPool.release(this);
    }
}
