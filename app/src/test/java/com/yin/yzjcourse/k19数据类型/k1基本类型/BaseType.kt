package com.yin.yzjcourse.k19数据类型.k1基本类型

import org.junit.Test

/**
 * kotlin没有基本数据类型，只有包装类型，但是为了提示数值运算的效率，在变量，属性，参数，返回值中涉及到的数字都会当做java
 * 的基本数据类型处理。
 *
 * 各个类型的数据都有一系列toXXX方法
 */
class BaseType {
    @Test
    fun testba(){
        var a:Int = 100
        a.toByte()
        a.toChar()
        a.toShort()
        a.toLong()
        a.toDouble()
        a.toFloat()
        a.toString()
        a.toBigDecimal()

        var b:String = "100"
        b.toByte()
        b.toCharArray()
        b.toShort()
        b.toLong()
        b.toDouble()
        b.toFloat()
        b.toBigDecimal()

        var c:Long=100
        c.toByte()
                //........
    }}