package com.yin.yzjcourse.k18空处理.k1空处理

import org.junit.Test

class Mynull {


    /**
     * null处理
     * kotlin will force you to handle nullable value.
     * https://www.cnblogs.com/figozhg/p/6686798.html
     *
     * kotlin how to avoid NullPointerException?
     * Kotlin let check NullPointerException when compile instead of runtime.
     *
     * kotlin变量声明方式：
     * val name = ""//name不可以被赋值null
     * val name:String? = ""//name可以被赋值null,注意问号要跟在类型后面，因此如果要使用问号，则类型不可以省略
     * 这种声明方式会在编译期间就进行检查；
     * 以上方式不仅适用于变量的声明，也适用于返回值等的声明，总之，牵扯到是否可为null都可以这样声明
     * ？的意思：表示这个变量可以存储两种类型，一种是你本来要赋予的类型，一种是null类型
     * 这意味着kotlin中所有类型的默认值都是非空的，但是在java中一般类型可以存储两种值：该类型的实例和null。例如String str，str可存储任意字符串也可存储null，
     * 因此null不是任何类型的实例，java中也是如此。
     */
    @Test
    fun testNull() {

        var name = "jack"//name不可以为空
//        name = null //报错，因为name没有用?声明，因此不可以赋null值

//        mynull(null)//错误，因为mynull的参数name，没有用?修饰所以不可以为null值，因此如果你传入null值就会报错

        //var x:Int? =10
        var x: Int? = null//if you want give a variable 'null' value, you must use '?' decorate this variable.otherwise this variable cann't be null.
//        var y  = x.toDouble()    if x is decorated with '?',you can't write like this,this will report  an error,so yous should judge is null;

        //if x can be null,you should handle null status.
        //handle way 1:
        if (x != null) {
            val y1 = x.toDouble()//except null status.
        }
        //handle way 2:
        val y2 = x?.toDouble()//if x not  null then execute x.toDouble();if x is null then y2 will be null too,and y2's type will be 'Double?'.
        //因为toString()是Any?的扩展函数，可空类型的扩展函数调用时永远不需要用?
        val y22 = y2.toString()//因为y2的类型是'Double?'，所以不会报错

        //if you don't want return a null value,you can use ':' define a default value;
        val y3 = x?.toDouble() ?: 0.0//if x is null will return 0.0
        //above line be equivalent to latter code.you will find that kotlin can give 'if' to a variable.
//        val y4 = if (x != null) {
//            x.toDouble()
//        } else {
//            0.0
//        }

        //through above all code,we can handle almost all the NullPointerException.

        //avoid null check.
        //if x use '?' decorated,means that x can be null,if you don't want check null status,you can use '!!' decorate x,but if x really is a null
        //value this will throws NullPointerException,just like java's usual status.so don't try to use '!!'.
        var y5 = x!!.toDouble()
    }

    fun mynull(name: String) {
        println("$name")
    }
}