package com.yin.yzjcourse.Jetpack;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

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
