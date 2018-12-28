package com.yin.yzjcourse.k6接口.k1接口函数

/**
 * 和java一样，kotlin可以继承一个类和实现多个接口
 */
class Button : ClickListener, FocusListener {
    override fun click() {
        println("button click")
    }

    override fun focus() {
        println("button focus")
    }

    /**
     * 因为多个父接口都有show() 所有你必须重写它
     * 也可以选择调用具体某个父类的show()
     */
    override fun show() {
        super<ClickListener>.show()
        super<FocusListener>.show()
    }
}