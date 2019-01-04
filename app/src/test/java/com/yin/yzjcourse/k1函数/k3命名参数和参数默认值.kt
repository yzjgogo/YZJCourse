package com.yin.yzjcourse.k1函数

import org.junit.Test

class k3命名参数和参数默认值 {


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
    fun testJoin() {
        val list = listOf(1, 2, 3)
        println(joinToString(list, ",", "*", "*"))
        println(joinToString(list, separator = "$", prefix = "@", postfix = "@"))//使用命名参数
        println(joinToString(list))//使用默认参数值
        println(joinToString(list, prefix = "%"))//即用了命名参数，也用了默认参数值
    }
}