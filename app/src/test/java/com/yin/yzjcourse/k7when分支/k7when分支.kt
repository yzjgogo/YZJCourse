package com.yin.yzjcourse.k7when分支

import org.junit.Test

class k7when分支 {


    /**
     * when expression
     * just like java's switch-case
     * when can as a expression also can as a statement.
     *
     * according to the condition order,compare the condition one by one,if which condition if found then execute it,
     * but not compare the after continue any more.
     */
    @Test
    fun testWhen() {
        //as a statement
        var x = 1
        when (x) {
            1 -> print("x == 1")
            2 -> print("x == 2")
            2, 3 -> print("2 or 3")//can let multi condition get together.use comma split it.
            in 5..9 -> print("in a range")
            !in 11..20 -> print("not in a range")
            is Int -> print("is int")
            else -> { //just like java's switch-case's 'default',when not satisfy any case will execute it.
                print("x 不是 1 ，也不是 2")
            }
        }

        println("\n--------------------------------------------------------------")

        //as a expression,可以有返回值
        var boo = when (x) {
            1 -> true
            else -> false
        }
        println("返回值:$boo")

        fun hasPrefix(x: Any) = when (x) {
            is String -> x.startsWith("prefix")
            else -> false
        }

        println("执行hasPrefix:${hasPrefix("prefix_oooooo")}")

        //when also can instead of 'if-else if' chain.
        var z = 2
        when {
            z == 1 -> print("is 1")
            z == 2 -> print("is 2")
            else -> print("not any")
        }
    }
}