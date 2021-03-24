package com.yin.yzjcourse.k1函数.k8内联函数inline

/**
 * https://www.jianshu.com/p/8a0d5bae9cdf
 */
class MyInline {
    /**
     * 内联函数：用inline修饰的函数
     * 如果一个函数用inline修饰，那么在调用这个函数时，并不是普通意义上的函数调用，而是将这个内联函数的代码
     * 复制一份到调用处。
     *
     * 为什么要定义内联函数呢？
     * 因为lambda表达式会被编译成匿名类实例，如果这个lambda表达式引用了外部变量，那么多次访问这个lambda还
     * 会创建多个匿名类实例，这会影响性能，而如果以内联的方式则不会创建实例。提高性能
     *
     * 将一个lambda表达式传入一个内联函数或者内联函数中的某个lambda表达式，这个lambda不一定会被内联，具体
     * 有没有被内联，要看这个lambda有没有被立即使用，如果被使用了肯定被内联了，如果没有被使用而只是在这个内联
     * 函数中传递一道，又给其它函数或对象使用了，则就不会被内联，具体遇到了你会感觉到的
     */

    //函数foo被声明成内联的，函数类型参数b被声明成禁止内联。
    inline fun foo(a: () -> Unit, noinline b: () -> Unit) {
        //...do something
    }
}