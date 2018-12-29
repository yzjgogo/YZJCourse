package com.yin.yzjcourse.k2语句和表达式

import org.junit.Test

/**
 * 在Kotlin 中，除了循环（ for, do 和do/while ）以外大多数控制结构都是
表达式。
 */
class k2语句和表达式 {



    /**
     * if expression.
     * @see testRange
     */
    @Test
    fun testIf() {
        //way 1:
        var a = 1
        var b = 2
        var max = if (a > b) a else b//just like java's x?:y
        println("max is $max")

        //way 2:
        //we can let if's final result give the variable.
        val max1 = if (a > b) {
            print("Choose a")
            a
        } else {
            print("Choose b")
            b
        }
    }





    /**
     * when expression
     */
    @Test
    fun testWhen() {
        var x = 2
        //as a expression
        var boo = when (x) {
            1 -> true
            else -> false
        }
        println("boo is $boo")
    }

}