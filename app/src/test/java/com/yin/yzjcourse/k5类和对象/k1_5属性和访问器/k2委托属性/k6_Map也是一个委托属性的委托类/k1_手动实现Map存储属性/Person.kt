package com.yin.yzjcourse.k5类和对象.k1_5属性和访问器.k2委托属性.k6_Map也是一个委托属性的委托类.k1_手动实现Map存储属性

/**
 * 定义一个map属性，用于存储Person的所有属性，以key-value的方式存储，key是属性名，value是属性值。
 */
class Person {
    private val _attributes = hashMapOf<String, String>()
    //向map中添加一些属性
    fun setAttribute(attrName: String, value: String) {
        _attributes[attrName] = value
    }

    //重写getter访问器，从map取属性值
    val name: String
        get() = _attributes["name"]!!
}