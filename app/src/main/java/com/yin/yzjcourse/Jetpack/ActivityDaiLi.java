package com.yin.yzjcourse.Jetpack;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;

import com.yin.yzjcourse.utils.DLog;

/**
 * https://blog.csdn.net/xiatiandefeiyu/article/details/78643482
 * Activity的生命周期的代理类，也就是说activity的生命周期可以由ActivityDaiLi来管理
 *  注意点：
 *  1：生命周期回调方法名可以任意，onCreate,create,anything等等；
 *  2：生命周期回调方法要么没有参数，要么有且只有一个LifecycleOwner类型的参数；
 *  3：LifecycleOwner的一个生命周期回调方法可以有多个LifecycleObserver的方法代理，例如这里的onCreate都有效；
 *  4：LifecycleOwner是被观察者，LifecycleObserver是观察者；
 *  5：高版本兼容库中，Activity和Fragment都实现LifecycleOwner；低版本的兼容库虽然没有实现，但是可通过LifecycleRegistry来实现相同的功能；
 *  6：我们自己的具有生命周期特性的类，也可以实现LifecycleOwner
 *
 */
public class ActivityDaiLi implements LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onCreate(LifecycleOwner activity) {
        if (activity instanceof MyLCActivity) {
            DLog.eLog("onCreate:" + true);
        } else {
            DLog.eLog("onCreate:" + false);
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onCreate() {
        DLog.eLog("onCreate:" + null);
    }
//不可以有非LifecycleOwner类型的参数
//    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
//    void onCreate(String str) {
//        DLog.eLog("onCreate:" + "str");
//    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    void start(LifecycleOwner activity) {
        DLog.eLog("onStart");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    void onResume(LifecycleOwner activity) {
        DLog.eLog("onResume");

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    void onPause(LifecycleOwner activity) {
        DLog.eLog("onPause");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    void stop(LifecycleOwner activity) {
        DLog.eLog("onStop");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onDestroy(LifecycleOwner activity) {
        if (activity instanceof MyLCActivity) {
            DLog.eLog("onDestroy:" + true);
        } else {
            DLog.eLog("onDestroy:" + false);
        }
    }
}

