package com.yin.yzjcourse.k16_Lambda.k5Lambda语法演变

import org.junit.Test

class LamConvert {
    data class Person(val name: String, val age: Int)

    @Test
    fun convert() {
        val people = listOf(Person("jack", 20), Person("fuck", 30))

        //演变1：maxBy(selector:(Person) -> Int)的参数是函数类型的形参，因此可以传入一个Lambda
        val max1 = people.maxBy({ p: Person -> p.age })

        //演变2：如果Lambda表达式是调用函数的最后一个参数，则可以将表达式放到外面
        val max2 = people.maxBy() { p: Person -> p.age }

        //演变3：如果Lambda表达式是调用函数的唯一一个参数，则可以省略()
        val max3 = people.maxBy { p: Person -> p.age }

        //演变4：推导出Lambda表达式中实参的类型，省略类型声明
        val max4 = people.maxBy { p -> p.age }

        //演变5：如果Lambda表达式只有一个实参，并且可以推导出该参数类型，则系统会生成默认参数it，it代表某个Person实例，但是在嵌套Lambda表达式中应避免用it
        val max5 = people.maxBy { it.age }

        //以上5种写法等价
        println("$max1,$max2,$max3,$max4,$max5")
    }
}