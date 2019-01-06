package com.yin.yzjcourse.k21泛型.k10_不变型_泛型类不存在子类型化关系

/**
 * 类比协变[com.yin.yzjcourse.k21泛型.k7协变_保留子类型化关系.MyXie]和逆变[com.yin.yzjcourse.k21泛型.k8_逆变_反转子类型化关系.MyFan]
 * 所谓不变型：
 * 泛型类的类型不存在子类型化关系，也就是说泛型类型实参的子类型化，不影响泛型类的类型
 *
 * MutableList <Any>既不是MutableList<String>的子类型也不是它的超类型，虽然Any和String存在子类型化关系。
 */
class MyNoChange {
}