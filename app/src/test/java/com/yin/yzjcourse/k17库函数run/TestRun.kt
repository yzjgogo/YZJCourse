package com.yin.yzjcourse.k17库函数run

import org.junit.Test

/**
 * 库函数run()用来执行一个代码块，用{}包裹
 */
class TestRun {
    @Test
    fun mytest(){
        run{
            var a = 1+2
            println(a)
        }
    }
}