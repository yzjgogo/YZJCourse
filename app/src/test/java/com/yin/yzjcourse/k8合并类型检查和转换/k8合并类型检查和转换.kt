package com.yin.yzjcourse.k8合并类型检查和转换

import org.junit.Test

class k8合并类型检查和转换 {
    interface Exter
    class Num(val a:Int): Exter
    @Test
    fun testCast(){
        var n = Num(10)
        testExter(n)
    }

    /**
     * 智能类型转换：
     * 通过is判断某个变量是某个类型后，无需再进行强制类型类型转换，再通过另一个变量接收转换结果，而是直接使用即可。
     */
    private fun testExter(n: Exter) {
        if (n is Num){
            println("试试：${n.a}")//is后，无需强转，直接使用
        }
    }
}