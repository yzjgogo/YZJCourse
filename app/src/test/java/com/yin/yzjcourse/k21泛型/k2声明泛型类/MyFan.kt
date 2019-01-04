package com.yin.yzjcourse.k21泛型.k2声明泛型类

class MyFan {


    interface List<T> {
        fun get(index: Int): T
    }

    //使用具体类型实参，继承泛型类
//    class StringList : List<String> {
//        override fun get(index: Int): String {
//            return ""
//        }
//    }

    //继承时不指定类型实参，这段代码每次，只是不完整，我注释了，可以看的。
//    class ArrayList<T>:List<T>{
//        override fun get(index: Int): T {
//            return null
//        }
//    }


    /**
     * 一个类可以把自己作为类型实参，典型例子就是Comparable接口
     */
    interface Comparable<T> {
        fun compareTo(other: T): Int
    }

    class String : Comparable<String> {
        override fun compareTo(other: String): Int {
            return 0
        }
    }
}