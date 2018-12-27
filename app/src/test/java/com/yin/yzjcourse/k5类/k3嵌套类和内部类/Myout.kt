package com.yin.yzjcourse.k5类.k3嵌套类和内部类

class Myout(val name:String) {
    /**
     * java：
     * 内部类：在外部类内部定义的，没有使用static修饰的类，可以访问外部类的实例；
     * 嵌套类：在外部类内部定义的，使用static修饰的类，不可以访问外部类的实例；
     *
     * kotlin：
     * 内部类：在外部类内部定义的，使用inner修饰的类，可以访问外部类的实例；
     * 嵌套类：在外部类内部定义的，没有使用inner修饰的类，不可以访问外部类的实例；
     *
     * 具体用法看 [TestOut]
     * @see TestOut
     */

    //嵌套类只是位置上恰好在外部类内部定义，其实与外部类没有任何关联，更不可访问外部类的实例
    class MyNest{
        fun nestFun(){
//            println("嵌套类获取外部类成员:${this@Myout.name}") 报错，嵌套类无法访问外部类实例
            println("我是嵌套类")
        }
    }

    //内部类属于外部类的一个实例，就好像是外部类的一个属性，一个方法一样的地位
    inner class MyInner{
        fun getOutInstance():Myout{
            return this@Myout
        }
        fun showOutName(){
            println("内部类获取外部类成员:${this@Myout.name}")
        }
    }
}