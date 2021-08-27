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



        println("*************%%%%%%%%%%%%%%%%%%%%")
        //成员引用，可以还可以引用顶层函数此时::前不需要类名
        val mysalute = ::salute
        mysalute()
        val myadd = ::add
        myadd(5)
        (::add)(5)//这样也可以
        println("*************%%%%%%%%%%%%%%%%%%%%")


        //成员引用，引用多个参数的函数,nextAction1等价于nextAction2
        val nextAction1 = MyMember::showit
        val nextAction2 = {m1:MyMember,p:Person,m:String -> m1.showit(p,m)}
        nextAction1(MyMember(),Person("fuck",20),"hh")
        nextAction2(MyMember(),Person("fuck",20),"hh")

        //又因为showit在MyMember内部 ，我们可以省略MyMember,nextAction3和nextAction4等价
        val nextAction3 = ::showit
        val nextAction4 = {p:Person,m:String -> showit(p,m)}
        nextAction3(Person("duck",40),"hh")
        nextAction4(Person("duck",40),"hh")
        println("############################################")


        //成员引用，引用构造方法,createPerson和createPerson2等价
        val createPerson = ::Person
        val createPerson2 = {name:String,age:Int -> Person(name,age)}
        val per = createPerson("Momo",40)
        val per2 = createPerson2("Momo",40)
        println(per)
        println(per2)
        println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@")


        //成员引用，引用扩展函数
        val ieat = Person::eat//{p:Person -> p.eat()}
        val myp = Person("Dodo",90)
        ieat(myp)//记得传参因为Person::eat等价于{p:Person -> p.eat()},可见实参列表有参数p


        //kotlin 1.1后，实例的成员引用,从ta和ta2等价可看出，不需传参
        val now = Person("Now",45)
        val ta = now::age
        val ta2 = {now.age}//可见不许传参
        println(ta())//不需传参,因为引用的具体实例的成员

        //tg和tg2等价，可见也不需传参
        val tg = now::getMyAge
        val tg2 = {now.getMyAge()}
        println(tg())//不需传参,因为引用的具体实例的成员
    }

    private fun showit(p: Person, m: String) {
        println("$p,$m")
    }

}