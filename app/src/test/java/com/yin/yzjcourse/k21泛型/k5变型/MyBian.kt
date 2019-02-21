package com.yin.yzjcourse.k21泛型.k5变型

import org.junit.Test

/**
 * 变型：用于相同的基础类型，而具有不同泛型的类型实参的类型之间的关系
 * 例如：List<String> 和 List<Any> 拥有相同的基础类型List,但是也拥有不同的泛型类型实参String和Any，因此List<String> 和 List<Any>互为变型
 */

class MyBian {
    /**
     * 如果列表是不可变的(只读的)，则可以传递具有不同泛型类型实参的变型
     */
    @Test
    fun testOnlyRead() {
        val list = listOf("aa", "bb")
        onlyRead(list)
    }

    fun onlyRead(list: List<Any>) {
        println(list.joinToString())
    }

    /**
     * 如果列表是可变的，因为涉及到列表中元素的修改和添加，则存在线程不安全的问题，因此编译器不允许传递不同泛型类型实参的变型。
     * 这在编译阶段就限制了，因此，如果你传递了，则会报错
     */
    @Test
    fun testWrite() {
        val list = mutableListOf("aa", "bb")
//        write(list)//编译错误，因为write的参数集合是可变的
        writeStr(list)//相同的泛型类型可以传递
    }

    fun write(list: MutableList<Any>) {
        list.add(40)
    }

    fun writeStr(list: MutableList<String>) {
        list.add("cc")
    }


}