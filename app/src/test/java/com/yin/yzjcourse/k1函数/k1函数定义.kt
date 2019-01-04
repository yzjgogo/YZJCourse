package com.yin.yzjcourse.k1函数

import org.junit.Test

class k1函数定义 {
    /**
     * no paras and no return value
     */
    fun test1() {
        println("test1")
    }

    /**
     * have paras and no return value
     */
    fun test2(a: Int, b: String) {
        println("test2")
    }

    /**
     * have paras and have return value
     */
    fun test3(a: Int): String {
        println("test3")
        return "test3"
    }
}