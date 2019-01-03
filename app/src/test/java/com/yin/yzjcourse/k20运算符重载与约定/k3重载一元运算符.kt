package com.yin.yzjcourse.k20运算符重载与约定

import org.junit.Test
import java.math.BigDecimal

/**
 * 表达式       函数名
 * +a           unaryPlus
 * -a           unaryMinus
 * !a           not
 * ++a,a++      inc
 * --a,a--      dec
 *
 *函数不可以有参数；
 * 其它条件参考：[com.yin.yzjcourse.k20运算符重载与约定.k1重载二元算数运算符]
 */
class k3重载一元运算符 {
    data class Point(val x: Int, val y: Int)

    /**
     * 对应-a运算符
     */
    operator fun Point.unaryMinus(): Point {
        return Point(-x, -y)
    }

    @Test
    fun testUnaryMinus() {
        val p1 = Point(10, 20)
        val p2 = -p1//等价于 val p2 = p1.unaryMinus()
        println(p2)
    }


    /**
     *
     * kotlin对BigDecimal添加了inc和dec等扩展
     * operator fun BigDecimal.inc() = this + BigDecimal.ONE
     *
     * 当你定义inc和dec函数来重载自增和自减的运算符时，编译器自动支持与
     * 普通数字类型的前缀和后缀自增运算符相同的语义：
     * bd++：先使用bd，再对bd++
     * ++bd：先对bd++，再使用bd
     */
    @Test
    fun testBigDecimal() {
        var bd = BigDecimal.ZERO
        println(bd++)//0 等价于bd.inc()
        println(++bd)//2
        println(--bd)//0
    }

}