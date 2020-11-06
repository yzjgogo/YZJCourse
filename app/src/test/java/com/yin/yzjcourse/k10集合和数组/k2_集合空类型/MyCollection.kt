package com.yin.yzjcourse.k10集合和数组.k2_集合空类型

import org.junit.Test

/**
 * 集合的可空和集合元素的可空不同
 * 声明元素可空的集合：List<Int?>
 * 声明可空的集合：List<Int>?
 * 声明集合可空且元素可空的集合：List<Int?>?
 */
class MyCollection {
    @Test
    fun testCollectionNull() {
        testNull(null)
        println("-------------------------------------")
        val list = listOf<Int?>(1, null, 3)
        testNull(list)
    }

    //filterNotNull()过滤掉集合中为null的元素，不为null的元素组合成新的集合返回，原集合不变
    fun testNull(list: List<Int?>?) {
        var result = list?.filterNotNull()
        println(result)
        println(list)
    }
}