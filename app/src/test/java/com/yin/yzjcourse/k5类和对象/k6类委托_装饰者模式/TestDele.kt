package com.yin.yzjcourse.k5类和对象.k6类委托_装饰者模式

import org.junit.Test

class TestDele {
    @Test
    fun mytest(){
        val myDelegate = MyDelegate(MyListenerImpl())
        myDelegate.onGet()
    }
}