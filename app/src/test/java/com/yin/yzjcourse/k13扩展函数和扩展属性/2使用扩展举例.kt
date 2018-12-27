package com.yin.yzjcourse.k13扩展函数和扩展属性

import org.junit.Test

class `2使用扩展举例` {
    @Test
    fun test(){
        //"kotlin"就是接收者对象，就是当前的this
        println("kotlin".secondChar())

        //集合的扩展函数调用
        println(listOf(1,2,3).joinToString())
    }
}