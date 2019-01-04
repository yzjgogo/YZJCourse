package com.yin.yzjcourse.k1函数

class k6局部函数 {


    /**
    局部函数
    局部函数可访问外部函数的所有变量和成员
    局部变量可以使代码解构更好
     */
    class User(val id: Int, val name: String, val address: String)

    //方式1：正常用法，局部函数直接放到外部函数里
    fun outFun(user: User) {
        fun innerFun(value: String) {
            if (value.isEmpty()) {
                throw Exception("""姓名或地址不能为空:${user.id}""")
            }
        }
        innerFun(user.name)
        innerFun(user.address)

        //do other something
    }

    //方式2：将局部函数放到一个扩展函数里
    fun User.extraFun() {
        fun innerFun(value: String) {
            if (value.isEmpty()) {
                throw Exception("姓名或地址不能为空:$id")
            }
        }
        innerFun(this.name)//没有省略this
        innerFun(address)//可省略this
    }

    fun outFun2(user: User) {
        user.extraFun()//调用扩展函数，间接调用其局部函数innerFun()
        //do other something
    }
}