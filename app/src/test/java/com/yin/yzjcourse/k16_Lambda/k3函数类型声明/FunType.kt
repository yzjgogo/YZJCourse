package com.yin.yzjcourse.k16_Lambda.k3函数类型声明

import com.yin.yzjcourse.k16_Lambda.k4Lambda表达式.LamPattern
import org.junit.Test

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


    /**
     * 函数类型的本质：
     * 一个函数类型的实例，是一个包含了invoke()方法的接口的具体实现
     * invoke()的参数和返回值由这个函数变量的定义决定；
     * 参考：[com.yin.yzjcourse.k1函数.k7高阶函数.k3java调用有函数类型参数的kotlin函数.MyJava]
     *
     * 例如：val myfun1:() -> Unit = { println("无参数，无返回值")}
     * myfun1指向一个接口的具体实现类(也就是这个方法的本质)，
     * 因此myfun1可以调用接口的invoke()方法。invoke()实际上就是myfun1的实际函数体
     */
    @Test
    fun testNature() {
        val myfun1: () -> Unit = { println("无参数，无返回值") }
        println(myfun1.invoke())

        val myfun2: (Int) -> String = { it.toString() }
        println(myfun2.invoke(10086))

        val myfun3: (Int, String) -> String = { a, b -> "$a  $b" }
        println(myfun3.invoke(10010, "hello"))
    }
}