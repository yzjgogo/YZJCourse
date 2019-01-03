package com.yin.yzjcourse.k19数据类型.k4_Nothing

import org.junit.Test

class MyNothing {
    @Test
    fun myNo() {
        var result: Nothing = tno()
        //下面的代码不会执行，因为上面的tno()方法不会正常结束，例如这里是抛出异常。
//        if (result is Nothing) {
//            println("是Nothing类型:$result")
//        } else {
//            println("不是是Nothing类型")
//        }
    }

    /**
     * 当一个函数不会成功的结束，例如跑出异常，或者无限循环等，可以声明返回类型为Nothing
     * Nothing不存储任何值。
     * 声明了返回值为Nothing的函数永远不会执行完整个方法体，永远不会返回。
     */
    fun tno(): Nothing {
        println("返回nothing")
        throw IllegalAccessException("我是异常")//注释这一行就会报错，因为声明了Nothing
    }
}