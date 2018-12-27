package com.yin.yzjcourse

import org.junit.Test

class KotlinTest {
    /**
     * == and ===
     * 装箱
     *
     * java的Integer的换成机制：具体可参考http://javapapers.com/java/java-integer-cache/
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
     * null处理
     * kotlin will force you to handle nullable value.
     * https://www.cnblogs.com/figozhg/p/6686798.html
     *
     * kotlin how to avoid NullPointerException?
     * Kotlin let check NullPointerException when compile instead of runtime.
     */
    @Test
    fun testNull() {

        //var x:Int? =10
        var x: Int? = null//if you want give a variable 'null' value, you must use '?' decorate this variable.otherwise this variable cann't be null.
//        var y  = x.toDouble()    if x is decorated with '?',you can't write like this,this will report  an error,so yous should judge is null;

        //if x can be null,you should handle null status.
        //handle way 1:
        if (x != null) {
            val y1 = x.toDouble()//except null status.
        }
        //handle way 2:
        val y2 = x?.toDouble()//if x not  null then execute x.toDouble();if x is null then y2 will be null too,and y2's type will be Double?.

        //if you don't want return a null value,you can use ':' define a default value;
        val y3 = x?.toDouble() ?: 0.0//if x is null will return 0.0
        //above line be equivalent to latter code.you will find that kotlin can give 'if' to a variable.
//        val y4 = if (x != null) {
//            x.toDouble()
//        } else {
//            0.0
//        }

        //through above all code,we can handle almost all the NullPointerException.

        //avoid null check.
        //if x use '?' decorated,means that x can be null,if you don't want check null status,you can use '!!' decorate x,but if x really is a null
        //value this will throws NullPointerException,just like java's usual status.so don't try to use '!!'.
        var y5 = x!!.toDouble()
    }

    /**
     *可变长参数函数
     */
    @Test
    fun testKeBian() {
        keBian(1, 2, 3, 4)
    }

    fun keBian(vararg v: Int) {
        for (vi in v) {
            println(vi)
        }
    }


    /**
     * lambda:anonymous fun
     */
    @Test
    fun testLambda() {
        val sumLambda: (Int, Int) -> Int = { x, y -> x + y }
        println(sumLambda(1, 2))  // 输出 3
    }


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


    /**
     * Array
     * array.size --> get array's elements count.
     * array.get(index) --> get the element according to index.
     * array.set(index) --> set the element according to index.
     * ByteArray, ShortArray, IntArray and so on,these all's usage are the same as Array.
     */
    @Test
    fun testArray() {
        //create Array method 1:
        var a = arrayOf(1, 2, 3)//[1,2,3]
        a.set(0, 100)
        a[1] = 200
        println(a.size.toString() + "~" + a.get(0) + "~" + a[1])

        //create Array method 2:
        var b = Array(3, { i -> (i * 2) })//[0*2,1*2,2*2]
    }


    /**
     * if expression.
     * @see testRange
     */
    @Test
    fun testIf() {
        //way 1:
        var a = 1
        var b = 2
        var max = if (a > b) a else b//just like java's x?:y

        //way 2:
        //we can let if's final result give the variable.
        val max1 = if (a > b) {
            print("Choose a")
            a
        } else {
            print("Choose b")
            b
        }
    }


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
        var x = 2
        when (x) {
            1 -> print("x == 1")
            2 -> print("x == 2")
            2, 3 -> print("2 or 3")//can let multi condition get together.use comma split it.
            in 5..9 -> print("in a range")
            !in 10..20 -> print("not in a range")
            is Int -> print("is int")
            else -> { //just like java's switch-case's 'default',when not satisfy any case will execute it.
                print("x 不是 1 ，也不是 2")
            }
        }

        //as a expression
        var boo = when (x) {
            1 -> true
            else -> false
        }

        fun hasPrefix(x: Any) = when (x) {
            is String -> x.startsWith("prefix")
            else -> false
        }

        //when also can instead of 'if-else if' chain.
        var z = 2
        when {
            z == 1 -> print("is 1")
            z == 2 -> print("is 2")
            else -> print("not any")
        }
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