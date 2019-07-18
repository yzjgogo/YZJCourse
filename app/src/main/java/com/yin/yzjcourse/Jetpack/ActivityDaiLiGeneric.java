package com.yin.yzjcourse.Jetpack;

import android.arch.lifecycle.GenericLifecycleObserver;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;

import com.yin.yzjcourse.utils.DLog;

/**
 * GenericLifecycleObserver继承自LifecycleObserver，LifecycleOwner的每一个生命周期方法触发都会回调onStateChanged()
 * LifecycleObserver的区别就是不用通过OnLifecycleEvent注解，逐个实现LifecycleOwner的各个生命周期的回调。
 */
public class ActivityDaiLiGeneric implements GenericLifecycleObserver {
    @Override
    public void onStateChanged(LifecycleOwner source, Lifecycle.Event event) {
        if (source instanceof MyLCActivity) {
            DLog.eLog("onStateChanged:"+true);
        }else {
            DLog.eLog("onStateChanged:"+false);
        }
        switch (event) {
            case ON_CREATE:
                DLog.eLog("onStateChanged-ON_CREATE");
                break;
            case ON_START:
                DLog.eLog("onStateChanged-ON_START");
                break;
            case ON_RESUME:
                DLog.eLog("onStateChanged-ON_RESUME");
                break;
            case ON_PAUSE:
                DLog.eLog("onStateChanged-ON_PAUSE");
                break;
            case ON_STOP:
                DLog.eLog("onStateChanged-ON_STOP");
                break;
            case ON_DESTROY:
                DLog.eLog("onStateChanged-ON_DESTROY");
                break;
            case ON_ANY:
                DLog.eLog("onStateChanged-ON_ANY");
                break;
            default:
                DLog.eLog("onStateChanged-default");
                break;
        }
    }
}
