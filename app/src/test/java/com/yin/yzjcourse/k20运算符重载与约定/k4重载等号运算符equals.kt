package com.yin.yzjcourse.k20运算符重载与约定

/**
 * kotlin的基类Any中的equals()方法已经用'operator'修饰，切已经重载了'=='和'!='运算符
 * 子类重写equals方法不需再次声明'operator'同样有效；
 * 在kotlin中默认使用'=='和'!='都会调用equals()方法
 * 对于'!='编译器会自动对'=='操作取反，所以无需重新定义函数;
 *
 * kotlin中的'==='等价于java中的'==',对值的比较，如果是基本数据类型就比较值，如果是对象的引用就比较对象地址；
 */
class k4重载等号运算符equals {

}