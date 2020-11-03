package com.yin.yzjcourse.k1函数

import org.junit.Test

class k4可变参数和展开运算符 {


    /**
     * 可变参数和展开运算符
     *
     * 可变参数以数组的形式接收多个参数，这里v就是一个数组
     *
     * 传参时如果想一次性将多个参数打包成数组传参，则需要用展开运算符"*",否则系统默认你传递的只是一个数组参数，而不是数组的各个元素分别是参数
     */
    fun argfun(vararg v: Int) {
        println(v.size)
        for (i in v) {
            println(i)
        }
//        for ((index,element) in v.withIndex()) {
//            println(element)
//        }
        println("---------------------")
    }

    @Test
    fun testarg() {
        //一次性传输多个参数
        argfun(1, 2, 3)

        //将多个参数打包成数组传输，注意要用“*”,否则系统默认你只是传递了一个数组参数
        val iarr = intArrayOf(1, 2, 3)
        argfun(*iarr)//可以将所有参数打包成数组
        argfun(0, *iarr)//也可以将部分参数打包成数组，与其余参数一同传递，目标函数同样将所有参数放到一个数组里
    }
}