package com.yin.yzjcourse.k16_Lambda.k9函数式接口

import org.junit.Test

/**
 * java的函数式接口，注意只针对java定义，kotlin不可以
 * 所谓函数式接口：
 * 只有一个抽象方法的java接口
 * 也叫SAM接口；
 *
 * 例如View的OnClickListener，Runnable等等
 *
 * java的函数式接口都适合用lambda表达式，当这个接口作为其它函数的参数时，我们可以将这个接口类型的参数看成是函数类型的参数，
 * 这个函数就是接口的抽象方法，因此就可以用lambda了，实际上函数类型的变量确实指向一个包含invoke()方法的接口的具体实例，
 * 参考：[com.yin.yzjcourse.k16_Lambda.k3函数类型声明.FunType]
 *
 * 也就是说如果一个函数的参数是SAM接口，我们可以把这个参数看成是这个SAM接口的唯一函数。
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

        //上面lambda的方式等价于下面匿名类的方式，因为最终lambda也会被编译成匿名类的方式。
        myjava.setRun(object :Runnable{
            override fun run() {
                println("我是匿名对象")
            }
        })
    }
}