package com.yin.yzjcourse.k16_Lambda.k6Lambda访问变量

import org.junit.Test

/**
 * lambda表达式可以访问调用它的函数的局部变量，这个变量不是final的也可以，不仅可以读取该变量，还可以修改该变量
 *
 * 注意，默认情况下，局部变量的生命期被限制在声明这个变量的函数中。但是如果它被lambda 捕捉了，
 * 使用这个变量的代码可以被存储并稍后再执行。你可能会问这是什么原理。当你捕捉final变量时，它
 * 的值和使用这个值的lambda代码一起存储。而对非final变量来说，它的值被封装在一个特殊的包装器中，
 * 这样你就可以改变这个值，而对这个包装器的引用会和lambda 代码一起存储。
 */
class MyVisit {
    fun bianli(list: List<String>, prefix: String) {
        var count = 0
        list.forEach {
            count++
            println("$prefix,$it,$count")
        }
    }

    @Test
    fun test() {
        val list = listOf("a", "b", "c")
        bianli(list, "fuck")
    }
}