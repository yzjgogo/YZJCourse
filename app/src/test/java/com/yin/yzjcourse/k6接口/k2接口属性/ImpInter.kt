package com.yin.yzjcourse.k6接口.k2接口属性

import org.junit.Test

class ImpInter {
    //在主构造方法中实现接口的抽象属性,同时继承了非抽象属性
    class User1(override val email: String) : User

    //实现接口的抽象属性，同时自定义getter，没有支持字段，,同时继承了非抽象属性
    class User2(val info: String) : User {
        override val email: String
            get() = info.substring(0, 16)
    }

    //实现接口的抽象属性，同时初始化该属性，有支持字段了。,同时继承了非抽象属性
    class User3(val info: String) : User {
        override val email = info.substring(0, 16)
    }

    @Test
    fun test() {
        val user1 = User1("yzjgogo@139.com")
        println("${user1.email},${user1.name}")

        val user2 = User2("yzjgogo@139.comdkjdkfjkd")
        println("${user2.email},${user2.name}")

        val user3 = User3("yzjgogo@139.comdkjdkfjkd")
        println("${user3.email},${user3.name}")
    }
}