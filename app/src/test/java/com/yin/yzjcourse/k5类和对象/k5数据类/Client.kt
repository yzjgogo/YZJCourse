package com.yin.yzjcourse.k5类和对象.k5数据类

/**
 * 给类加上data关键字后，这个类就自动生成了toString(),equals(),hashCode()，copy()等方法，这些方法会放到某后，程序员尽管调用接口
 * 从而解决了java针对这些方法的大量重复的样板代码；
 *
 * equals()和hashCode()法会将所有在主构造方法中声明的属性纳入考虑，注意没有在主构造方法中声明的属性将不会加入到相等
 * 性检查和哈希值计算中去。
 *
 * copy()可以复制一个对象(产生一个新的对象)，同时修改属性值，copy的参数是有默认值的，就是原对象的属性值，但是你可以输入 新的参数
 * 从而改变原属性值。
 */
data class Client(val name: String, val postalCode: Int) {

    //copy方法类似这样
//    fun copy(name: String = this.name,postalCode:Int = this.postalCode) = Client(name,postalCode)
}