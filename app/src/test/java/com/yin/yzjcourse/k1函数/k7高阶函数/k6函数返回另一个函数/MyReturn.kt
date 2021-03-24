package com.yin.yzjcourse.k1函数.k7高阶函数.k6函数返回另一个函数

import org.junit.Test

/**
 * 一个函数返回另一个函数,返回值类型就是函数类型的
 */
class MyReturn {
    data class Person(val name:String,val age:Int)
    fun getFun(p:Person):(Person) -> Boolean{
        println("主函数参数:${p.age}")
//        return {person -> println("返回的参数");person.age>0}
        return {println("返回的参数");it.age>0}//等价于上一行
    }

    @Test
    fun mytest(){
        val mf = getFun(Person("Jack",100))
        mf(Person("Fuck",20))
    }

    @Test
    fun mytest2(){
        println(testReturn(Person("ad",100),200))
    }
    fun testReturn(p:Person,b:Int):(Person) -> String{
//        return {it.toString()}
        return {p -> p.age.toString()}
    }
}