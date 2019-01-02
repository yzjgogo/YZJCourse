package com.yin.yzjcourse.k16_Lambda.k9函数式接口

import org.junit.Test

/**
 * java的函数式接口
 * 所谓函数式接口：
 * 只有一个抽象方法的java接口
 * 也叫SAM接口；
 *
 * 例如View的OnClickListener，Runnable等等
 *
 * java的函数式接口都适合用lambda表达式
 *
 * lambda在编译的时候都会被编译成匿名类，如果
 */
class MyInter {
    @Test
    fun testRun(){
        val a = 1;
        val myjava = MyJava()
        myjava.setRun { println("我在Runnable的run()中运行，我不会重复重建") }//这个lambda对应的匿名类可以复用，不会重复创建
        myjava.setRun { println("我在Runnable的run()中运行，我会重复重建，$a") }//这个lambda对应的匿名类不可以复用，每次调用都会重复创建该匿名类，因为吃用其所在函数的变量
        val lam = { println("我不会重复创建")}
        myjava.setRun(lam)//lam不会重复创建，因为有变量接收
    }
}