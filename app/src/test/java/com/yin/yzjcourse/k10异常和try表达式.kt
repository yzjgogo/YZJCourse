package com.yin.yzjcourse

import org.junit.Test

class k10异常和try表达式 {

    /**
     * 抛出一个异常
     */
    @Test
    fun testThrow() {
        var a = 10
        if (a > 5) {
            throw IllegalArgumentException("我是一个异常")
        }
    }


    /**
     * try-catch-finally
     * 和java一样，但是不需要throws出异常
     */
    @Test
    fun testCatch() {
        var i = catch("test")
        println("调用结果1：$i")
        println("*************************")
        var j = catch("100")
        println("调用结果2：$j")
    }

    private fun catch(a: String): Int {
        try {
            return a.toInt()
        } catch (e: Exception) {
            return 10086
        } finally {
            println("finally执行:$a")
        }
    }


    /**
     * try表达式
     */
    @Test
    fun testTry() {
        var a: Int = try {
            100//返回100
        } catch (e: Exception) {
            return//程序不再向下执行
//            200 也可以不用return,返回一个默认值
        }
        println("try表达式：$a")
    }
}