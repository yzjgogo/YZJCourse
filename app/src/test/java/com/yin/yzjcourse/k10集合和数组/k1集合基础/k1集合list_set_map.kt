package com.yin.yzjcourse.k10集合和数组.k1集合基础

import org.junit.Test
import java.util.*

/**

集合类型        只读             可变
List           listof          mutableListOf 、arrayListOf
Set            setof           mutableSetOf 、hashSetOf 、linkedSetOf 、sortedSetOf
Map            mapof           mutableMapOf 、hashMapOf 、linkedMapOf 、sortedMapOf

Kotlin 没有采用它自己的集合类，而是采用的标准的Java 集合类，
这对Java 开发者是一个好消息。你现在所掌握的所有Java 集合的知识在这里依然
适用。

kotlin只是对这些java里的集合添加了大量的扩展函数，是操作集合更方便

更多内容参考：[com.yin.yzjcourse.k16_Lambda.k8集合库函数和序列.MyCollection]

 */
class k1集合list_set_map {
    class Person(val name: String, val age: Int)

    /**
     *
     */
    @Test
    fun testCollection() {

        //创建一个set
        val set = hashSetOf(1, 7, 53)

        //创建一个list
        val list = arrayListOf(1, 7, 53)

        //获取集合的最后一个元素
        println("最后一个:${list.last()}")

        //获取集合中的最大值，不仅适用于int集合，所有可比较的对象集合都可以
        println("最大值:${list.max()}")

        //根据条件获取最大的元素
        val people = listOf(Person("jack", 20), Person("fuck", 30))
        val maxAge = people.maxBy { it.age }


        //数字集合求和
        val sumlist = listOf(1, 2, 3, 4, 5, 6)
        println("数字集合求和:${sumlist.sum()}")


    }


    /**
     * map的创建与修改
     *
     * map的遍历用到了解构声明(letter,binary),参考:[com.yin.yzjcourse.k20运算符重载与约定.k9_1解构声明_声明多个变量]
     * 参考 kotlin in action page195
     */
    @Test
    fun testMap() {
        //创建一个可排序的map，添加、访问元素，迭代元素
        var binaryReps = TreeMap<Char, String>()
        for (c in 'A'..'F') {
            val binary = Integer.toBinaryString(c.toInt())
            binaryReps[c] = binary//根据key向map中添加value
        }
        //同时遍历key和value
        for ((letter, binary) in binaryReps) {
            println("$letter = $binary")
        }
        println("binaryReps:" + binaryReps.javaClass)//class java.util.TreeMap


        //创建一个map
        val map = hashMapOf(1 to "one", 6 to "six", 53 to "fifty-three")
        println("map:" + map.javaClass)//class java.util.HashMap
    }


    /**
     * toSet
     * 对list去重
     */
    @Test
    fun listToSet() {
        val list = listOf("a", "b", "a", "a")
        val set = list.toSet()
        println(set)
    }


    /**
     * 集合的平均值
     * 计算集合元素的平均值，返回double类型
     */
    @Test
    fun testaverage() {
        val list = listOf(100, 200, 300)
        println(list.average())
    }


    /**
     * 获取指定索引区间的元素集合
     */
    @Test
    fun getIndexRange() {
        val list = listOf("a", "b", "c", "d", "e", "f", "g")
        val mlist = list.slice(1..3)//获取索引1,2,3位置的元素组成新的集合返回
        println(mlist)
    }


    /**
     * 获取集合中指定类型的实例元素，组成新的集合返回
     * filterIsInstance()用到了泛型中的实化类型参数，参考[com.yin.yzjcourse.k21泛型.k4泛型运行时擦除.K2实化类型参数]
     */
    @Test
    fun filterIsInstance() {
        val list = listOf("a", 1, 2.0, "b")
        //获取list中字符串实例的元素
        val result = list.filterIsInstance<String>()
        println(result)
    }


    /**
     * 遍历集合
     * 集合遍历与数组遍历完全相同参考数组的遍历
     */
    @Test
    fun testCollectionIterator() {
        /**
         * [com.yin.yzjcourse.k10集合和数组.k4数组.MyArray.testIterator]
         */
    }


    /**
     * 通过下标访问集合元素
     * 下标也可以访问map元素
     *
     * 也可以通过下标赋值
     *
     * 其实是下标运算符的重载，内部有用'operator'修饰的get和set函数
     * 参考:[com.yin.yzjcourse.k20运算符重载与约定.k6下标运算符get_set约定]
     */
    @Test
    fun testIndexVisit() {
        val list = arrayListOf("a", "b", "c")
        list[0] = "e"
        println(list[0])

        val map = hashMapOf(1 to "one", 2 to "two")
        map[1] = "ooo"
        println(map[1])
        println(map[2])
    }


    /**
     * 运算符重载的集合扩展函数
     * 参考：[com.yin.yzjcourse.k20运算符重载与约定.k1重载二元算数运算符.testCollection]
     * 和[com.yin.yzjcourse.k20运算符重载与约定.k2重载符合赋值运算符.testPlusAssign]
     */
    @Test
    fun testAssign() {
        val list1 = arrayListOf(11, 22, 33)
        val list2 = arrayListOf(44)
        val list3 = arrayListOf(11)
        //plus函数
        val list = list1 + list2//产生一个新的集合 返回
        println(list)

        //minus函数
        val list4 = list1 - list3//产生一个新的集合返回
        println(list4)

        println("******************************************")

        val mlist = arrayListOf("a", "b", "c")
        mlist += "e"
        println(mlist)

        mlist -= "a"
        println(mlist)
    }


    /**
     * 集合用到了‘in’约定
     * 判断某个对象是否属于集合元素
     * 参考：[com.yin.yzjcourse.k20运算符重载与约定.k7_in的约定]
     */
    @Test
    fun testIn() {
        val list = listOf("a", "b", "c")
        println("a" in list)//等价于list.contains("a")

        println("z" in list)//list.contains("z")
    }


    /**
     * 集合的解构声明和组件函数
     */
    @Test
    fun testcomponent() {
        /**
         * 参考:[com.yin.yzjcourse.k20运算符重载与约定.k9_1解构声明_声明多个变量.testCollectionAndArray]
         */
    }
}