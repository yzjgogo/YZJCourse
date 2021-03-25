package com.yin.yzjcourse.k5类和对象.k1_6延迟初始化属性lateinit

import org.junit.Test

/**
 * https://www.jianshu.com/p/24fdd70fdbce?utm_source=oschina-app
 *
 * 延迟初始化属性用lateinit修饰
 * 只有主构造函数定义的属性可以用val修饰，因此延迟初始化的属性也只能用var修饰；
 *
 * 为什么要引入延迟初始化属性？
 * 对于非空属性，对象创建时必须赋值，无法延迟初始化；
 * 对于可空属性，虽然可以不在对象创建时初始化，但是非空判断太多，导致代码可读性差；
 * 因此引入延迟初始化属性。
 */
class MyLate {
    class Person {
        lateinit var name: String

        fun setNewName(_name: String) {
            name = _name
        }
    }

    @Test
    fun testLate() {
        val p = Person()
        //延迟初始化的属性，如果在被初始化前就访问，则会报错
//        println("加载前:${p.name}")//lateinit property name has not been initialized
        p.setNewName("jack")
        println("加载后:${p.name}")
    }
}