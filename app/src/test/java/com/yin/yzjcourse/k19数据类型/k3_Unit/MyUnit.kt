package com.yin.yzjcourse.k19数据类型.k3_Unit

import org.junit.Test

class MyUnit {
    /**
     * 接口ProcessListener的process()方法定义时是有返回值的，但是实现时传入了Unit，因此可以看起来没有返回值，其实
     * 还是有返回值Unit的
     *
     * 因此函数没有返回值和函数有返回值Unit是同一件事的不同说法。
     */
    class MyProcess : ProcessListener<Unit> {
        override fun process() {
//            return Unit  默认有这一句
        }
    }

    /**
     * Unit类型和其它String，Int，Any等类型没什么本质的区别，也可以作为参数、返回值、泛型等类型使用
     * Unit类型有唯一值值也是Unit;
     * 即使一个方法没有返回值，默认也隐式返回Unit：return Unit
     */
    @Test
    fun testmy() {
        var result: Unit = show()
        if (result is Unit) {
            println("我是Unit类型:$result")
        } else {
            println("我不是Unit类型")
        }
    }

    fun show() {
        println("没有返回值")
        //return Unit 默认有这一句
    }
}