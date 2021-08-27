package com.yin.yzjcourse.k5类和对象.k9对象表达式_匿名内部类

import org.junit.Test

class Test {
    @Test
    fun mytest(){
        testinner(MyView())
    }

    /**
     * object关键字不仅能用来声明单例模式，还能用来声明匿名对象，但是匿名对象不是单例的；
     * 匿名对象代替了java中的匿名内部类的用法；
     * 匿名对象可以实现多个接口或不实现接口；
     * 对象表达式中的代码可以访问创建它的函数里的变量，并且不需要用final修改，之所以不需要用final修饰，是因为这个count被一个对象包裹起来了，实际上onFocus和onClick里对count的访问，是对那个包裹这个count的对象的访问
     * Tools -> Kotlin -> Show Kotlin Bytecode -> Decompile查看
     */
    fun testinner(view: MyView){
        var count = 0//不需要用final修改
        view.addListener(object :ClickListener,Focusable(){
            override fun onFocus() {
                count = 0
            }

            override fun onClick() {
                count ++
            }
        })
    }


    /**
     * 可以用变量接收一个对象
     */
    @Test
    fun test1(){
        var myobj = object :ClickListener{
            override fun onClick() {
                println("被点击了")
            }
        }
        myobj.onClick()
    }
}