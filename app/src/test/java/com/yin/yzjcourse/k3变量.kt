package com.yin.yzjcourse

/**
val （来自value ） 一一不可变引用。使用val 声明的变量不能在初始化之
后再次赋值。它对应的是Java 的final 变量。尽量把变量定义成val。

var （来自variable ） 一一可变引用。这种变量的值可以被改变。这种声明对
应的是普通（非final ）的Java 变量。
 */
class k3变量 {
    /**
     * 如果变量没有初始化器，需要显式地指定它的类型
     */
    fun test(){
        val answer: Int
        answer = 42
    }
}