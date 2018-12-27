package com.yin.yzjcourse

import com.yin.yzjcourse.k12顶层函数和顶层属性.joinToString
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

 */
class k11集合list_set_map {
    /**
     *
     */
    @Test
    fun testMap(){
        //创建一个可排序的map，添加、访问元素，迭代元素
        var binaryReps = TreeMap<Char, String>()
        for (c in 'A'..'F'){
            val binary = Integer.toBinaryString(c.toInt())
            binaryReps[c] = binary//根据key向map中添加value
        }
        //同时遍历key和value
        for((letter , binary) in binaryReps){
            println("$letter = $binary")
        }
        println("binaryReps:"+binaryReps.javaClass)//class java.util.TreeMap


        //创建一个map
        val map = hashMapOf(1 to "one",6 to "six",53 to "fifty-three")
        println("map:"+map.javaClass)//class java.util.HashMap


        //创建一个set
        val set= hashSetOf(1, 7, 53)

        //创建一个list
        val list= arrayListOf(1,7,53)

        //获取集合的最后一个元素
        println("最后一个:${list.last()}")

        //获取集合中的最大值，不仅适用于int集合，所有可比较的对象集合都可以
        println("最大值:${list.max()}")


    }
}