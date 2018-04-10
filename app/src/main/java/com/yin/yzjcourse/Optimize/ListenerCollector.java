package com.yin.yzjcourse.Optimize;

import android.view.View;

import java.util.WeakHashMap;

/**
    1：WeakHashMap里的entry可能会被GC自动删除，即使程序员没有调用remove()或者clear()方法。
    2：WeekHashMap 的这个特点特别适用于需要缓存的场景。在缓存场景下，由于内存是有限的，不能缓存所有对象；
       对象缓存命中可以提高系统效率，但缓存MISS也不会造成错误，因为可以通过计算重新得到。
    3：内部用到了弱引用，因此易被GC回收，当在WeakHashMap之外没有某个key的强引用了，则下次GC时就会回收这对<K,V>。
    4：WeakHashMap和HashMap一样key和value的值都可以为null，并且也是无序的。
 */

public class ListenerCollector {
    static private WeakHashMap<View, MyView.MyListener> sListener = new WeakHashMap<>();

    public void setsListener(View view, MyView.MyListener listener) {
        sListener.put(view, listener);
    }

    public static void clearListeners() {
        //移除所有监听。
        sListener.clear();
    }

    ;
}
