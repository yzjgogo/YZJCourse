package com.yin.yzjcourse.k1函数

import org.junit.Test

class k1FunTest {
    /**
     * no paras and no return value
     */
    fun test1() {
        println("test1")
    }

    /**
     * have paras and no return value
     */
    fun test2(a: Int, b: String) {
        println("test2")
    }

    /**
     * have paras and have return value
     */
    fun test3(a: Int): String {
        println("test3")
        return "test3"
    }


    /**
     * 表达式函数体
     */
    fun max(a: Int, b: Int): Int = if (a > b) a else b

    //can omit return value type
    fun max1(a: Int, b: Int) = if (a > b) a else b

    @Test
    fun testMax() {
        var m = max1(1, 2)
        println("m is $m")
    }


    /**
     * 命名参数和参数默认值
     */
    fun <T> joinToString(collection: Collection<T>,
                         separator: String = ",",
                         prefix: String = "(",
                         postfix: String = ")"): String {
        val result = StringBuilder(prefix)
        for ((index, element) in collection.withIndex()) {
            if (index > 0) result.append(separator)
            result.append(element)
        }
        result.append(postfix)
        return result.toString()
    }

    @Test
    fun testJoin() {
        val list = listOf(1, 2, 3)
        println(joinToString(list, ",", "*", "*"))
        println(joinToString(list, separator = "$", prefix = "@", postfix = "@"))//使用命名参数
        println(joinToString(list))//使用默认参数值
        println(joinToString(list, prefix = "%"))//即用了命名参数，也用了默认参数值
    }


    /**
     * 可变参数和展开运算符
     *
     * 可变参数以数组的形式接收多个参数，这里v就是一个数组
     *
     * 传参时如果想一次性将多个参数打包成数组传参，则需要用展开运算符"*",否则系统默认你传递的只是一个数组参数，而不是数组的各个元素分别是参数
     */
    fun argfun(vararg v: Int) {
        println(v.size)
        for (i in v) {
            println(i)
        }
    }

    @Test
    fun testarg() {
        //一次性传输多个参数
        argfun(1, 2, 3)

        //将多个参数打包成数组传输，注意要用“*”,否则系统默认你只是传递了一个数组参数
        val iarr = intArrayOf(1, 2, 3)
        argfun(*iarr)//可以将所有参数打包成数组
        argfun(0, *iarr)//也可以将部分参数打包成数组，与其余参数一同传递，目标函数同样将所有参数放到一个数组里
    }


    /**
     * 中缀调用和解构声明(同时声明多个变量)
     * to是一个函数名，只是在使用时一般省略()和. 用空格代替。
     *
     * 自定义中缀调用关键点：
     * 1：函数要用“infix”关键字修饰；
     * 2：函数类似一个扩展函数，要定义到接收者类型下，函数体中的this就是该接收者类型的当前实例；
     * 3：还是必须有且只有一个参数；
     * 4：函数名可以任意，不要求必须是‘to’
     * 5：返回值不要求，可有可无；
     *
     * 解构声明用到了Pair(k,v)类，同时声明两个局部变量
     */
//    中缀调用典型用法->map,1 to "one" 等价于 1.to("one")
    val map = mapOf(1 to "one", 2 to "two", 3 to "three")
    //val map1 = mapOf(1.to("one"),2.to("two"),3.to("three"))

    //自定义中缀调用函数
    infix fun String.myto(p: Int) {
        println("参数：$p，$this")
    }

    //自定义一个泛型的中缀函数，并且给了一个返回值，这个返回值用了Pair类，代表一对元素
    infix fun <T, E> T.youto(p: E): Pair<T, E> {
        return Pair(this, p)
    }

    @Test
    fun testTo() {
        "a" myto 200
        //通过Pair实现解构声明，同时声明多个变量，只能是局部变量(方法内的变量)
        val (a1, b1) = 10086 youto 10010//返回一个Pair
        val (a2, b2) = Pair(1, 2)
        println("$a1,$b1   $a2,$b2")
    }


    /**
    局部函数
    局部函数可访问外部函数的所有变量和成员
    局部变量可以使代码解构更好
     */
    class User(val id: Int, val name: String, val address: String)

    //方式1：正常用法，局部函数直接放到外部函数里
    fun outFun(user: User) {
        fun innerFun(value: String) {
            if (value.isEmpty()) {
                throw Exception("""姓名或地址不能为空:${user.id}""")
            }
        }
        innerFun(user.name)
        innerFun(user.address)

        //do other something
    }

    //方式2：将局部函数放到一个扩展函数里
    fun User.extraFun() {
        fun innerFun(value: String) {
            if (value.isEmpty()) {
                throw Exception("姓名或地址不能为空:$id")
            }
        }
        innerFun(this.name)//没有省略this
        innerFun(address)//可省略this
    }

    fun outFun2(user: User) {
        user.extraFun()//调用扩展函数，间接调用其局部函数innerFun()
        //do other something
    }
}