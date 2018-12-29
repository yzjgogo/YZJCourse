package com.yin.yzjcourse.k16_Lambda.k3函数类型声明

import org.junit.Test
import com.yin.yzjcourse.k16_Lambda.k4Lambda表达式.LamPattern

/**
 * 函数也是一个类型
 * 函数类型声明
 * 变量类型 函数名:(形参1类型,形参2类型,...) -> 返回值类型 = {实参1，实参2,... -> 函数体}
 *
 * 其中“=”右边就是Lambda表达式(因为左边声明了参数类型，所以Lambda省略了参数类型)
 * @see LamPattern
 */
class FunType {
    //有参数和返回值
    val sum: (Int, Int) -> Int = { x, y -> x + y }

    //没有参数没有返回值，没有返回值时Unit不可省略
    val action: () -> Unit = { println("哈哈") }

    //返回值可空
    val retNull: (Int, Int) -> Int? = { _, _ -> null }

    //函数类型变量可空,varnull可以为空
    val varnull: ((Int, Int) -> Int)? = null

    @Test
    fun mytest() {
        sum(1, 2)
        action()
        retNull(1, 2)
        varnull?.invoke(1, 2)
    }
}