package com.yin.yzjcourse.k19数据类型.k2Any和Any问号

import org.junit.Test

class TestAny {
    /**
     * Any：是kotlin中所有非空类型的超类，对应java的Object。
     * Any?：是kotlin中所有可空类型的超类。
     */
    @Test
    fun tstan() {
        var a: Any = 42//自动装箱
    }
}