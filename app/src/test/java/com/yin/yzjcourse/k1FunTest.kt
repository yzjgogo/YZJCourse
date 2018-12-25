package com.yin.yzjcourse

import org.junit.Test

class k1FunTest {
    /**
     * no paras and no return value
     */
    fun test1() {
        println("test1")
    }

    /**
     * have paras and no return value
     */
    fun test2(a: Int, b: String) {
        println("test2")
    }

    /**
     * have paras and have return value
     */
    fun test3(a: Int): String {
        println("test3")
        return "test3"
    }


    /**
     * 表达式函数体
     */
    fun max(a: Int, b: Int): Int = if (a > b) a else b

    //can omit return value type
    fun max1(a: Int, b: Int) = if (a > b) a else b

    @Test
    fun testMax() {
        var m = max1(1, 2)
        println("m is $m")
    }


    /**
     * 命名参数和参数默认值
     */
    fun <T> joinToString(collection: Collection<T>,
                         separator: String = ",",
                         prefix: String = "(",
                         postfix: String = ")"): String {
        val result = StringBuilder(prefix)
        for ((index, element) in collection.withIndex()) {
            if (index > 0) result.append(separator)
            result.append(element)
        }
        result.append(postfix)
        return result.toString()
    }
    @Test
    fun testJoin(){
        val list = listOf(1,2,3)
        println(joinToString(list,",","*","*"))
        println(joinToString(list,separator = "$",prefix = "@",postfix = "@"))//使用命名参数
        println(joinToString(list))//使用默认参数值
        println(joinToString(list,prefix = "%"))//即用了命名参数，也用了默认参数值
    }
}