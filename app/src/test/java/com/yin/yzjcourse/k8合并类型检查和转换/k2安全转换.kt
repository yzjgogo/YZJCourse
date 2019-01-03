package com.yin.yzjcourse.k8合并类型检查和转换

import org.junit.Test

class k2安全转换 {
    interface Exter
    class Num(val a: Int) : Exter

    @Test
    fun testCast() {
        var n = Num(10)
        testExter(n)
    }

    private fun testExter(n: Exter) {
        //安全转换转换
        //如果n确实是Num类型，则直接转换，否则表达式为null
        val num = n as? Num

        //等价于下面这种写法
        val num1 = if (n is Num) {
            n as Num
        }else{
            null
        }
    }
}