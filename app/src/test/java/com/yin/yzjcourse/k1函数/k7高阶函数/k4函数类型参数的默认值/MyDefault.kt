package com.yin.yzjcourse.k1函数.k7高阶函数.k4函数类型参数的默认值

import org.junit.Test

class MyDefault {
    /**
     * operation有默认值，
     * 这个默认值其实就是一个lambda表达式，也就是一个函数体，
     * myfun()被调用时，operation就按这个函数执行
     */
    fun myfun(a: Int, operation: (Int) -> String = { it.toString() }) {
        println(operation(a))
    }

    @Test
    fun test() {
        myfun(10086)//因为myfun()的第二个参数是函数类型且有默认值，所以可以不传参，使用默认值即可
    }
}