package com.yin.yzjcourse.k16_Lambda.k7成员引用


data class Person(val name: String, val age: Int){
    fun getMyAge():Int{
        return age
    }
}