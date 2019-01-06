package com.yin.yzjcourse.k21泛型.k9_多个泛型既有协变也有逆变

/**
 * 假如一个泛型类有多个泛型类型参数T、E等等，则这些泛型类型有些可以协变，有些可以逆变。
 * 最典型的例子就是FunctionN接口；
 * FunctionN接口参考[com.yin.yzjcourse.k1函数.k7高阶函数.k3java调用有函数类型参数的kotlin函数.MyJava]
 *
 * public interface Function1<in P1, out R> : Function<R>
 *     可见P1是逆变的,P2是协变得。
 */
class MyMulti {

}