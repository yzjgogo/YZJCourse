package com.yin.yzjcourse.k21泛型.k11_点变型

import org.junit.Test

/**
 * 协变:[com.yin.yzjcourse.k21泛型.k7协变_保留子类型化关系.MyXie]
 * 逆变:[com.yin.yzjcourse.k21泛型.k8_逆变_反转子类型化关系.MyFan]
 * 不变:[com.yin.yzjcourse.k21泛型.k10_不变型_泛型类不存在子类型化关系.MyNoChange]
 * 点变：只取不变型的协变部分，或只取不变型的逆变部分，目的，把互相不为子超类型的泛型类类型，转变为可以互相喂子超类型的类型。
 * 点变可以将不变型类型在代码的局部临时变为协变类型或逆变类型的投影。
 *
 * 例如：
 * MutableList<out T>：只取MutableList<T>的协变部分，in位置的方法等将会禁用；
 * MutableList<in T>：只取MutableList<T>的逆变部分，out位置的方法等将会禁用；
 *
 *
 */
class MyPoint {
    /**
     * 报错，因为copyDate()中的MutableList<T>是不变型的，而你传入的泛型类型实参不一样，一个使Int，一个使Number。
     * MutableList<Int>和MutableList<Number>不存在子类型化关系，互相不是各自的子类。因此类型检查错误，就抱错了。
     */
    fun <T> copyData(source: MutableList<T>, destination: MutableList<T>) {
        for (item in source) {
            destination.add(item)
        }
    }

    @Test
    fun test() {
        val intlist = mutableListOf(1, 2, 3)//source中传入MutableList<Int>,不可以，也应该是Any
        val numlist = mutableListOf<Number>()//destination中传入MutableList<Number>
//        copyData(intlist,numlist)//报错，因为copyDate()中只定义了一个泛型类型参数T，而你传入的泛型类型实参不一样，一个使Int，一个使Any
    }


    /**
     * 思路：把互相不为子超类型的泛型类类型，转变为可以互相喂子超类型的类型
     *
     * 第一个参数source中的T，设置成协变，则针对source(destination无关)，MutableList<T>从不变改成协变，则source传入
     * 的MutableList<X(out T)>必须是MutableList<T>的子类型 因为是协变所以X必须是T的子类型，注意是子类型。这样source的泛型就必须是destination的
     * 泛型的子类型。例如copyData2(MutableList<Int>,MutableList<Number>)
     * 类似于<R:T,T>copyData1(source: MutableList<R>, destination: MutableList<T>)
     */
    fun <T> copyData1(source: MutableList<out T>, destination: MutableList<T>) {
        for (item in source) {
            destination.add(item)
        }
    }

    @Test
    fun test1() {
        val intlist = mutableListOf(1, 2, 3)//source中传入MutableList<Int>
        val numlist = mutableListOf<Number>()//destination中传入MutableList<Number>
        copyData1(intlist, numlist)//可以
    }


    /**
     * 第二个参数destination中的T，设置成逆变，则针对destination(source无关)，MutableList<T>从不变型改成协变型，则destination传入
     * 的MutableList<X(in T)>必须是MutableList<T>的子类型，因为是逆变所以X必须是T的超类型，注意是超类型。这样source的泛型就必须是destination的
     * 泛型的子类型。例如copyData2(MutableList<Int>,MutableList<Number>)
     * 类似于<R:T,T>copyData1(source: MutableList<R>, destination: MutableList<T>)
     *
     * 其实copyData2和copyData1是等价的。
     *
     */
    fun <T> copyData2(source: MutableList<T>, destination: MutableList<in T>) {
        for (item in source) {
            destination.add(item)
        }
    }

    @Test
    fun test2() {
        val intlist = mutableListOf(1, 2, 3)//source中传入MutableList<Int>
        val numlist = mutableListOf<Number>()//destination中传入MutableList<Number>
        copyData2(intlist, numlist)//可以
    }
}