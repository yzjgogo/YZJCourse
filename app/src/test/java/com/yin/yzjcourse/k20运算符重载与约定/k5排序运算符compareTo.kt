package com.yin.yzjcourse.k20运算符重载与约定

import org.junit.Test

/**
 *  compareTo()函数在接口Comparable中已经用‘operator’修饰，因此子类重写该方法时无需再次用‘operator’修饰，同样可以
 *  使用比较运算符；
 *  compareTo()对应了：>，<，>=，<=
 *
 *  compareValuesBy(a: T, b: T, vararg selectors: (T) -> Comparable<*>?): Int
 *  参数a和b是参与比较的两个对象，
 *  第三个参数是可变长参数，参数类型是函数类型，因此可以传入多个lambda表达式，比较时会按该可变长参数的顺序依次根据给定的
 *  条件比较，如果比较出结果了(1或-1)整个方法就返回;如果相等了(0),就接着取下一个参数比较，依次类推，如果到最后一个也没比较出结果(0)则直接相等。
 */
class k5排序运算符compareTo {
    class Person(val firstName: String, val lastName: String) : Comparable<Person> {
        override fun compareTo(other: Person): Int {
            return compareValuesBy(this, other, Person::lastName, Person::firstName)
        }
    }

    @Test
    fun testCompare() {
        val p1 = Person("abc", "def")
        val p2 = Person("acb", "dfe")
        //重写了compareTo方法就可以用比较运算符了
        println(p1 > p2)//等价于p1.compareTo(p2) > 0
        println(p1 < p2)//等价于p1.compareTo(p2) < 0
        println(p1 >= p2)
        println(p1 <= p2)

    }
}