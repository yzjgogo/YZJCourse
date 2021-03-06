package com.yin.yzjcourse.k21泛型.k4泛型运行时擦除

import org.junit.Test

/**
 * 和java一样kotlin在运行时，泛型信息会被擦除
 * 例如，你不知道在运行时一个List是String类型的还是Int类型的，因为运行时不再存储泛型类型参数；
 */
class K1泛型擦除 {
    /**
     * 类型擦除对‘is’的影响
     */
    @Test
    fun testIs() {
        val value = listOf<Int>()

        //错误的使用，因为<String>在运行阶段被擦除，因此运行时执行这段代码会没法比较，所以
        //kotlin在编译阶段就给你报错
//        if (value is List<String>) {}

        //kotlin中的泛型类型必须指定类型实参
//        if (value is List) {} 错误，没有指定类型实参
        //*可以理解成未知类型实参的泛型类型。
        if (value is List<*>) {

        }
    }


    /**
     * 类型擦除对'as'的影响
     *
     * as操作一般在编译器不会报错，但会提示你“ unchecked cast ”
     * 但是在运行时如果类型实参不匹配则会报错
     *
     * 就是说给你机会转，但是运行期间会发生什么都不好说。
     */
    @Test
    fun testAs() {
        printSum(listOf("1","2"))//ClassCastException
    }
    fun printSum(c: Collection<*>) {
        //因为<Person>被擦除，所以，强转成功得到结果pList，其实就是原String类型的list，只是泛型被忽略了
        val pList = c as? List<Person>
                ?: throw IllegalArgumentException("Person异常")
        println("&&&&&&&&&&&&&& $pList &&&&&&")

        //下面两行编译器认为类型不兼容Perosn
//        println("&&&&&&&&&&&& ${pList[0] is Int} &&&")
//        println("&&&&&&&&&&&& ${pList[0] is String} &&&")


        //虽然你写了<Int>,但是运行时as并不知道他要强转成<Int>，因为<Int>被擦除了，所以得到的结果intList实际上还是String类型，在执行intList.sum()时会报错
        val intList = c as? List<Int>
                ?: throw IllegalArgumentException("异常")
        println(intList)
        println(intList.sum())
    }
    class Person
}