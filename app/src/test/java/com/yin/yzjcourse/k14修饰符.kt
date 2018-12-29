package com.yin.yzjcourse

import com.yin.yzjcourse.k6接口.k1接口函数.ClickListener

class k14修饰符 {
    /**
     * final open abstract override
     * kotlin中类和方法默认都是final的，这和java相反。
     *
     * final:不能被重写；
     * open：可以被重写；
     * abstract:必须被重写；
     *
     */
    //MyClass open修饰，可以被继承
    open class MyClass() : ClickListener {

        //f1 默认是final的，子类不可重写
        fun f1() {

        }

        //f2 是open的，子类可以重写
        open fun f2() {

        }

        //override修饰的，是重写了父类的方法，因此子类默认也可重写，如果不希望子类重写override修饰的方法，则可再用final修饰
        final override fun click() {
        }
    }

    //抽象类不能被实例化，抽象成员默认都是open的，非抽象成员默认仍是final的
    abstract class MyAbs() {
        abstract fun abs()//open的

        //非抽象成员默认是final的，可用open修饰
        fun abs1() {

        }
    }

    //接口不能用final,open，abstract修饰
    interface MyInter {
        fun interf()//默认是abstract
    }


    /**
     * public：所有地方可见
     * internal：模块中可见，一个模块就是一组一起编译的Kotlin文件。这有可能是一个Intellij IDEA模块、一个Eclipse项目、
     *          一个Maven或Gradle项目或者一组使用调用Ant任务进行编译的文件。
     * protected：子类中可见，注意和java的区别，java是保内可见，kotlin之内是子类可见
     * private：类或文件中可见
     *
     * static:kotlin没有static关键字，参考伴生对象
     *
     * kotlin禁止从public函数引用去引用低可见的类型，即public不可引用internal,protected,private
     * 类的扩展函数不能访问类的private和protected成员。
     */
}