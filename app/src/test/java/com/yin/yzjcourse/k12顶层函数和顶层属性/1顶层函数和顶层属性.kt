package com.yin.yzjcourse.k12顶层函数和顶层属性

/**
 * 顶层函数和顶层属性不从属于任何一个类，也不定义再class里，而是定义在一个单独的.kt文件里。
 * 这个.kt文件类似java的XX.Utils类，常用于定义一些静态常量和静态方法。
 *
 * 如果包外文件要使用顶层函数或顶层属性，则需要import导入。
 *
 * java如何调用扩展函数：
 * java中会把kotlin的扩展函数定义文件，例如Common.kt变异为Commonkt.java类
 * 因此就像调用java的静态函数一样：Commonkt.joinToString()
 */





/**
 * 顶层属性，类似java的
 */
const val DEFAULT_VAL = -1//类似java的 public static final int DEFAULT_VAL  =-1
var common = 1//可变属性
val common2 = 2//不可变属性

/**
 * 顶层函数
 */
fun <T> joinToString(collection: Collection<T>,
                     separator: String = ",",
                     prefix: String = "(",
                     postfix: String = ")"): String {
    val result = StringBuilder(prefix)
    for ((index, element) in collection.withIndex()) {
        if (index > 0) result.append(separator)
        result.append(element)
    }
    result.append(postfix)
    return result.toString()
}