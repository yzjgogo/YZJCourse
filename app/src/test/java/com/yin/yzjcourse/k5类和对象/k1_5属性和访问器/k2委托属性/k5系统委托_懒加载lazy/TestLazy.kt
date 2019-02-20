package com.yin.yzjcourse.k5类和对象.k1_5属性和访问器.k2委托属性.k5系统委托_懒加载lazy

import org.junit.Test

/**
 * 懒加载：
 * 一般用于获取对象的某个属性时比较耗时(数据库加载，网络加载)，因此当对象创建时并不给该属性初始化值，只有在第一次访问到
 * 该属性时，才开始获取属性值，属性值一旦获取到了，以后再访问该属性，也不会重新获取属性值；
 * 系统提供了 lazy{}来实现这种功能，看[Person]
 *
 * 懒加载也是一种属性委托方式。
 */
class TestLazy {
    @Test
    fun testl() {
        val person = Person("Fuck")
        person.emails//这次会加载属性值调用lazy()，因为属性还没值
        println("*****再一次访问emails******")
        person.emails//已经加载过了 ，不在加载，不在调用lazy()
    }
}