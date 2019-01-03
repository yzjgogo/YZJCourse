package com.yin.yzjcourse.k20运算符重载与约定

import org.junit.Test

/**
 * 符合赋值运算符:+= -=等等
 * +=   plusAssign
 * -=   minusAssign
 * *=   timesAssign
 * 似乎还有其它的，待补充
 *
 * 重载符合赋值运算符的函数返回值应该是Unit；
 * 其它条件参考：[com.yin.yzjcourse.k20运算符重载与约定.k1重载二元算数运算符]
 */
class k2重载符合赋值运算符 {

    /**
     * Kotlin标准库为可变集合定义了plusAssign扩展函数,对应运算符'+='
    operator fun <T> MutableCollection<T>.plusAssign(element: T){
    this.add(element)
    }
     */
    @Test
    fun testPlusAssign() {
        val list = arrayListOf("a", "b", "c")
        list += "e"
        println(list)

        list -= "a"
        println(list)
    }
}