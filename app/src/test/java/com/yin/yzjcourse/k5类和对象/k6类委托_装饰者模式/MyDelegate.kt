package com.yin.yzjcourse.k5类和对象.k6类委托_装饰者模式

/**
 * impl是被装饰的对象；
 * MyDelegate是装饰者类，用于装饰impl;
 * 通过by关键字，MyDelegate代理了对象impl的所有属性和方法，也就是说impl有的方法或属性，MyDelegate实例都有，可直接调用，
 *      其实调用的就是imp的。
 *
 * 关键点：MyDelegate和MyListenerImpl都有共同的基类；
 *
 * 结果好处：
 * 不用改变MyListener和MyListenerImpl的解构，就实现了impl新功能的扩展，比继承优雅多了，因为经常改类解构会导致系统不稳定。
 */
class MyDelegate(val impl: MyListenerImpl = MyListenerImpl()) : MyListener by impl {
    //可以根据需要重写某个函数,扩展impl的功能
    override fun onGet() {
        impl.onGet()
        println("MyDelegate onGet")
    }

    //对于那些不重写的，隐藏在幕后，你看不到但是可调用，例如MyDelegate实例仍可调用onSuccess()
}