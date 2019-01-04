package com.yin.yzjcourse.k5类和对象.k1_5属性和访问器.k2委托属性.k1引入1_属性变化监听原始代码

class Person(val name: String, age: Int, salary: Int) : PropertyChangeAware() {
    var age: Int = age
        set(newValue) {
            val oldValue = field
            field = newValue
            //setter访问器才会改变属性，此时触发变化监听最合适，会引起对象添加的监听器执行
            changeSupport.firePropertyChange("age", oldValue, newValue)
        }

    var salary: Int = salary
        set(newValue) {
            val oldValue = field
            field = newValue
            changeSupport.firePropertyChange("salary", oldValue, newValue)
        }
}