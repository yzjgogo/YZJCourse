package com.yin.yzjcourse.k21泛型

class MyFan {
    fun test(){
        //不用声明泛型类型形参，可以根据类型实参推导出String类型形参
        val list = listOf("A","B")

//        val list1 = listOf() 报错，没有指定类型
        val list1:List<String> = listOf()//可以
        val list2 = listOf<String>()//也可以
    }


    /**
     * 声明函数
     */
    fun <T> List<T>.getSub():List<T>{
        return this.subList(0,1)
    }

    fun <T> List<T>.myFilter(f:(T) -> Boolean):List<T>{
        val list = arrayListOf<T>()
        return list
    }

    /**
     * 声明属性
     */
//    fun <T> List<T>.mFirstElement:T
}