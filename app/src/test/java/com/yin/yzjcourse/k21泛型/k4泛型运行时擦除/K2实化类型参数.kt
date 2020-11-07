package com.yin.yzjcourse.k21泛型.k4泛型运行时擦除

import org.junit.Test

/**
 * 由[K1泛型擦除]可知，泛型类型在运行时是被擦除的，你无法知道运行时一个类引用了什么类型的泛型，函数也是一样；
 *
 * 什么是实化类型参数？真的实化了吗？
 * 所谓实化类型参数，就是让泛型类或泛型函数在运行时保留泛型的具体类型，看起来就像没有擦除一样，
 * 其实并不是真的实化了，而是采用了内联函数，因为内联函数就是把代码拷贝一份放到调用处，因此调用
 * 内联函数时泛型的类型参数就确定了，确定了后‘reified’关键字就会把泛型(例如：T)，替换成具体的
 * 类型(例如：String)，替换后，内联函数的整套代码放到调用处，就好像没有使用泛型一样，也就谈不上
 * 什么泛型擦除了。
 *
 * 集合的filterIsInstance()函数就用到了实化类型参数
 * 参考：[com.yin.yzjcourse.k10集合和数组.k1集合基础.k1集合list_set_map.filterIsInstance]
 *
 */
class K2实化类型参数 {
    //会报错，因为泛型类型会在运行时擦除，value根本不知道和谁比较
//    fun <T> isA(value: Any) = value is T

    //声明成内联函数，切用'reified'修饰泛型
    //当其它函数调用isA()时T会被替换成具体的类型，再把isA()的整套代码复制到调用出，巧妙的避开了泛型
    inline fun <reified T> isA(value:Any) = value is T

    @Test
    fun doTest(){
        //因为isA()用inline修饰，所以执行isA()其实就是把isA()的代码(函数体)复制一份到调用处(此时如果泛型T用reified修饰，则同时会泛型T换成具体的类型，这里是Int)，
        //因此实际上只是一段代码的复制操作，并不是泛型真的可以不被擦除
        val result = isA<Int>(10086)//因为inline，这里实际上不是函数调用，而是等价于： '10086 is Int' 这段代码，只是把这段代码复制到这个位置而已
        println(result)
    }

    class Person
}