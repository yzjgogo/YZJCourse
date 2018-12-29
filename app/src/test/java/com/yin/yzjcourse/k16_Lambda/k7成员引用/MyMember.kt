package com.yin.yzjcourse.k16_Lambda.k7成员引用

import org.junit.Test
import com.yin.yzjcourse.k16_Lambda.k4Lambda表达式.LamPattern
class MyMember {

    /**
     *成员引用(引用类的成员，类名::成员)：
     * 创建一个调用单个方法或者访问单个属性的函数值
     * 不仅可以引用属性，还可以引用函数，如果引用函数不能加()
     *
     * 成员引用本质上是一个Lambda，因为Person::age等价于{p:Person -> p.age}，
     * Person::getMyAge等价于{p:Person -> p.getMyAge()}
     * [LamPattern]
     *
     * kotlin 1.1之后还可以引用实例的成员,实例::成员
     *
     * 注意，如果理解不了成员引用，可以吧成员引用转换为Lambda就看懂了。
     */
    @Test
    fun test() {
        val people = listOf(Person("name",20), Person("Fuck",30))
        val fitper = Person("Ada",70)
        //getAge1等价于getAge2
        val getAge1 = {p:Person -> p.age}
        val getAge2 = Person::age
        //getAge3等价于getAge4
        val getAge3 = {p:Person -> p.getMyAge()}
        val getAge4 = Person::getMyAge//会自动调用getMyAge()
        //调用成员或函数的引用函数时，记得传参，因为::本质上也是一个Lambda表达式，你可以根据Lambda的实参列表看到是否需要传参
        getAge1(fitper)
        getAge2(fitper)
        getAge3(fitper)
        getAge4(fitper)

        val max1 = people.maxBy{it.age}
        val max2 = people.maxBy(Person::age)
        val max3 = people.maxBy(Person::getMyAge)//会自动调用getMyAge()
        println("$max1,$max2,$max3")




        //成员引用，可以还可以引用顶层函数此时::前不需要类名
        fun salute() = println("salute")
        run{::salute}


        //成员引用，引用多个参数的函数
        val nextAction = ::showit
        nextAction(Person("fuck",20),"hh")


        //成员引用，引用构造方法
        val createPerson = ::Person
        val per = createPerson("Momo",40)
        println(per)


        //成员引用，引用扩展函数
        val ieat = Person::eat
        val myp = Person("Dodo",90)
        ieat(myp)//记得传参因为Person::eat等价于{p:Person -> p.eat()},可见实参列表有参数p


        //kotlin 1.1后，实例的成员引用
        val now = Person("Now",45)
        val ta = now::age
        println(ta())//不需传参,因为引用的具体实例的成员
        val tg = now::getMyAge
        println(tg())//不需传参,因为引用的具体实例的成员
    }
    val action = {p:Person,m:String -> showit(p,m)}

    private fun showit(p: Person, m: String) {
        println("$p,$m")
    }

}