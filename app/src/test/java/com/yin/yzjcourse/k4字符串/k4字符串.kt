package com.yin.yzjcourse.k4字符串

import org.junit.Test

class k4字符串 {


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


        /**
        分割字符串
        java的一个可恶的问题：Java中的Split方法不适用于一个点号。当代码写为12.345-6.A”.split（"·"）的时候，我们期
        待的结果是得到一个[]12,345-6,A]数组。但是Java的split方法竟然返回一个空数组！这是因为它将一个正则表达式作为参
        数，井根据表达式将字符串分割成多个字符串。这里点号（．）是表示任何字符的正则表达式。

        kotlin就不存在这种问题
        kotlin的split可以传入正则，也可以传入字符串；

         */
        //参数是正则
        println("12.345-6.A".split("\\.|-".toRegex()))//[12, 345, 6, A]
        //可以指定多个分隔符,可变长参数
        println("12.345-6.A".split(".", "-"))//[12, 345, 6, A]
        //截取
        val mstr = "abc,def,gh,i,jklm"
        println(mstr.substringBeforeLast(","))//最后换一个‘,’之前的子串
        println(mstr.substringAfterLast(","))//最后一个','之后的子串


        /**
         *  获取字符串的第一个字符和最后一个字符
         */
        val ym = "abcdefg"
        val ymc = ym.first()
        val yml = ym.last()
        println("第一个:$ymc \n 最后一个：$yml")


        /**
         * 将字符串转换为字符集合
         */
        val strl = "hello"
        println(strl.toList())
    }
}