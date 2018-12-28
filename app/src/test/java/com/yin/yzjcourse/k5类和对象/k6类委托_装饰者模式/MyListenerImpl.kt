package com.yin.yzjcourse.k5类和对象.k6类委托_装饰者模式

class MyListenerImpl:MyListener {
    override fun onSuccess() {
        println("MyListenerImpl onSuccess")
    }

    override fun onGet() {
        println("MyListenerImpl onGet")
    }
}