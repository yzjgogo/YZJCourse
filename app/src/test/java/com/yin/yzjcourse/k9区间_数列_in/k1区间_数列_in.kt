package com.yin.yzjcourse.k9区间_数列_in

import org.junit.Test

class k1区间_数列_in {


    /**
     * 区间：range
     * reference [testForLoop] related.
     * 参考:[com.yin.yzjcourse.k20运算符重载与约定.k8_1_rangTo约定]
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
        println("判断某个元素在不在集合里：${"kotlin" in setOf("java", "kotlin")}")
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
    }
}