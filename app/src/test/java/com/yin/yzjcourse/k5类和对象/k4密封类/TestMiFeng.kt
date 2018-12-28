package com.yin.yzjcourse.k5类和对象.k4密封类

import org.junit.Test

class TestMiFeng {
    /**
     * when表达式处理密封类的子类时，各个分支必须处理了该密封类的所有子类，否则编译错误，也不需要处理else分支
     * 即有几个子类，when表达式就有几个分支
     */
    @Test
    fun testm() {
//        val mfeng = MyMifeng() 报错 不能创建密封类的实例，因为密封类默认只有私有构造器
        val mf: MyMifeng = MyMifeng.My1("my1name")
        val n = when (mf) {
            is MyMifeng.My1 -> "return my1"
            is MyMifeng.My2 -> "return my2"
            is MyMifeng.My3 -> "return my3"
            is My4 -> "return my4"
        }
        println(n)

        //密封类的子类可以是data类
        val my3 = MyMifeng.My3(33)
        println(my3)

        val my4 = My4()
        my4.my4f()
    }
}