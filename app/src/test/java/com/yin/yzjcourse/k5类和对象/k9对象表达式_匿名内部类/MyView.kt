package com.yin.yzjcourse.k5类和对象.k9对象表达式_匿名内部类

class MyView {
    var listener:ClickListener? = null
    fun addListener(clickListener: ClickListener){
        listener = clickListener
    }
}