package com.yin.yzjcourse.k5类和对象.k1_1主构造器

import org.junit.Test

class MyMain {

    /**
     *  主构造方法的实现原理：
     *  Uesr1,User2,User3的定义都是等价的；
     *  都有一个主构造器，都有init语句块，都有属性name
     *  构造方法的参数同时也是类的属性
     *  参考[testCon]
     */
    class User1(val name: String)


    class User2(_name: String) {
        val name = _name
    }

    class User9 constructor(val name: String) //也可以

    class User3 constructor(_name: String) {
        val name: String

        init {
            println("User3 init")
            name = _name
        }
    }

    @Test
    fun testCon() {
        val user1 = User1("user1")
        val user2 = User2("user2")
        val user3 = User3("user3")
        println("${user1.name},${user2.name},${user3.name}")
    }


    /**
     * 主构造方法参数可以声明默认值,也可以使用命名参数传递
     * val user4 = User4()//name和isAdult都是默认值
     * val user4 = User4("Lucy")//isAdult用默认值
     * val user4 = User4(name = "Lili",isAdult = true)
     *
     * 这样在大多数情况就满足了，类似java的多构造方法的场景。
     */
    class User4(val name: String = "jack", val isAdult: Boolean = false)

    @Test
    fun testDef() {
        val user41 = User4()
        val user42 = User4("Lili")
        val user43 = User4(isAdult = true)
        println("${user41.name},${user41.isAdult}" +
                "\n${user42.name},${user42.isAdult}" +
                "\n${user43.name},${user43.isAdult}")
    }
}