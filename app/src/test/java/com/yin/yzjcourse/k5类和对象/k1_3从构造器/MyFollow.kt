package com.yin.yzjcourse.k5类和对象.k1_3从构造器

import org.junit.Test

class MyFollow {


    /**
     * 从构造方法(非默认 构造方法)
     *
     * 从构造器的参数不可以是val类型的；
     * 从构造器的参数默认不是类的属性，需要自己处理转化为类的属性，这和java构造器一样；
     * 使用从构造器构造对象时，init初始化块仍然执行；
     * 虽然init会执行，但init内部引用不到从构造器的参数；
     *
     */
    //错误，设置了主构造方法就不能有从构造方法
//    open class User7(add:String){
//        constructor(isAdult: Boolean)
//        constructor(isAdult: Boolean,age: Int)
//    }
    //设置了从构造方法就不能有主构造方法，即使是空的主构造方法也没有，即无法通过val user7 = User7()创建对象
    open class User7 {
        init {
//            println("User7 init${this.isAdult}") 虽然init会执行，但init内部引用不到从构造器的参数
            println("User7 init")
        }

        constructor(isAdult: Boolean)
        constructor(isAdult: Boolean, age: Int)
    }
    //Uer7后没有括号，因为User7没有主构造方法
    class Student2 : User7 {
        var add: String//属性

        init {
            println("Student2 init")
        }

        //可以像java一样用this调用自己的另一个构造器
        constructor(_add: String, isAdult: Boolean) : this(_add, isAdult, 0) {
            add = _add
        }

        //可以通过super调用父构造器
        constructor(_add: String, isAdult: Boolean, age: Int) : super(isAdult, age) {
            add = _add
        }
    }

    @Test
    fun test7() {
//        val user7 = User7()报错，不再有主构造器 ，因为有从构造器
        val user7 = User7(true)
        println("分割")
        val stu2 = Student2("home", false)
        println(stu2.add)
    }

}