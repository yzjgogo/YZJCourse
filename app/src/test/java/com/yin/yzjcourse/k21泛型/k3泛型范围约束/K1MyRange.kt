package com.yin.yzjcourse.k21泛型.k3泛型范围约束

import org.junit.Test

class K1MyRange {
    /**
     * 指定类型实参必须是Number的子类
     * 既然有了限制，就可以调用最大限制的类里定义的方法，这里调用了Number的toDouble()方法。
     */
    fun <T : Number> oneHalf(value: T): Double {
        return value.toDouble()
    }

    /**
     * 约束了类型实参必须实现了Comparable接口
     */
    fun <T : Comparable<T>> max(a: T, b: T): T {
        return if (a > b) a else b
    }

    /**
     * 约束了类型实参必须即是MyFaceOne的子类也是MyFaceTwo的子类
     */
    fun <T> get(seq: T) where T : MyFaceOne, T : MyFaceTwo {
        println("执行了get")
    }
    //有返回值的
    fun <T> catch(seq: T):String where T : MyFaceOne, T : MyFaceTwo {
        println("执行了get")
        return "catch了"
    }
    class Face(val name:String,val age:Int):MyFaceOne,MyFaceTwo {
        override fun face1() {
            println("执行face_1")
        }

        override fun face2() {
            println("执行face_2")
        }
    }

    @Test
    fun doTest(){
        val face = Face("beauty",20)
        val c = catch(face)
        println("--------$c---------")
    }
}