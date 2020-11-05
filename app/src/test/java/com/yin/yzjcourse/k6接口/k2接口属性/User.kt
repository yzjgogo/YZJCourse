package com.yin.yzjcourse.k6接口.k2接口属性

interface User {

    //抽象属性，子类必须实现，没有支持字段(属性值)，没有getter,setter
    val email: String

    //普通属性，具有getter,setter,不可以有支持字段(属性值)，可以被子类继承
//    val subname:String = "hello" 错误，接口属性不可以有支持字段(属性值)
    val name: String
        get() = email.substringBefore("@")
}