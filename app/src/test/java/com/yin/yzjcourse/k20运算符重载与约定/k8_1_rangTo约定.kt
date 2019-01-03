package com.yin.yzjcourse.k20运算符重载与约定

import org.junit.Test

/**
 * 创建一个区间的约定：对‘operator’修饰的rangeTo()函数可使用‘..’符号代替
 * 可用来创建一个数据类型的值的范围；参考:[com.yin.yzjcourse.k9区间_数列_in.k1区间_数列_in]
 *
 * 因为kotlin已经对Comparable接口增加了扩展函数rangeTo,所以如果自己的类要定义这个运算符，只需实现Comparable接口，无需再定义一个rangeTo方法。
 * operator fun <T:Comparable<T>> T.rangeTo(that: T): ClosedRange<T>
 * 可见rangeTo返回了一个ClosedRange<T>,这个在[k8_2_iterator约定]中会用到。
 */
class k8_1_rangTo约定 {
    data class Person(val age:Int,val name:String):Comparable<Person>{
        override fun compareTo(other: Person): Int {
            return compareValuesBy(this,other,Person::age)
        }
    }

    @Test
    fun testRangeTo(){
        val p1 = Person(10,"jack")
        val p2 = Person(15,"Mono")
        val range = p1..p2//等价于 p1.rangTo(p2) 返回类型是ClosedRange<Person>
        val p3 = Person(12,"Fuck")
        println(p3 in range)
    }
}