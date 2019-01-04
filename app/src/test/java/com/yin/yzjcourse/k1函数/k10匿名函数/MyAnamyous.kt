package com.yin.yzjcourse.k1函数.k10匿名函数

class MyAnamyous {
    data class Person(val name: String, val age: Int)

    var list = listOf(Person("Jack", 20), Person("Fuck", 30))

    /**
     * 匿名函数没有函数名，其实是lambda的一个替代函数，参数由调用它的函数决定。
     */
    fun show() {
        list.forEach(fun(person) {
            println(person)
        })
    }


    /**
     * 匿名函数可以像普通函数一样定义返回值；
     * 如果这个函数有返回值，对于代码块体的匿名函数，返回值类型不可省略；
     */
    fun getFuck() {
        val flist = list.filter(fun(person): Boolean {
            return person.age > 20
        })
    }

    /**
     * 对于表达式体的匿名函数，返回值类型可以省略.
     */
    fun getFuck2() {
        val flist = list.filter(fun(person) = person.age > 20)
    }
}