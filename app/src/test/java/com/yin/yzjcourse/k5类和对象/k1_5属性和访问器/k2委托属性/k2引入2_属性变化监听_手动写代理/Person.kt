package com.yin.yzjcourse.k5类和对象.k1_5属性和访问器.k2委托属性.k2引入2_属性变化监听_手动写代理

/**
 * 代理初见雏形
 * 这里的_age和_salary就是代理
 *
 * 当我们通过getter获取age属性值时，其实是通过代理_age内部的getValue()方法获取代理内部维护的age属性值
 * 当我们通过setter设置属性值时，其实是通过代理_age内部的setValue(newvalue)方法给代理内部维护的属性设置新值；
 * 因此我们读取或修改的属性值的逻辑其实都是在代理内部维护的。
 */
class Person(val name: String, age: Int, salary: Int) : PropertyChangeAware() {
    val _age = ObservableProperty("age", age, changeSupport)
    var age: Int
        get() = _age.getValue()//getter委托给了代理的getValue()
        set(value) {
            _age.setValue(value)//setter委托给了代理的setValue()
        }

    val _salary = ObservableProperty("salary", salary, changeSupport)
    var salary: Int
        get() = _salary.getValue()
        set(value) {
            _salary.setValue(value)
        }
}