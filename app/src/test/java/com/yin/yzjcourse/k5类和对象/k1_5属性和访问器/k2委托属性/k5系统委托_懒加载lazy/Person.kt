package com.yin.yzjcourse.k5类和对象.k1_5属性和访问器.k2委托属性.k5系统委托_懒加载lazy

/**
 * 懒加载的用法，看[TestLazy]
 */
class Person(val name: String) {
    val emails by lazy {
        println("只有第一次访问emails会调用得到emails的属性值,以后都是emails属性直接就有值了，不用去耗时加载了")
        var eList = loadEmails()
        "$eList,$name"//这就是返回值，也是emails属性懒加载得来的属性值，当然也可以无返回值UNIT
    }

    /*
    //退化1:lazy是一个只有一个函数类型参数的函数
    val emails by lazy() {
        println("只有第一次访问emails会调用")
        var eList = loadEmails()
        "$eList,$name"//这就是返回值，也是emails属性懒加载得来的属性值，当然也可以无返回值UNIT
    }
    */

    /*
    //退化2：lazy是一个只有一个函数类型参数的函数
    val emails by lazy({
        println("只有第一次访问emails会调用")
        var eList = loadEmails()
        "$eList,$name"//这就是返回值，也是emails属性懒加载得来的属性值，当然也可以无返回值UNIT
    })
    */
}