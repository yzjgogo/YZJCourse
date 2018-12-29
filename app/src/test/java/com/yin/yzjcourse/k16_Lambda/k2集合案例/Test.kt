package com.yin.yzjcourse.k16_Lambda.k2集合案例

import org.junit.Test

class Test {
    data class Person(val name: String, val age: Int)

    @Test
    fun mytest() {
        var list = listOf(Person("jack",20), Person("Mack",30))
        //maxBy其实是一个函数，该函数的参数也是函数类型，这里传入了lambda函数
        var max1 = list.maxBy { it.age }
        println(max1)

        var max2 = list.maxBy(Person::age)
        println(max2)
    }
}