package com.yin.yzjcourse.k18空处理.k2let函数

import org.junit.Test

/**
 * let函数
 * 只在变量非空的时候才会执行let函数；如果变量为空则什么也不发生。
 * 相当于if(email!=null){...}没有else语句的情况
 */
class MyLet {
    @Test
    fun tlet() {
        val email:String? = null
        email?.let { sendEmail(it) }//不会执行let的lambda表达式，因为email为空，其中it就是email

        val email2:String? = "yzj@139.com"
        email2?.let { sendEmail(it) }//会执行let的lambda表达式，因为email2不为空，其中it就是email2
    }
    //参数不可为空
    fun sendEmail(email:String){
        println(email)
    }
}