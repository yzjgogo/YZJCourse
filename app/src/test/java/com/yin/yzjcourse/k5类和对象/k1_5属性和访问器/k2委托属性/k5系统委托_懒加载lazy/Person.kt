package com.yin.yzjcourse.k5类和对象.k1_5属性和访问器.k2委托属性.k5系统委托_懒加载lazy

/**
 * 懒加载的用法，看[TestLazy]
 */
class Person(val name: String) {
    val emails by lazy {
        println("只有第一次访问emails会调用")
        loadEmails()
    }
}