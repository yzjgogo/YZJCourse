package com.yin.yzjcourse.k21泛型.k3泛型范围约束

class K1MyRange {
    /**
     * 指定类型实参必须是Number的子类
     * 既然有了限制，就可以调用最大限制的类里定义的方法，这里调用了Number的toDouble()方法。
     */
    fun <T : Number> oneHalf(value: T): Double {
        return value.toDouble()
    }

    /**
     * 约束了类型实参必须实现了Comparable接口
     */
    fun <T : Comparable<T>> max(a: T, b: T): T {
        return if (a > b) a else b
    }

    /**
     * 约束了类型实参必须即是CharSequence的子类也是Appendable的子类
     */
    fun <T> get(seq: T) where T : CharSequence, T : Appendable {

    }
}