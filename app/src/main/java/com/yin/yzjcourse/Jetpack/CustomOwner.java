package com.yin.yzjcourse.Jetpack;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.support.annotation.NonNull;

/***
 * 自定义的LifecycleOwner，自定义生命周期onOpen(),onRun(),onRelease()
 *
 * 其实Activity和Fragment也是通过LifecycleRegistry实现生命周期回调代理的，这里我们只是依葫芦画瓢写一套自己的实现；
 */
public class CustomOwner implements LifecycleOwner {
    private LifecycleRegistry mLifecycleRegistry;//Activity和Fragment也用到了这个

    public CustomOwner() {
        mLifecycleRegistry = new LifecycleRegistry(this);
        getLifecycle().addObserver(new CustomDaiLi());//和Activity和Fragment一样
    }

    public void onOpen() {
        mLifecycleRegistry.markState(Lifecycle.State.CREATED);
    }

    public void onRun() {
        mLifecycleRegistry.markState(Lifecycle.State.STARTED);
    }

    public void onRelease() {
        mLifecycleRegistry.markState(Lifecycle.State.DESTROYED);
    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        //Activity和Fragment也是返回的mLifecycleRegistry
        return mLifecycleRegistry;
    }
}
