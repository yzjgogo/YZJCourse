package com.yin.yzjcourse.k1函数.k9函数返回return

/**
 * 总结：
 * 如果没有使用标签，return从最近的使用fun关键字修饰的函数返回(前提是使用到的函数的参数如果有lambda,则这个函数需要是inline的，例如这里的forEach就是inline的)。
 * 参考[com.yin.yzjcourse.k1函数.k8内联函数inline.MyInline]这里也涉及到return
 */
class MyReturn {
    data class Person(val name: String, val age: Int)

    fun findAlice1(list: List<Person>) {
        for (person in list) {
            if (person.name == "Alice") {
                return//全局返回，整个findAlice1()结束
            }
        }
        println("findAlice1最后")
    }

    fun findAlice2(list: List<Person>) {
        list.forEach {
            if (it.name == "Alice") {
                return//也是全局返回，整个findAlice2()结束，因为forEach是inline修饰的
            }
        }
        println("findAlice2最后")
    }

    /**
     * 标签处返回
     * 定义标签：标签名@
     * 引用标签: @标签名
     */
    fun findAlice3(list: List<Person>) {
        list.forEach mlable@{
            if (it.name == "Alice") {
                return@mlable//局部返回，返回到标签处，继续向下执行
            }
        }
        println("findAlice3最后")
    }

    /**
     * 函数名也可以作为标签名
     */
    fun findAlice4(list: List<Person>) {
        list.forEach {
            if (it.name == "Alice") {
                return@forEach//局部返回，返回到标签处，继续向下执行
            }
        }
        println("findAlice4最后")
    }


    /**
     * 匿名函数内部的return，从匿名函数返回
     */
    fun findAlice5(list:List<Person>){
        list.forEach(fun(person){
            if (person.name == "Alice") {
                return//从匿名函数返回，最近的fun关键字修饰的函数
            }
        })
    }
}