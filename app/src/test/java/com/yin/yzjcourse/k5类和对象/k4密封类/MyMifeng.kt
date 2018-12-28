package com.yin.yzjcourse.k5类和对象.k4密封类

/**
 * 密封类的所有的子类必须是嵌套的，即只能定义在密封类的内部；
 * 不能创建密封类的实例，因为密封类默认只有私有构造器;
 * kotlin1.0之前密封类的子类不允许是data类，后来允许了；
 * kotlin1.0之前密封类的子类必须是嵌套类，后来放到同一文件的任意位置都可以；
 */
sealed class MyMifeng {
    class My1(val name: String) : MyMifeng()
    class My2 : MyMifeng()
    //kotlin1.0之前密封类的子类不允许是data类，后来允许了
    data class My3(val age: Int) : MyMifeng() {
        fun show() {
            println("my3")
        }
    }
}

//kotlin1.0之前密封类的子类必须是嵌套类，后来放到同一文件的任意位置都可以
//这里的My4就不是密封类的嵌套类
class My4() : MyMifeng() {
    fun my4f() {
        println("我是my4")
    }
}