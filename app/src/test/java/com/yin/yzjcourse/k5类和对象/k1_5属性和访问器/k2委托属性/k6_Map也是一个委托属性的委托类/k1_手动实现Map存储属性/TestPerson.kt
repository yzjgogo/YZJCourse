package com.yin.yzjcourse.k5类和对象.k1_5属性和访问器.k2委托属性.k6_Map也是一个委托属性的委托类.k1_手动实现Map存储属性

import org.junit.Test

class TestPerson {
    @Test
    fun testP(){
        val p = Person()
        val maps = mapOf("name" to "Jack","company" to "ALi")
        for ((key, value) in maps) {
            p.setAttribute(key,value)
        }
        println(p.name)
    }
}