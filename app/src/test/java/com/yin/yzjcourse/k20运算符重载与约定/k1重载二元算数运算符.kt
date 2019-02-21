package com.yin.yzjcourse.k20运算符重载与约定

import org.junit.Test

/**
 * 重载二元算数运算符：目的是用数学运算符表示函数调用
 * 可重载的二元算数运算符
 * a + b   plus
 * a - b   minus
 * a * b   times
 * a / b   div
 * a % b   mod
 *
 * 可重载的运算符是有限的，就是上面这几个，函数名也是固定的；
 * 目标函数要用'operator'关键字修饰，该关键子的作用范围包括实现或重写了它的方法，
 *          实现或重写了它的方法不需再次声明operator，例如Any的equals方法就用'operator'声明，但是子类不需再次用'operator'声明。
 * 运算符的两个操作数和返回值类型可以各不相同；
 * 一般把运算符声明为扩展函数，这样才方便给其它第三方框架以及自己已经存在的类定义，当然也可以定义成普通函数；
 * 运算符的优先级与算数运算符的优先级基本一样，例如a+b*c等价于a+(b*c)；
 * 一般不能颠倒操作数顺序，例如a*b一般不等价于b*a除非在类A和类B都定义相同的times函数；
 *
 */
class k1重载二元算数运算符 {
    /**
     * 声明成普通的函数
     */
    data class Point(val x: Int, val y: Int) {
        //plus用operator修饰，可以通过'+调用'
        operator fun plus(other: Point): Point {
            return Point(x + other.x, y + other.y)
        }
    }

    @Test
    fun testPlus() {
        val p1 = Point(10, 20)
        val p2 = Point(30, 40)
        val p3 = p1 + p2//等价于:val p3 = p1.plus(p2)
        println(p3)
    }


    /**
     * 声明成扩展函数
     * 声明成Char的扩展函数
     * 切两个操作数和返回值类型各不相同
     */
    operator fun Char.times(count: Int): String {
        return toString().repeat(3)
    }

    @Test
    fun testTimes() {
        val char = 'a'
        val str = char * 3//等价于 val str = char.times(3)
        println(str)
    }


    /**
     * 集合的运算符重载的扩展函数
     */
    @Test
    fun testCollection() {
        val list1 = arrayListOf(11, 22, 33)
        val list2 = arrayListOf(44)
        val list3 = arrayListOf(11)
        //plus函数
        val list = list1 + list2//产生一个新的集合 返回
        println(list)

        //minus函数
        val list4 = list1 - list3//产生一个新的集合返回
        println(list4)
    }
}