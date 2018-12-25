package com.yin.yzjcourse

import org.junit.Test

class k4字符串和字符串模板 {


    /**
     * String
     */
    @Test
    fun testString() {
        //String pattern
        //1:$varName 表示变量的值
        var a = 1
        val s1 = "a is $a"
        println(s1)

        //2:${varName.fun()} 表示变量的方法返回值:
        val s2 = "${s1.replace("is", "was")}, and so on"
        println(s2)

        //3:直接原样输出$,用‘\’
        println("我是\$a")


        //multi line String
        val text = """
    多行字符串
    多行字符串
    """
        println(text)   // 输出有一些前置空格

        val text2 = """
    |多行字符串
    |菜鸟教程
    |多行字符串
    |Runoob
    """.trimMargin()
        println(text2)    // 前置空格删除了,默认 | 用作边界前缀，但你可以选择其他字符并作为参数传入，比如 trimMargin(">")。


        //String can be for loop
        var content = "hello"
        for (c in content) {
            println(c)
        }
        println(content[1])//we can visit String's element with '[index]'
    }
}