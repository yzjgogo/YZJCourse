package com.yin.yzjcourse.k18空处理.k4泛型的可空性

import org.junit.Test

class MyFan {
    /**
     * 泛型参数默认是可空的，等价于'Any?'
     * 这里的T等价于'Any?'
     */
    fun <T> show(t: T) {
        println(t?.hashCode())//因为t可空，所以必须使用安全调用
    }

    @Test
    fun tesfn() {
        show(null)
    }

    /**
     * 怎么让泛型不允许为空
     * 给泛型指定一个非空的边界Any，此时T不可以是null
     */
    fun <T : Any> showInfo(t: T) {
        println(t.hashCode())//t不可能是空
    }

    @Test
    fun testInfo() {
        showInfo("hello")
    }
}