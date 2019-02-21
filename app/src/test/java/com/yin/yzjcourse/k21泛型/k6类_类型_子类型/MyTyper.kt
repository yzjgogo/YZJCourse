package com.yin.yzjcourse.k21泛型.k6类_类型_子类型

/**
 * 变量的类型规定了变量的可能的值；
 *
 * 对于非泛型类，一个类至少可以对应两种类型，例如String类对应String类型和String?类型；
 *
 * 对于泛型类，一个类可以对应无数多类型，要得到一个泛型类的类型，则你需要用一个类型实参替换泛型T，
 *  例如List<T>类可以对应List<Int>,List<String?>,List<List<Double>>等等。
 *
 *  子类型和超类型：
 *  任何时候，如果你需要的是类型A的值，都可以用类型B的值代替，则成为类型B是类型A的子类型，反之，类型A是类型B的超类型。任何一个类型都是它自己的子类型。
 *  例如：Int是Number的子类型，Int也是Int的子类型，Int不是String的子类型。
 *  编译器每次给变量赋值，或者给函数传参时都会做类型检查，只允许传递子类型值，否则就会编译错误，参考:[checkType]
 *
 *  非空类型A是可空类型A?的子类型，反过来却不是。
 *
 *  不变型的：
 *  一个泛型类一一例如， MutableList 一一如果对于任意两种类型A 和B,MutableList<A>既不是MutableList<B >的子类型也不是
 *  它的超类型，它就被称为在该类型参数上是不变型的。Java 中所有的类都是不变型的（尽管那些类具体的使用可以标记成可变型的。
 *  协变的：
 *  如果A是B的子类型,那么List<A>就是List<B>的子类型。这样的类或者接口被称为协变的。
 *  逆变的：
 *  如果A是B的子类型,那么List<A>就是List<B>的超类型。这样的类或者接口被称为逆变的。
 */
class MyTyper {

    fun checkType(i:Int){
        val n:Number = i//类型检查通：Int是Number的子类型，可以赋值过

        fun f(s:String){}
//        f(i)//类型检查不通过：函数f()要求参数是String类型，但是i是Int，Int不是String的子类型，报错！
    }
}