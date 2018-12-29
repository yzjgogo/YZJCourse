package com.yin.yzjcourse.k5类和对象.k8伴生对象

import org.junit.Test

class TestA {
    @Test
    fun mTest(){
        A.Companion.show()//Companion可省略
        A.show()//类似java的静态调用

//        val a = A.Companion.getA("jack")
        val a = A.getA("jack")//省略了Companion
        println(a.name)

        //类名A是接口的实例，注意对象a不是
        testListener(A)

        //调用伴生对象的扩展函数
        A.expfun()
    }

    fun testListener(listener:AListener){
        listener.click()
    }
}
