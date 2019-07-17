package com.yin.yzjcourse.Jetpack;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;

import com.yin.yzjcourse.utils.DLog;

public class CustomDaiLi implements LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onOpen(LifecycleOwner activity) {
        if (activity instanceof CustomOwner) {
            DLog.eLog("onOpen:" + true);
        } else {
            DLog.eLog("onOpen:" + false);
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    void onRun(LifecycleOwner activity) {
        DLog.eLog("onRun");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onRelease(LifecycleOwner activity) {
        DLog.eLog("onRelease");
    }
}
