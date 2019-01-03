package com.yin.yzjcourse.k18空处理.k5_kotlin访问java的类型判断

import org.junit.Test

/**
 * kotlin访问java的变量和属性时，不会强制你进行空判断，这种情况下访问的java的不知是否可空的类型，称为kotlin的
 * 平台类型，你不可以在kotlin中定义该类型，这只是针对访问java提出的概念。
 *
 * 平台类型：kotlin访问java变量时，针对该变量的类型。
 *
 */
class MyKot {
    /**
     * kotlin访问java的属性或变量时，是不知道该属性或变量的可控性的，
     * 因此我们既可以把它当成可空类型也可以把它当成非空类型 ，但是你必须对你的决定负责 ，要做好处理各种情况的准备；
     */
    @Test
    fun testVisitJava() {
        val myJack = MyJavaClass("jack")
        val myNull = MyJavaClass(null)
        //你可以把它当成非空的处理，但是可能出现空指针异常，要做好处理
        println(myJack.name.toUpperCase())
        //也可以当成可空的类型处理
        println(myNull.name ?: "UnKnow".toUpperCase())
    }


    /**
     *
     * 同样的道理，kotlin类继承java类重写java的方法时，方法的参数或返回值你既可以定义成可空的，也可以定义成非空的；
     */
    class Person : MyJavaInterface {
        override fun showinfo(str: String?): String {
            println("重写该方法后，我把参数设置成可空的，返回值设置成非空的")
            return str ?: "OK"
        }
        //下面这种也可以
//        override fun showinfo(str: String):String? {
//            println("重写该方法后，我把参数设置成非空的，返回值设置成可空的")
//            return str?:"OK"
//        }
    }

    @Test
    fun testExtendJava() {
        val p = Person()
        println(p.showinfo(null))
        println(p.showinfo("Haha"))
    }
}