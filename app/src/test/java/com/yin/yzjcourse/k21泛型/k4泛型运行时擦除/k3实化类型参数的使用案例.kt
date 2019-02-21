package com.yin.yzjcourse.k21泛型.k4泛型运行时擦除

import android.app.Service
import java.util.*
class k3实化类型参数的使用案例 {

    /**
     * Service::class.java的语法展现了如何获取java.lang.Class对应的Kotlin类。
     * 这和Java中的Service.class是完全等同的
     */
    val serviceimpl1 = ServiceLoader.load(Service::class.java)

    /**
     * 实化类型参数的内联函数用法
     *
     */
    inline fun <reified T> loadService(): ServiceLoader<T>? {
        return ServiceLoader.load(T::class.java)
    }
    //代码更简介
    val serviceimpl2 = loadService<Service>()
}