package com.yin.yzjcourse.k21泛型.k3泛型范围约束

class K2MakeNotNull {
    /**
     * kotlin中泛型默认情况下是可空的，
     * 例如：calss Mc<T> ,无需声明T?,T也是可空的,因为T等价于T:Any?
     * 要想让泛型不可以为空，则可以给一个非空的上届即可。例如:T:Any
     */
    class MC<T : Any>(a: T) {

    }
}