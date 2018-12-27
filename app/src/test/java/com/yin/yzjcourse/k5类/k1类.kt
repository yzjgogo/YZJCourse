package com.yin.yzjcourse.k5类

import org.junit.Test

class k1类 {
    /**
     * 构造器的参数，同时也是类的属性，如果是var默认存在getter和setter，如果是val默认存在getter
     *
     * kotlin的声明默认都是public和final的，例如这里的Person类就是final的，默认不允许继承
     */
    class Person(val name: String, var age: Int, var isMarried: Boolean)

    @Test
    fun testClass() {
        var per = Person("Jack", 28, true)
        per.age = 30//默认调用setter
        println("年龄${per.age}")//默认调用getter
        per.isMarried = false//默认调用setter
        println("是否结婚了：${per.isMarried}")//默认调用getter
    }


    /**
     * 自定义 getter访问器
     */
    class Rectangle(val height: Int, val width: Int) {
        val isSquare: Boolean
            get() {
                return height == width
            }
    }
    @Test
    fun testVisit(){
        var rect = Rectangle(40, 20)
        println("是正方形吗：${rect.isSquare}")
    }
}