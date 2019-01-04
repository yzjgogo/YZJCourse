package com.yin.yzjcourse.k1函数.k7高阶函数.k5函数类型参数可空

import org.junit.Test

class MyNull {
    /**
     * operation定义成空的，因为一个函数类型的实例，是一个包含了invoke()方法的接口的具体实现
     * invoke()的参数和返回值由这个函数变量的定义决定；
     * 所有可以采用安全调用invoke()的方式调用该函数
     *
     * 如果operation为null则直接返回null，否则执行invoke(),invoke()函数就是operation的函数体。
     *
     * 参考：[com.yin.yzjcourse.k1函数.k7高阶函数.k3java调用有函数类型参数的kotlin函数.MyJava]
     */
    fun myfun(a: Int, operation: ((Int) -> String)?) {
        println(operation?.invoke(a))
    }

    @Test
    fun test() {
        myfun(10086,null)//因为myfun()的第二个参数是函数类型且有默认值，所以可以不传参，使用默认值即可
    }
}