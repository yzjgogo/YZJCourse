package com.yin.yzjcourse.k5类和对象.k1_5属性和访问器.k2委托属性.k4最终2_属性变化监听_系统代理类

import org.junit.Test
import java.beans.PropertyChangeListener

class TestUsage {
    @Test
    fun testProperty() {
        val p = Person("Jack", 34, 2000)
        p.addPropertyChangeListener(PropertyChangeListener { event ->
            println("属性${event.propertyName}从${event.oldValue}变到${event.newValue}")
        })
        p.age = 65//
        p.salary = 6500
    }
}