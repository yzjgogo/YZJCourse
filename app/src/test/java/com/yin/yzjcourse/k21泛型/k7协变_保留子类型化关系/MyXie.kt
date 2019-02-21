package com.yin.yzjcourse.k21泛型.k7协变_保留子类型化关系

/**
 * 具体什么是协变，参考:[com.yin.yzjcourse.k21泛型.k6类_类型_子类型.MyTyper]
 *
 *  不变型的：
 *  一个泛型类一一例如， MutableList 一一如果对于任意两种类型A 和B,MutableList<A>既不是MutableList<B >的子类型也不是
 *  它的超类型，它就被称为在该类型参数上是不变型的。Java 中所有的类都是不变型的（尽管那些类具体的使用可以标记成可变型的。
 *  协变的：
 *  如果A是B的子类型,那么List<A>就是List<B>的子类型,可见子类型化被保留了，这样的类或者接口被称为协变的。注意List<T>是只读的。
 *  子类型化被保留后，所有需要List<B>的变量或参数，都可以用List<A>代替。
 *  逆变的：
 *  如果A是B的子类型,那么List<A>就是List<B>的超类型。这样的类或者接口被称为逆变的。
 *
 * 这里讨论如何定义一个协变类；
 * 一个协变类肯定是一个泛型类；
 *
 * 要把泛型类声明成某个在类型参数T上协变的，则要用关键字out修饰对应的泛型类型参数T。
 * out修饰的要点：
 * 类中所有用到了T的位置，都必须是out位置，或者既不是out位置也不是in位置。也就是说out修饰的泛型T不能出现在in和out两种位置上
 *
 * 类型参数T上的关键宇out有两层含义：
 * 1：子类型化会被保留（ Producer<Cat>是Producer <Animal>的子类型）
 * 2：T:只能用在out位置，T被用到的所有位置都必须是非in位置
 *
 *out位置：
 * 所谓的out位置，即这个类只能生产类型T的值(类似只读)，不能消费类型T的值(类似可写可修改)。
 * 1：函数返回值用到的T；这种情况下，该函数生产类型为T 的值。
 * 2：主构造函数中val属性；
 *
 * in位置：
 * 所谓in位置，就是可以修改泛型T的值的位置，可以修改并不是说你必须修改再算in位置，只是你存在修改的可能性，
 * 例如函数内，系统无法知道你在函数里到底有没有修改，所以T用到了非private的函数里面，就断定是不安全的。就说是在in位置
 * 1：函数参数用到的T；。这样的函数消费类型为T 的值，因为可能在函数内对该值修改，类型不安全。
 *
 * 注意：
 * 私有方法的参数或私有属性，即不属于out位置，也不属于in位置。
 * var属性，out位置和in位置都用到了。
 * vararg属性，既不在in位置也不再out位置。
 */
class MyXie {

    open class Animal{
        fun feed(){
            println("喂动物吃东西")
        }
    }

    /**
     * 把Herd<T>声明成协变的，这样如果Cat是Animal的子类型，则Herd<Cat>也是Herd<Animal>的子类型。
     */
    class Herd<out T:Animal>{
        val size:Int
            get()=4
        operator fun get(i:Int):T{
            return Animal() as T
        }
    }
    fun feedAll(animals: Herd<Animal>) {
        for (i in 0 until animals.size) {
            animals[i].feed()
        }
    }

    class Cat:Animal () {
        fun cleanLitter() { println("执行cleanLitter") }
    }

    fun takeCareOfCats(cats: Herd<Cat>){
        for (i in 0 until cats.size) {
            cats[i].cleanLitter()
            feedAll(cats)//Herd<out T:Animal>如果不用out声明成协变的，则Herd<Cat>就不是Herd<Animal>的子类型，尽管Cat是Animal的子类型也不可以。
        }
    }



/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



    /**
     * 案例1：
     * t用var修饰，在in位置和out位置都用到了T;
     * t用val修饰，没有对T的值的消费(修改)操作，T在out位置
     * t用vararg修饰，T既不在in位置，也不再out位置。
     */

//    class Dog1<out T:Animal>(var t:T)
    class Dog2<out T:Animal>(val t:T)
    class Dog3<out T:Animal>(vararg t:T)

//    class Dog4<out T:Animal>{
//        var t:T = Animal() as T
//            get() = Animal() as T
//    }

    class Dog5<out T:Animal>{
        val t:T
            get() = Animal() as T
    }


    /**
     * 案例2：函数参数中的T处于in位置
     */
//    class Dog6<out T:Animal>{
//        fun test(t:T){
//
//        }
//    }


    /**
     * 案例3：函数返回值的T处于out位置
     */
    class Dog10<out T:Animal>{
        fun test():T{
            return Animal() as T
        }
    }
    class Dog11<out T:Animal>{
        fun test():List<T>{
            return listOf<T>()
        }
    }


    /**
     * 案例4：私有的属性或者私有方法的参数中用到T既不在in位置，也不再out位置
     */

    class Dog7<out T:Animal>(private var t:T)
    class Dog8<out T:Animal>{
        private var t:T = Animal() as T
    }
    class Dog9<out T:Animal>{
        private fun test(t:T){
        }
    }
}