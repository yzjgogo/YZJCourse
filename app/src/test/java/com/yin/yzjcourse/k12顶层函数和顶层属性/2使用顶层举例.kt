package com.yin.yzjcourse.k12顶层函数和顶层属性

import org.junit.Test

class `2使用顶层举例` {

    /**
     * 使用顶层函数或顶层属性，如果不在同一个包内，则需要import引入
     */
    @Test
    fun testTop(){
        //使用顶层函数
        joinToString(setOf(1,2,3))

        //使用顶层属性
        println(DEFAULT_VAL)
    }
}