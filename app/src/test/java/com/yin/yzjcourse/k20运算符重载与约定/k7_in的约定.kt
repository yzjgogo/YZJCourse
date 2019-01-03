package com.yin.yzjcourse.k20运算符重载与约定

import org.junit.Test

/**
 * in 约定：
 * 以集合的in约定为例解释：用于判断某个对象是否属于集合，实际上用'operator'修饰的contains(element)方法
 * 参考：[com.yin.yzjcourse.k10集合和数组.k1集合基础.k1集合list_set_map.testIn]
 *
 * 同理，我们可以定义自己的'in'约定
 */
class k7_in的约定 {
    data class HorizontalLine(val left: Int, val right: Int) {
        //'operator'修饰的contains方法
        operator fun contains(value: Int): Boolean {
            return value >= left && value <= right
        }
    }

    @Test
    fun testIn() {
        val line = HorizontalLine(1, 10)
        println(2 in line)//等价于:line.contains(2)
    }
}