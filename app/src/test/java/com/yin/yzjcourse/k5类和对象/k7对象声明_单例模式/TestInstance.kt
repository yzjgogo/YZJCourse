package com.yin.yzjcourse.k5类和对象.k7对象声明_单例模式

import org.junit.Test

class TestInstance {
    @Test
    fun test(){
        MyInstance.inFun()
        MyInstance.onGet()
        MyInstance.onSuccess()
        MyInstance.age = 10086
        println(MyInstance.age)
    }
}