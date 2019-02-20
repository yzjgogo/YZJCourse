package com.yin.yzjcourse.k1函数.k7高阶函数.k2函数的参数可以是函数

import org.junit.Test

class k2函数的参数可以是函数 {

    /**
     * 定义一个函数，该函数的参数可以接收另一个函数；
     * 其中函数类型的参数中的函数参数列表可以指定变量名，也可以不指定，无论你指不指定我调用该函数时都可以不用；
     *例如：operation: (Int, Int) -> String) 可以指定参数名 operation: (op1:Int, op2:Int) -> String)
     *
     * 具体lambda语法参考:[com.yin.yzjcourse.k16_Lambda.k5Lambda语法演变.LamConvert]
     */
    fun myfun(a: Int, b: Int, operation: (Int, Int) -> String) {
        var result = operation(a, b)
        println("结果：$result")
    }

    @Test
    fun testFun() {
//        myfun(30,40,{a,b -> "$a-$b"}) 这样也可以

        myfun(10, 20) { a, b ->
            "$a-$b"
        }

        //参数名可以任意起
        myfun(10, 20) { a1, b1 ->
            "$a1-$b1"
        }
    }
}