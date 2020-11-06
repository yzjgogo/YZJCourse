package com.yin.yzjcourse.k13扩展函数和扩展属性

/**
 * 有点类似顶层函数和顶层属性，区别是扩展函数和扩展属性要绑定到现有的某个类上
 *
 * 给已存在的类添加新的属性和方法，这些新增的方法不能访问原来类的私有或者是受保护的成员
 *
 * 包外的文件如果想使用该扩展 ，则需要import导入，导入时也可以修改方法名，避免出现重复的方法名：
 * import strings.lastChar as last
 * 则last就代替了lastChar
 *
 * 扩展函数类似java的静态函数
 *
 * 扩展函数尽量不要重写原始类的成员函数，即使你重写了，也是调用原始类的成员函数，这和子类重写父类的函数完全相反。
 */

//给String类添加扩展函数lastChar()
//其中String叫接收者类型，this叫接收者对象，例如："kotlin".lastChar()中的kotlin就是当前的this。
//java如果调用kotlin的扩展函数：Commonkt.lastChar("kotlin")，接收者对象将会作为函数的第一个参数传入，注意是第一个参数
//例如：String.lastChar(a)则java中调用为，Commonkt.lastChar("kotlin",a),多了一个参数接收者对象
fun String.lastChar():Char = this.get(this.length-1)
fun String.lastButOne():Char = this[this.length-2]

fun String.firstChar():Char = get(0) //也可以省略接受者对象

fun String.secondChar():Char{
        return this.get(1)
//        return this[1] 这样也可以
}


//给集合定义一个扩展
fun <T> Collection<T>.joinToString(
                     separator: String = ",",
                     prefix: String = "(",
                     postfix: String = ")"): String {
        val result = StringBuilder(prefix)
        for ((index, element) in this.withIndex()) {
                if (index > 0) result.append(separator)
                result.append(element)
        }
        result.append(postfix)
        return result.toString()
}


/**
 * 可空类型的扩展函数[com.yin.yzjcourse.k18空处理.k3可空类型的扩展函数.MyExtraKt.isReallyNull]
 */



/**
 扩展属性

 val类型，要自定义getter访问器

 var类型，要自定义getter和setter访问器
 */
val String.lastChar:Char
        get() = get(length-1)

var StringBuilder.lastChar:Char
        get() = get(length-1)
        set(value:Char) {
                this.setCharAt(length-1,value)
        }


