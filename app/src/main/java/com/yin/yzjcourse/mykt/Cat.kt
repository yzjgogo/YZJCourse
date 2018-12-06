package com.yin.yzjcourse.mykt

import java.io.Serializable

class Cat : Serializable {
    var age: Int = 0
    var name: String? = null

    override fun toString(): String {
        return "Dog{" +
                "age=" + age +
                ", name='" + name + '\''.toString() +
                '}'.toString()
    }
}
