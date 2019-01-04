package com.yin.yzjcourse.k1函数.k7高阶函数.k3java调用有函数类型参数的kotlin函数

class MyKotlin {
    /**
     * 无参数，无返回值
     */
    fun kotfun1(operation: () -> Unit) {
        operation()
        println("执行kotfun1")
    }

    fun kotfun2(operation: (Int) -> Unit) {
        operation(10)
        println("执行kotfun2")
    }

    fun kotfun3(operation: (Int) -> String) {
        println("执行kotfun3:${operation(10)}")
    }

    fun kotfun4(str:String,operation: (Int,String) -> String) {
        println("执行kotfun3:${operation(10,str)}")
    }
}