package com.yin.yzjcourse.k1函数

import org.junit.Test

class k5中缀调用和解构声明 {


    /**
     * 中缀调用和解构声明(同时声明多个变量)
     * to是一个函数名，只是在使用时一般省略()和. 用空格代替。
     *
     * 自定义中缀调用关键点：
     * 1：函数要用“infix”关键字修饰；
     * 2：函数类似一个扩展函数，要定义到接收者类型下，函数体中的this就是该接收者类型的当前实例；
     * 3：函数必须有且只有一个参数；
     * 4：函数名可以任意，不要求必须是‘to’
     * 5：返回值不要求，可有可无；
     *
     * 解构声明用到了Pair(k,v)类，同时声明两个局部变量
     *
     * 更多解构声明的内容参考:[com.yin.yzjcourse.k20运算符重载与约定.k9_1解构声明_声明多个变量]
     */
//    中缀调用典型用法->map,1 to "one" 等价于 1.to("one")
    val map = mapOf(1 to "one", 2 to "two", 3 to "three")
    //val map1 = mapOf(1.to("one"),2.to("two"),3.to("three"))

    //自定义中缀调用函数
    infix fun String.myto(p: Int) {
        println("参数：$p，$this")
    }

    //自定义一个泛型的中缀函数，并且给了一个返回值，这个返回值用了Pair类，代表一对元素
    infix fun <T, E> T.youto(p: E): Pair<T, E> {
        return Pair(this, p)
    }

    @Test
    fun testTo() {
        "a" myto 200
        //通过Pair实现解构声明，同时声明多个变量，只能是局部变量(方法内的变量)
        val (a1, b1) = 10086 youto 10010//返回一个Pair
        val (a2, b2) = Pair(1, 2)
        println("$a1,$b1   $a2,$b2")
    }
}