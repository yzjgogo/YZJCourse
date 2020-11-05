package com.yin.yzjcourse.k5类和对象.k6类委托_装饰者模式

class MyObj: MyListenerImpl() {
    override fun onGet() {
        super.onGet()
        println("子类的onGet")
    }

    override fun onSuccess() {
        super.onSuccess()
        println("子类的onSuccess")
    }

    fun childMethod(){
        println("子类自己的方法")
    }
}