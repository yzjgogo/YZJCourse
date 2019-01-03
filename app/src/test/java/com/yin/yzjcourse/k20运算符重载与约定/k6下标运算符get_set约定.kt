package com.yin.yzjcourse.k20运算符重载与约定

import org.junit.Test

/**
 * 下标运算符，
 * 通过'operator'修饰的get函数可以用下标访问;
 * 通过'operator'修饰的set函数可以用下标赋值;
 *
 * get可以有不止一个参数，例如get(x,y)，可通过[x,y]访问;
 * set可以有不止两个参数，最后一个参数用于通过下标赋值时，等号右边的值，例如set(a,b,v),则[a,b] = v;
 * get和set的参数可以是任意类型，并不必须是int类型，map就可以是任意类型;
 *
 * kotlin已经对集合和map等实现了下标运算符的约定，可直接通过下标访问集合元素
 * 参考：[com.yin.yzjcourse.k10集合和数组.k1集合基础.k1集合list_set_map.testIndexVisit]
 *
 */
class k6下标运算符get_set约定 {
    data class Point(var x: Int, var y: Int) {
        //可通过索引获取
        operator fun get(index: Int): Int {
            return when (index) {
                0 -> x
                1 -> y
                else -> throw IllegalArgumentException("超出范围")
            }
        }

        //可通过索引赋值
        operator fun set(index: Int, value: Int) {
            when (index) {
                0 -> x = value
                1 -> y = value
                else -> throw IllegalArgumentException("又超出范围")
            }
        }
    }

    @Test
    fun testIndexVisit() {
        var point = Point(10, 20)
        point[0] = 100//等价于  point.set(0,100)
        println(point[0])//等价于point.get(0)
    }
}