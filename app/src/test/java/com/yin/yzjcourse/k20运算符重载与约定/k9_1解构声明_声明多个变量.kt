package com.yin.yzjcourse.k20运算符重载与约定

import org.junit.Test

/**
 * 更多解构声明的内容参考:[com.yin.yzjcourse.k1函数.k5中缀调用和解构声明]
 * 讲到了Pair类
 */
class k9_1解构声明_声明多个变量 {
    /**
     * 对于数据类，编译器为每个在主构造方法中声明的属性都定义了componentN()组件函数，依次是component1,component2,...,注意是从1开始，不是从0开始
     * 这些属性的类型可以不相同
     * 解构声明：val (a,b) = point
     * 等价于：val a = point.component1()
     *        val b = point.component2()
     *
     */
    data class Point(val x: Int, val y: String)

    @Test
    fun testPoint() {
        val point = Point(10, "hello")
        val (a, b) = point
        println("$a , $b")
    }


    /**
     * 对于非数据类，可以定义自己的componentN()函数
     *
     * 主构造方法的多个属性，不一定必须全部定义componentN()函数
     *
     * 使用解构声明val (a,n) = person时，也不是必须声明的变量数和主构造方法的属性数相同，
     * val (a,b,c) = person,  val (a,b) = person都可以，但是一定是按照1,2,3...的方法顺序赋值。
     */
    class Person(val age: Int, val name: String, val add: String, val home: String) {
        operator fun component1() = age
        operator fun component2() = name
        operator fun component3() = add
        operator fun component4() = home
    }

    @Test
    fun testPerson() {
        val person = Person(30, "Jack", "Anhui", "华阳")
        val (a, b) = person//等价于 val a = person.component1() val n = person.component2()
        println("person:$a,$b")
    }


    /**
     * 数组和集合中也有定义componentN()函数
     * 但是数组和集合只定义了component1...component5,因此最多只能同时声明5个变量，超过就报错，但是并不是说该
     * 集合或数组，最多只能有5个元素，而是只能访问前5个元素。
     */
    @Test
    fun testCollectionAndArray() {
        //数组
        val array = arrayOf("aa", "bb", "cc", "dd", "ee", "ff")
        val (a, b, c, d, e) = array
        println("数组:$a,$b,$c,$d,$e")

        //集合
        val list = listOf("aa", "bb", "cc", "dd", "ee", "ff")
        val (a1, b1, c1, d1, e1) = list
        println("集合:$a1,$b1,$c1,$d1,$e1")

    }


    /**
     * 用处：
     * 可用于函数返回多个值，只需将这多个值，组成一个类的属性，然后返回类的实例即可
     */
    fun getPoint(): Point {
        return Point(10, "fuck")//这样就间接返回两个值了。
    }

    @Test
    fun testgetPoint() {
        val (a, b) = getPoint()
        println("用处:$a,$b")
    }

}