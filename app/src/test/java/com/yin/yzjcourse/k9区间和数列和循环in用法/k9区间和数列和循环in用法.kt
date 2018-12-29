package com.yin.yzjcourse.k9区间和数列和循环in用法

import org.junit.Test

class k9区间和数列和循环in用法 {



    /**
     * 区间：range
     * reference [testForLoop] related.
     * @see testForLoop
     */
    @Test
    fun testRange() {
        var i = 1
        if (i in 1..10) { // [1,10]
            println(i)
        }

        var c = '2'
        if (c in '0'..'9') {
            println(c)
        }

        //in 也可以用与集合
        println("判断某个元素在不在集合里：${"kotlin" in setOf("java","kotlin")}")
    }

    /**
     * for loop
     */
    @Test
    fun testForLoop() {
        //for loop with range.
        for (i in 1..4) print(i) // output “1234”
        for (i in 4..1) print(i) // output nothing ,because can't output in the opposite direction
        //use 'step' assign step size
        for (i in 1..4 step 2) print(i) // output “13”
        for (i in 4 downTo 1 step 2) print(i) // output  “42”,if you want output in the opposite direction  you can use 'downTo' keyword.
        //use 'until' except the last element.
        for (i in 1 until 10) {   // i in [1, 10) not include 10.
            println(i)
        }

        //through index loop array or list and so on.
        var ints = arrayOf(11, 22, 33)
        for (i in ints.indices) {
            println(ints[i])
        }

        //loop index and element at the same time.
        for ((index, value) in ints.withIndex()) {
            println("the element at $index is $value")
        }

        //forEach loop
        val mycount = intArrayOf(111, 222, 333, 0, 444, 555, 666)
        mycount.forEach {
            print(it)
        }
        println("分割")

        //use anonymous fun.
        mycount.forEach(fun(value: Int) {
            print(value)
        })
    }
}