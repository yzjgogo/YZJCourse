package com.yin.yzjcourse.k20运算符重载与约定

import org.junit.Test

class k8_2_iterator约定 {
    data class Person(var age:Int,var name:String):Comparable<Person>{
        override fun compareTo(other: Person): Int {
            return compareValuesBy(this,other,Person::age)
        }
    }

    //给这个区间添加一个'operator'修饰的iterator()函数，使这个区间可用于for循环
    operator fun ClosedRange<Person>.iterator(): Iterator<Person> =
            object :Iterator<Person>{
                var current = start
                override fun hasNext() =
                        current <= endInclusive

                override fun next() = current.apply { current.age+=1 }

            }

    @Test
    fun testRangeTo(){
        val p1 = Person(10,"jack")
        val p2 = Person(15,"Mono")
        val range = p1..p2//等价于 p1.rangTo(p2)
        val p3 = Person(12,"Fuck")
        println(p3 in range)
        //因为iterator()所以可以for循环了
        for (p in range){
            print(p)
        }
    }
}