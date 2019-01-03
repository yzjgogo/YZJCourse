package com.yin.yzjcourse.k5类和对象.k1_4私有构造器

import org.junit.Test

class MyPrivate {

    /**
     * 私有的主构造方法
     * 私有的构造方法无法被外界初始化
     */
    class User6 private constructor(val name: String) {
        fun fun6() {
            //do something
        }
    }


    /**
     * 私有的从构造方法
     */
    class Person{
        private constructor(name: String)
    }
}