package com.yin.yzjcourse.k5类和对象.k1_5属性和访问器.k2委托属性.k2引入2_属性变化监听_手动写代理

import org.junit.Test
import java.beans.PropertyChangeListener

class TestUsage {
    @Test
    fun testProperty() {
        val p = Person("Jack", 34, 2000)
        p.addPropertyChangeListener(PropertyChangeListener { event ->
            println("属性${event.propertyName}从${event.oldValue}变到${event.newValue}")
        })
        p.age = 45//最终调用了代理的setValue(newvalue)
        p.salary = 4500
    }
}