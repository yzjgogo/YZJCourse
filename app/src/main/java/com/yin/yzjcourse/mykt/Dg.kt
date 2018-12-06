package com.yin.yzjcourse.mykt

import java.io.Serializable

class Dg : Serializable {
    var age: Int = 0
    var name: String? = null
    var type: String? = null

    override fun toString(): String {
        return "Dog{" +
                "age=" + age +
                ", name='" + name + '\''.toString() +
                ", type='" + type + '\''.toString() +
                '}'.toString()
    }
}
