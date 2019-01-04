package com.yin.yzjcourse.k5类和对象.k1_5属性和访问器.k2委托属性.k3最终1_属性变化监听_by_自定义代理

/**
 * 这个代码比[com.yin.yzjcourse.k5类和对象.k1_5属性和访问器.k2委托属性.k2引入2_属性变化监听_手动写代理.Person]更为精简
 * 因为kotlin作出了‘by’约定，没显示的代码已经在底层自动完成。你只需用‘by’修饰即可，其它交给编译器，kotlin会自动把
 * 委托(代理)的对象存储在隐藏的属性中，类似[com.yin.yzjcourse.k5类和对象.k1_5属性和访问器.k2委托属性.k2引入2_属性变化监听_手动写代理.Person]
 * 中的_age,_salary,并在访问(getter)或修改(setter)时调用委托的getValue()和setValue()
 *
 * ObservableProperty的实例就是代理(委托)，内部实际维护了对应的属性，包括属性的读取和写入
 */
class Person(val name: String, age: Int, salary: Int) : PropertyChangeAware() {
    var age: Int by ObservableProperty(age, changeSupport)

    var salary: Int by ObservableProperty(salary, changeSupport)
}