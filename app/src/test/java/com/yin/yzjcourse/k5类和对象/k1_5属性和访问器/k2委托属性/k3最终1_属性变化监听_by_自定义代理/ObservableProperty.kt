package com.yin.yzjcourse.k5类和对象.k1_5属性和访问器.k2委托属性.k3最终1_属性变化监听_by_自定义代理

import java.beans.PropertyChangeSupport
import kotlin.reflect.KProperty

/**
 * 用来维护属性的信息，包括属性名，属性值，属性的读取与写入等
 *
 * 注意getValue()和setValue()要用'operator'修饰；
 * 通过‘by’引入ObservableProperty代理后，即可实现属性代码及其精简的属性代理
 */
class ObservableProperty(var propValue: Int, val changeSupport: PropertyChangeSupport) {

    operator fun getValue(p: Person, prop: KProperty<*>): Int = propValue

    operator fun setValue(p: Person, prop: KProperty<*>, newValue: Int) {
        val oldValue = propValue
        propValue = newValue
        changeSupport.firePropertyChange(prop.name, oldValue, newValue)
    }
}