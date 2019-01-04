package com.yin.yzjcourse.k16_Lambda.k4Lambda表达式

import com.yin.yzjcourse.k16_Lambda.k3函数类型声明.FunType
import org.junit.Test

/**
 * Lambda表达式就是声明函数类型时，”=“右边的部分
 * {x:Int, y:Int -> x + y}
 * 模式是：{实参1(:类型)，实参2(:类型)... -> 函数体}
 *
 * 注意点：
 * 要用{}包裹;
 * 其中的参数是实参，不是形参；
 * 参数类型根据是否可以从上下文推导出来，来决定是否省略；
 * 可以将Lambda表达式给一个变量接收，这个变量就是函数类型的变量；参考：[FunType] [myt]
 * 整个Lambda就是一个函数名，可使用Lambda直接调用；[direct]
 */
class LamPattern {
    //用一个变量接收一个Lambda表达式,这个变量就是一个函数类型，可以作为函数名使用
    val sum = { x: Int, y: Int -> x + y }

    @Test
    fun myt() {
        println(sum(1, 2))
    }


    //直接调用Lambda表达式,
    @Test
    fun direct() {
        //但是这样做缺乏可读性，也没有意义
        { println("哈哈") }()//这个Lambda只有函数体

        //run可以执行一段代码块
        run { println("嘿嘿") }

        run {
            var a = 3 + 4
            println(a)
        }
    }


    //Lambda表达式的函数体并不一定是单行，可以更复杂
    val lam = { x: Int, y: Int ->
        val a = x + y + 2
        println(a)
    }

    @Test
    fun testl() {
        lam(1, 2)
    }
}