package com.yin.yzjcourse.k16_Lambda.k11带接收者的Lambda_with和apply

import org.junit.Test

class MyReceive {
    /**
     * this指向with的第一个参数对象，
     * 这里的this就是sb对象
     *
     * 可以对一个对象进行扩展
     */
    @Test
    fun testwith() {
        val sb = StringBuilder()
        fun result() = with(sb) {
            append("hello")//可省略this
            this.toString()//也可以不省略
        }
    }

    /**
     * apply是一个扩展函数
     * 里面的this指向引用apply函数的对象
     */
    @Test
    fun testApply() {
        val sb = StringBuilder()
        fun result() = sb.apply {
            append("myapply")
            this.toString()
        }
    }
}