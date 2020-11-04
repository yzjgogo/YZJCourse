package com.yin.yzjcourse.k5类和对象.k1_5属性和访问器.k1属性基础

import org.junit.Test

class MyField {

    /**
     * 主构造器的参数，同时也是类的属性，如果是var默认存在getter和setter，如果是val默认存在getter
     * 只有主构造方法中定义的属性才允许用val。
     */
    class Person(val name: String, var age: Int, var isMarried: Boolean)

    @Test
    fun testClass() {
        var per = Person("Jack", 28, true)
        per.age = 30//默认调用setter
        println("年龄${per.age}")//默认调用getter
        per.isMarried = false//默认调用setter
        println("是否结婚了：${per.isMarried}")//默认调用getter
        println("姓名是：${per.name}")//默认调用getter，无setter,因为是val修饰的
    }



    ////////////////////////////////////////////////////////////////////////////////////////////




    /**
     * 自定义 getter访问器
     */
    class Rectangle(val height: Int, val width: Int) {
        val isBig:Boolean = true;

        //定义的是isSquare的getter，与isBig无关，看位置即可
        val isSquare: Boolean
            get() {
                return height == width
            }
    }

    @Test
    fun testVisit() {
        var rect = Rectangle(40, 20)
        println("是正方形吗：${rect.isSquare}")
    }



    ////////////////////////////////////////////////////////////////////////////////////////////




    /**
     * getter和setter访问器，访问支持字段，自定义getter,setter访问器
     *
     * field用于访问支持字段，setter中即可以读取，也可以写入；getter中可以读取
     * 此处的field就是address的支持字段，也就是其属性值
     */
    class Dog(val name: String) {
        var address: String = "unknow"
            set(value: String) {
                println("重写了setter，这条狗是:$name,原来的地址是:$field,要换成新的地址:$value")
                field = value
            }
            get() {
                println("重写了getter:$field")
                return field//必须返回field不可以返回address,如果返回address就是无限死循环调用getter
            }
    }

    @Test
    fun testDog() {
        val dog = Dog("哈士奇")
        //自动调用setter
        dog.address = "新地址"
        //自动调用getter
        println(dog.address)
    }



    ////////////////////////////////////////////////////////////////////////////////////////////





    /**
     * getter,setter访问器的可见性
     */
    class WordLength {
        var totalLength: Int = 0
            private set //setter设置为私有，只有类内部可调用

        fun addWord(word: String) {
            totalLength += word.length//内部可调用私有的setter
        }
    }

    @Test
    fun testLen() {
        val wl = WordLength()
//        wl.totalLength = 10 //报错，因为你已经将setter设置为私有的了
        wl.addWord("fuck")
        println(wl.totalLength)//getter不变，扔可外部访问
    }
}