package com.yin.yzjcourse

import org.junit.Test

class KotlinTest {
    /**
     * == and ===
     * 装箱
     *
     * java的Integer的缓存机制：具体可参考http://javapapers.com/java/java-integer-cache/
    简单解释就是Java把-128到127的数都缓存了 引用这个范围内的数都会指向同一个对象，不在这个范围内的就会遵循装箱的规则了，不是同一个对象，所以你定义的 a 大于127就和官网的demo结果一样了，官网用的10000，明显知道这个“坑”
     */
    @Test
    fun testEquals() {
        val a: Int = 1000 //attention[-128,127]
        println(a === a) // true，value equals and obj's address equals too.

        //因为?做了为空判断，所以经过了装箱，创建了两个不同的对象
        val boxedA: Int? = a
        val anotherBoxedA: Int? = a


        println(boxedA === anotherBoxedA) //  false，值相等，对象地址不一样，但是如果a在[-128,127]则为true，看 注释
        //虽然经过了装箱，但是值是相等的，都是10000
        println(boxedA == anotherBoxedA) // true，值相等
    }


    /**
     * label:return ,break ,continue
     * just like java's label.
     */
    @Test
    fun testLabel() {
        //https://www.cnblogs.com/duduhuo/p/6908233.html
    }

    /**
     *
     */
//    @Test
//    fun testClassCast(){
//        var a = ""
//        var str = a.toInt()
//        println(str.)
//    }


/*
    这个类的名称被称为接收者类型；用来调用这个扩展函数的那个对象，叫作接
    收者对象


    在这个扩展函数中，可以像其他成员函数一样用this 。而且也可以像普通的
    成员函数一样，省略它。


    和在
    类内部定义的方法不同的是，扩展函数不能访问私有的或者是受保护的成员

    */
}