package com.yin.yzjcourse.k1函数

import org.junit.Test

class k2表达式函数体 {


    /**
     * 表达式函数体
     */
    fun max(a: Int, b: Int): Int = if (a > b) a else b

    //can omit return value type
    fun max1(a: Int, b: Int) = if (a > b) a else b

    @Test
    fun testMax() {
        var m = max1(1, 2)
        println("m is $m")
    }
}