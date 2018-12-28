package com.yin.yzjcourse.k5类和对象.k7对象声明_单例模式

import com.yin.yzjcourse.k5类和对象.k6类委托_装饰者模式.MyListener

/**
 * 对象声明：
 * 声明类的同时创建了单例的对象；
 * 这个对象名也是类名；
 * 因为是单例的，所以不可以有构造器(包括主构造器 和从构造器);
 * 可以有属性、方法、初始化语句块，可以继承或实现父类；
 *
 * 对比java的单例模式
 */
object MyInstance : MyListener {
    var age:Int = 100
    //初始化语句块可以有
    init {
        println("init")
    }

    //方法可以有
    fun inFun() {
        println("inFun")
    }

    override fun onGet() {
        println("onGet")
    }

    override fun onSuccess() {
        println("onSuccess")
    }
}