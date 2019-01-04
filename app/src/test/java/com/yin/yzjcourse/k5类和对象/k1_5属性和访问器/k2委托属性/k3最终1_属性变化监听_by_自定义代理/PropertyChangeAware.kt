package com.yin.yzjcourse.k5类和对象.k1_5属性和访问器.k2委托属性.k3最终1_属性变化监听_by_自定义代理

import java.beans.PropertyChangeListener
import java.beans.PropertyChangeSupport

/**
 * 要想使用PropertyChangeSupport来监听属性的变化，需要在类中定义PropertyChangeSupport的实例，又因为
 * 每个类都定义PropertyChangeSupport实例太麻烦，所以我们可以定义一个基类PropertyChangeAware，在该基
 * 类中定义PropertyChangeSupport的实例，其它类只需继承该基类即可
 */
open class PropertyChangeAware {
    protected val changeSupport = PropertyChangeSupport(this)

    //给一个对象添加属性监听，这个监听会监听该对象中所有触发了监听的属性
    //参考Person类中的firePropertyChange，用于触发监听
    fun addPropertyChangeListener(listener: PropertyChangeListener) {
        changeSupport.addPropertyChangeListener(listener)
    }

    fun romovePropertyChangeListener(listener: PropertyChangeListener) {
        changeSupport.removePropertyChangeListener(listener)
    }

}