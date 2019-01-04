package com.yin.yzjcourse.k5类和对象.k1_5属性和访问器.k2委托属性.k6_Map也是一个委托属性的委托类.k2_Map也是kotlin属性委托类

/**
 *kotlin标准库己经在标准Map和MutableMap接口上定义了getValue()和setValue()扩展函数，所以这里可以直接这样用
 * map实例作为属性的委托；
 */
class Person {
    private val _attributes = hashMapOf<String, String>()
    //向map中添加一些属性
    fun setAttribute(attrName: String, value: String) {
        _attributes[attrName] = value
    }

    val name: String by _attributes
}