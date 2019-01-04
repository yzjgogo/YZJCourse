package com.yin.yzjcourse.k5类和对象.k1_5属性和访问器.k2委托属性.k2引入2_属性变化监听_手动写代理

import java.beans.PropertyChangeSupport

/**
 * 用来维护属性的信息，包括属性名，属性值，属性的读取与写入等
 */
class ObservableProperty(val propName: String, var propValue: Int, val changeSupport: PropertyChangeSupport) {
    //属性的getter最终是调用的getValue()
    fun getValue(): Int = propValue

    //属性的setter最终调用的是setValue()
    fun setValue(newValue: Int) {
        val oldValue = propValue
        propValue = newValue
        changeSupport.firePropertyChange(propName, oldValue, newValue)
    }
}