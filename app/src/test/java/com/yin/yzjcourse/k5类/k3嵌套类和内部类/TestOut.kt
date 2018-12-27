package com.yin.yzjcourse.k5类.k3嵌套类和内部类

import org.junit.Test

class TestOut {
    @Test
    fun testFun(){
        val out = Myout("jack")
        //嵌套类使用,要外部类名.引用
        val nest = Myout.MyNest()
        nest.nestFun()

        //内部类使用，要外部类实例.引用
        val inner = out.MyInner()
        println(inner.getOutInstance().name)//内部类的实例属于外部类的实例
        inner.showOutName()
    }
}