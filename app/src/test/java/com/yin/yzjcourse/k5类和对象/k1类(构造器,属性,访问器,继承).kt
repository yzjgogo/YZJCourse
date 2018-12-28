package com.yin.yzjcourse.k5类和对象

import org.junit.Test

class `k1类(构造器,属性,访问器,继承)` {

    /**
     *  主构造方法的实现原理：
     *  Uesr1,User2,User3的定义都是等价的；
     *  都有一个主构造器，都有init语句块，都有属性name
     *  构造方法的参数同时也是类的属性
     *  参考[testCon]
     */
    class User1(val name: String)


    class User2(_name: String) {
        val name = _name
    }

    class User3 constructor(_name: String) {
        val name: String

        init {
            println("User3 init")
            name = _name
        }
    }

    @Test
    fun testCon() {
        val user1 = User1("user1")
        val user2 = User2("user2")
        val user3 = User3("user3")
        println("${user1.name},${user2.name},${user3.name}")
    }


    /**
     * 主构造方法参数可以声明默认值,也可以使用命名参数传递
     * val user4 = User4()//name和isAdult都是默认值
     * val user4 = User4("Lucy")//isAdult用默认值
     * val user4 = User4(name = "Lili",isAdult = true)
     *
     * 这样在大多数情况就满足了，类似java的多构造方法的场景。
     */
    class User4(val name: String = "jack", val isAdult: Boolean = false)

    @Test
    fun testDef() {
        val user41 = User4()
        val user42 = User4("Lili")
        val user43 = User4(isAdult = true)
        println("${user41.name},${user41.isAdult}" +
                "\n${user42.name},${user42.isAdult}" +
                "\n${user43.name},${user43.isAdult}")
    }


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


    /**
     * 私有的构造方法
     * 私有的构造方法无法被外界初始化
     */
    class User6 private constructor(val name: String) {
        fun fun6() {
            //do something
        }
    }


    /**
     * 从构造方法(非默认 构造方法)
     *
     * 从构造器的参数不可以是val类型的；
     * 从构造器的参数默认不是类的属性，需要自己处理转化为类的属性，这和java构造器一样；
     * 使用从构造器构造对象时，init初始化块仍然执行；
     * 虽然init会执行，但init内部引用不到从构造器的参数；
     *
     */
    //错误，设置了主构造方法就不能有从构造方法
//    open class User7(add:String){
//        constructor(isAdult: Boolean)
//        constructor(isAdult: Boolean,age: Int)
//    }
    //设置了从构造方法就不能有主构造方法，即使是空的主构造方法也没有，即无法通过val user7 = User7()创建对象
    open class User7 {
        init {
//            println("User7 init${this.isAdult}") 虽然init会执行，但init内部引用不到从构造器的参数
            println("User7 init")
        }

        constructor(isAdult: Boolean)
        constructor(isAdult: Boolean, age: Int)
    }

    class Student2 : User7 {
        var add: String//属性

        init {
            println("Student2 init")
        }

        //可以像java一样用this调用自己的另一个构造器
        constructor(_add: String, isAdult: Boolean) : this(_add, isAdult, 0) {
            add = _add
        }

        //可以通过super调用父构造器
        constructor(_add: String, isAdult: Boolean, age: Int) : super(isAdult, age) {
            add = _add
        }
    }

    @Test
    fun test7() {
//        val user7 = User7()报错，不再有主构造器 ，因为有从构造器
        val user7 = User7(true)
        println("分割")
        val stu2 = Student2("home", false)
        println(stu2.add)
    }


    /**
     * 构造器的参数，同时也是类的属性，如果是var默认存在getter和setter，如果是val默认存在getter
     *
     * kotlin的声明默认都是public和final的，例如这里的Person类就是final的，默认不允许继承
     */
    class Person(val name: String, var age: Int, var isMarried: Boolean)

    @Test
    fun testClass() {
        var per = Person("Jack", 28, true)
        per.age = 30//默认调用setter
        println("年龄${per.age}")//默认调用getter
        per.isMarried = false//默认调用setter
        println("是否结婚了：${per.isMarried}")//默认调用getter
    }


    /**
     * 自定义 getter访问器
     */
    class Rectangle(val height: Int, val width: Int) {
        val isSquare: Boolean
            get() {
                return height == width
            }
    }

    @Test
    fun testVisit() {
        var rect = Rectangle(40, 20)
        println("是正方形吗：${rect.isSquare}")
    }


    /**
     * getter和setter访问器，访问支持字段，自定义getter,setter访问器
     *
     * field用于访问支持字段，setter中即可以读取，也可以写入；getter中可以读取
     * 此处的field就是address的支持字段，也就是其属性值
     */
    class Dog(val name: String) {
        var address: String = "unknow"
            set(value: String) {
                println("重写了setter，这条狗是:$name,原来的地址是:$field,要换成新的地址:$value")
                field = value
            }
            get() {
                println("重写了getter:$field")
                return field//必须返回field不可以返回address
            }
    }

    @Test
    fun testDog() {
        val dog = Dog("哈士奇")
        //自动调用setter
        dog.address = "新地址"
        //自动调用getter
        println(dog.address)
    }


    /**
     * getter,setter访问器的可见性
     */
    class WordLength {
        var totalLength: Int = 0
            private set //setter设置为私有，只有类内部可调用

        fun addWord(word: String) {
            totalLength += word.length//内部可调用私有的setter
        }
    }

    @Test
    fun testLen() {
        val wl = WordLength()
//        wl.totalLength = 10 报错，因为你已经将setter设置为私有的了
        wl.addWord("fuck")
        println(wl.totalLength)//getter不变，扔可外部访问
    }
}