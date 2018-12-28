package com.yin.yzjcourse.k5类和对象

import org.junit.Test

class k2枚举类 {
    /**
     * 没有定义属性的枚举类
     */
    enum class FRUIT {
        APPLE, ORANGE, BANANA, PEACH
    }


    /**
     * 带属性的枚举类
     * 构造器参数用于声明各个枚举常量的属性值；
     *
     * 每个枚举常量创建时需指定属性值，各个 枚举常量间用‘，’分割，如果要在枚举类中定义任何方法('rgb()'),需要在最后一个枚举常量后
     * 用‘;’结束
     *
     * 可以给枚举类定义方法，如果定义方法，则 最后一个枚举 常量后需用‘;’结尾。
     *
     */
    enum class Color(val r: Int, val g: Int, val b: Int) {
        RED(255, 0, 0), ORANGE(255, 165, 0), YELLOW(255, 255, 0),
        GREEN(0, 255, 0), BLUE(0, 0, 255), INDIGO(75, 0, 135),
        VIOLET(238, 130, 238);

        fun rgb() = (r * 256 + g) * 256 + b
    }
    @Test
    fun testEnum(){
        println("颜色：${Color.RED.rgb()}")
    }

}