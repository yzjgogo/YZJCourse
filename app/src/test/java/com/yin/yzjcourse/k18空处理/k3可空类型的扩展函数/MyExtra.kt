package com.yin.yzjcourse.k18空处理.k3可空类型的扩展函数

import org.junit.Test

/**
 * 对可空类型添加扩展函数[com.yin.yzjcourse.k4字符串.k4字符串.testisNull]
 * 引用这样的扩展函数无需进行非空判断，因为该函数内部就进行了非空判断
 *
 * 更多扩展函数内容参考：[1扩展函数和扩展属性.kt]
 */
//对可空类型的字符串定义扩展
fun String?.isReallyNull(): Boolean {
    return this == null || this.isNullOrBlank() || this.isNullOrEmpty()
}

class MyExtra {
    @Test
    fun myte() {
        var str: String? = ""
        //虽然str可空，但无需对其进行安全调用，因为isReallyNull内部做了判断
        println(str.isReallyNull())
    }
}