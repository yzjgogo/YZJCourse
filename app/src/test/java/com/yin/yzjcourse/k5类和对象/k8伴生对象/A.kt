package com.yin.yzjcourse.k5类和对象.k8伴生对象

/**
 *kotlin不能拥有静态成员，没有static关键字
 *
 *伴生对象定义在类A内部，因为可以直接通过类A访问伴生对象的方法和属性，就好像java中类A的静态成员，所以不需要
 * 指定对象的名字，如果你不指定对象的名称，默认有一个名称叫“Companion”,也可以给伴生对象指定名字，但是A调用伴生
 * 对象成员时同样可以省略；
 *
 * 伴生对象可以访问类A的所有私有成员，包括私有构造方法;
 *
 * 一个类最多只能有一个伴生对象;
 *
 * 伴生对象和普通的类定义一样，可以有名字、函数、扩展函数、属性、继承、实现等；
 *
 * 伴生对象就是解决kotlin没有static关键字的问题，伴生对象里的属性和方法就是外部A的static的属性和方法
 * @see TestA
 */
class A private constructor(val name: String) {
    //    companion object Havename{ 可以给伴生对象起一个名字
//    companion object Companion: AListener { 默认有一个名称叫“Companion”，可以忽略
    companion object : AListener {
        var desc:String = "描述"
        //伴生对象可以实现接口,类名A就是AListener接口的实例，参考TestA
        override fun click() {
            println("接口函数click")
        }

        //A的静态方法
        fun show() {
            println("伴生对象的方法")
        }

        //伴生对象可访问A的私有成员，工厂方法
        fun getA(newname: String) = A(newname)
    }
}

/**
 * 伴生对象可以定义扩展函数，就相当于给A添加新的java中的静态函数
 * @see TestA
 */
fun A.Companion.expfun(){
    println("A的伴生对象的扩展函数")
}