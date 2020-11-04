package com.yin.yzjcourse.k5类和对象.k1_5属性和访问器.k2委托属性.k1引入1_属性变化监听原始代码

import org.junit.Test
import java.beans.PropertyChangeListener

class TestUsage {
    @Test
    fun testProperty() {
        val p = Person("Jack", 34, 2000)
        p.addPropertyChangeListener(PropertyChangeListener { event ->
            println("属性${event.propertyName}从${event.oldValue}变到${event.newValue}")
        })

        //这里的it就是propertyChange(PropertyChangeEvent evt)里的evt对象
//        p.addPropertyChangeListener(PropertyChangeListener {
//            println("属性${it.propertyName}从${it.oldValue}变到${it.newValue}")
//        })

        p.age = 35
        p.salary = 4000
    }
}