package com.yin.yzjcourse.k5类和对象.k6类委托_装饰者模式

import org.junit.Test

class TestDele {
    @Test
    fun mytest(){
        //即保留了MyListenerImpl里onGet()的逻辑println("MyListenerImpl onGet")
        //也保留了MyObj里onGet()的逻辑println("子类的onGet")
        //又扩展出了MyDelegate里onGet的逻辑println("MyDelegate onGet")
        //课件我没有改变MyObj的onGet的逻辑，就通过myDelegate实现了MyObj的onGet的逻辑拓展，课件比继承优雅
        val myDelegate = MyDelegate(MyObj())
        myDelegate.onGet()
    }
}