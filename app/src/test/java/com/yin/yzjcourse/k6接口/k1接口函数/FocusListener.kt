package com.yin.yzjcourse.k6接口.k1接口函数

interface FocusListener {
    /**
     * kotlin的接口，可以有抽象方法、有具体实现的不同方法
     * 非抽象的子类必须实现接口的抽象方法，可以选择是否实现接口的普通方法
     */

    //抽象方法:非抽象子类必须实现
    fun focus()

    //具体方法：子类可以选择是否重写
    fun show(){
        println("我是FocusListener")
    }

    fun setFocus(b:Boolean){
        println("是否获取到焦点：$b")
    }
}