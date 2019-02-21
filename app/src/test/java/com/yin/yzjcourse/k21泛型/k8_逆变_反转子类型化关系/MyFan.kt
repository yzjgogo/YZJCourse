package com.yin.yzjcourse.k21泛型.k8_逆变_反转子类型化关系

import org.junit.Test

/**
 * 逆变与协变正好相反，类似于照镜子，一切都是相反的。
 * 参考协变:[com.yin.yzjcourse.k21泛型.k7协变_保留子类型化关系.MyXie]
 *
 *  逆变的：
 *  如果A是B的子类型,那么List<A>就是List<B>的超类型。这样的类或者接口被称为逆变的。
 *
 *  对于逆变，泛型类型参数的子类型关系没有被保留，而是被反转了。
 *  如果一个泛型类在某个泛型T上是逆变的，说明这个泛型T只用在了in位置，不能用在out位置。
 *
 *  一般来说如果一个泛型类的泛型T用in修饰了，说明这个泛型T只用在了该类的非private的函数参数中，
 *  因为一个泛型类中，除了该位置，要不是out位置，要不既是out位置又是in位置，要不既不是out位置又不是in位置。
 *
 */
class MyFan {

    open class Animal {
        fun feed() {
            println("喂动物吃东西")
        }
    }


    /**
     * 案例1：
     * t用var修饰，在in位置和out位置都用到了T;
     * t用val修饰，没有对T的值的消费(修改)操作，T在out位置
     * t用vararg修饰，T既不在in位置也不再out位置
     */

//    class Dog1<in T:Animal>(var t:T)
//    class Dog2<in T: MyXie.Animal>(val t:T)
    class Dog3<in T : Animal>(vararg t: T)

//    class Dog4<in T:Animal>{
//        var t:T = Animal() as T
//            get() = Animal() as T
//    }

//    class Dog5<in T: Animal>{
//        val t:T
//            get() = Animal() as T
//    }


    /**
     * 案例2：函数参数中的T处于in位置
     */
    class Dog6<in T : Animal> {
        fun test(t: T) {

        }
    }


    /**
     * 案例3：函数返回值的T处于out位置
     */
//    class Dog10<in T: Animal>{
//        fun test():T{
//            return MyXie.Animal() as T
//        }
//    }
//    class Dog11<in T: Animal>{
//        fun test():List<T>{
//            return listOf<T>()
//        }
//    }


    /**
     * 案例4：私有的属性或者私有方法的参数中用到T既不在in位置，也不再out位置
     */

    class Dog7<in T : Animal>(private var t: T)

    class Dog8<in T : Animal> {
        private var t: T = Animal() as T
    }

    class Dog9<in T : Animal> {
        private fun test(t: T) {
        }
    }


    /**
     * 一个实际存在的逆变类。
     * 这个接口的泛型T用in修饰。
     * String是Any的子类型；
     * 但是Comparator<Any>是Comparator<String>的子类型。
     */
    interface Comparator<in T> {
        fun compare(el: T, e2: T): Int
    }

    @Test
    fun testComparator() {
        val anycompa = Comparator<Any> { e1, e2 ->
            e1.hashCode() - e2.hashCode()
        }

        val list = listOf("cc", "ee", "aa")
        println(list.sortedWith(anycompa))


    }
}