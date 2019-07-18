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
 *  2：生命周期回调方法要么没有参数，要么有且只有一个LifecycleOwner类型的参数,ON_ANY除外，具体看onAny()的注释
 *  3：LifecycleOwner的一个生命周期回调方法可以有多个LifecycleObserver的方法代理，例如这里的onCreate都有效；
 *  4：LifecycleOwner是被观察者，LifecycleObserver是观察者；
 *  5：高版本兼容库中，Activity和Fragment都实现LifecycleOwner；低版本的兼容库虽然没有实现，但是可通过LifecycleRegistry来实现相同的功能；
 *  6：我们自己的具有生命周期特性的类，也可以实现LifecycleOwner
 *  7：LifecycleObserver，实现该接口的类，可以通过被 LifecycleOwner 类中的 Lifecycle 对象的 addObserver(LifecycleObserver o) 方法注册，被注册后，LifecycleObserver 便可以观察 LifecycleOwner 的生命周期事件。
 *  8： LifecycleOwner，如 Activity 或 Fragment。实现该接口的类持有 Lifecycle 对象。持有 Lifecycle 有什么作用呢？ 作用是在将 LifecycleOwner 对应的生命周期事件传递给内部的 Lifecycle 对象去处理，于是其生命周期的改变便可被 Lifecycle 所注册的观察者 LifecycleObserver 观察到并触发其对应的事件。
 *  9：Lifecycle 是一个抽象类，它持有关于组件(如 Activity 或 Fragment)生命周期状态的信息，并且允许其他对象观察此状态。
 *      LifecycleOwner 持有 Lifecycle 对象，LifecycleOwner 通过 getLifecycle() 获取内部 Lifecycle 对象，然后就可以注册或取消注册观察者 LifecycleObserver。
 *      Lifecycle 使用两个主要枚举来跟踪其关联组件的生命周期状态
 *      State：当前生命周期所处状态。由框架和 Lifecycle 类所调度的生命周期事件。这些事件映射到 Activity 或 Fragment 中的回调事件。
 *      Event：当前生命周期改变对应的事件。由 Lifecycle 对象所跟踪[tracked]的组件的当前状态。
 *  10：LifecycleRegistry，继承自 Lifecycle，是 Activity 或 Fragment 中所持有的 Lifecycle 对象的实际类型。LifecycleRegistry 内部有一个成员变量 State 用于记录当前的生命周期，当其生命周期改变后，LifecycleRegistry 就会逐个通知每一个注册的 LifecycleObserver。
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


    /**
     * 可以接受两个参数，第二个参数必须是Lifecycle.Event类型，这个方法的意思时无论LifecycleOwner回调任何一个生命周期方法，都会回调onAny
     * 例如打开Activity的过程会执行onAny(ON_CREATE) onAny(ON_START) onAny(ON_RESUME)
     * @param activity
     * @param event
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    void onAny(LifecycleOwner activity,Lifecycle.Event event) {
        DLog.eLog("onAny"+event.toString());
    }
}

