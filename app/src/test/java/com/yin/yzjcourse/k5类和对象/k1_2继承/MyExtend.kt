package com.yin.yzjcourse.k5类和对象.k1_2继承

class MyExtend {

    /**
     * 继承
     * 类默认是final，open修饰才可被继承
     *
     */
    //子类继承父类需要初始化父类的构造方法
    open class User5(val name: String)
    class Student(val age: Int, name: String) : User5(name)


    //如果父类没有任何构造方法，系统也会默认分配一个不做任何事情的构造方法(),子类同样 需要初始化父类的空构造方法。
    open class Button
    class TextButton() : Button()
    class ImageButton(val img: String) : Button()


    //接口没有构造方法，也没有空构造方法，因此子类实现接口不需要用()初始化接口
    interface Clickable
    class MyButton : Clickable//不应该是 class MyButton:Clickable(),没有()
}