package com.yin.yzjcourse.k16_Lambda.k11带接收者的Lambda_with和apply

import org.junit.Test

class MyReceive {
    /**
     * this指向with的第一个参数对象，
     * 这里的this就是sb对象
     *
     * 因为：
     * 1：with是一个顶层函数；
     * 2：with的参数有两个，第一个参数是T,第二个参数是T的扩展函数类型，因此第二个参数的函数里的this就应该是T的实例
     *
     * 可以对一个对象进行扩展
     *
     * 返回该对象
     */
    @Test
    fun testwith() {
        val sb = StringBuilder()
        fun result() = with(sb) {
            append("hello")//可省略this
            this.toString()//也可以不省略
        }
        println(result())
    }

    /**
     * apply是一个扩展函数
     * 里面的this指向引用apply函数的对象
     * 返回该对象
     */
    @Test
    fun testApply() {
        val sb = StringBuilder()
        fun result() = sb.apply {
            append("myapply")
            this.toString()
        }
        println(result())
    }
}