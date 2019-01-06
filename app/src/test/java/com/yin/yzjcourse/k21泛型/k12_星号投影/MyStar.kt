package com.yin.yzjcourse.k21泛型.k12_星号投影

import org.junit.Test
import java.util.*

/**
 * 当你不知道泛型实参的任何信息时，用*代替，
 * List<*>,当你不知道这个集合是String类型，Int类型，Boolean类型还是其他什么类型时，就可以用List<*>
 * 用了'*'表明我们不关心泛型类型。也就是说不具有消费操作(修改操作)的时候用*，否则就是不安全的，编译器会阻止你这样做。
 *
 * 以集合为例
 * MutableList<*>投影成了MutableList<out Any?>
 */
class MyStar {
    @Test
    fun testStar() {
        val list: MutableList<Any> = mutableListOf('a', 1, "age")
        val chars = mutableListOf('a', 'b', 'c')
        val unknows: MutableList<*> = if (Random().nextBoolean()) list else chars
//        unknows.add(42)//因为不安全，你对泛型类型实参一无所知，不要尝试消费(修改)操作，编译器会阻止你这样做。
        println(unknows.first())//生产(读取)操作是可以的
    }

    fun testList(list: List<*>) {
        println(list.first())
    }
}