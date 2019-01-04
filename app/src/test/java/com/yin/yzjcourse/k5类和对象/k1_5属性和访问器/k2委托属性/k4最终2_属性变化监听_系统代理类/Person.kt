package com.yin.yzjcourse.k5类和对象.k1_5属性和访问器.k2委托属性.k4最终2_属性变化监听_系统代理类

import kotlin.properties.Delegates
import kotlin.reflect.KProperty

/**
 * 使用了Delegates.observable()后，连委托的类都不用写了，Delegates.observable()内部实现了委托类，切同样有getValue()和setValue()
 *
 * 因此我做一个总结：
 * 所谓属性代理，就是属性所在的类不直接维护属性，而是交给另一个类(kotlin系统有提供，可以不用自己定义)的实例来维护，
 * 这个实例也被当做该类的属性(‘by’ 后跟的就是这个实例)，该实例必须有getValue()和setValue()方法，且这两个
 * 方法必须用‘operator’修饰，因为属性的访问器getter实际就是调用的代理的getValue()，setter实际就是调用代理的setValue()
 *
 *
 * 代理类可以自己定义，参考:[com.yin.yzjcourse.k5类和对象.k1_5属性和访问器.k2委托属性.k3最终1_属性变化监听_by_自定义代理.ObservableProperty]
 * 但是kotlin也有提供现成的，例如Delegates.observable(),Map,MutableMap,参考[com.yin.yzjcourse.k5类和对象.k1_5属性和访问器.k2委托属性.k6_Map也是一个委托属性的委托类.k2_Map也是kotlin属性委托类.Person]
 *
 * 'by'右边的表达式不一定是新创建的实例，也可以是函数调用、另一个属性或任何其他表达式， 只要这个表达式的值，是能够被编
 * 译器用正确的参数类型来调用getValue()和setValue()的对象。与其他约定一样，getValue()和setValue()可以是对象自己声明
 * 的方法或扩展函数。
 *
 * ‘by’ 右边是属性代理类的实例，这个实例也可以当成当前类的属性定义
 *
 * 对KProperty<*>不懂不要紧，按套路来就行了。
 */
class Person(val name: String, age: Int, salary: Int) : PropertyChangeAware() {
    //observer是属性值变化后的会掉方法，方便你做变化后的逻辑处理
    private val observer = { prop: KProperty<*>, oldValue: Int, newValue: Int ->
        changeSupport.firePropertyChange(prop.name, oldValue, newValue)
        println("哈哈哈")
    }
    var age: Int by Delegates.observable(age, observer)

    var salary: Int by Delegates.observable(salary, observer)
}