package com.yin.yzjcourse.mykt.MyAnko.A5Layouts.L2Separate

import org.jetbrains.anko.*
import org.jetbrains.anko.sdk27.coroutines.onClick

/**
 * 创建布局的类
 *
 * 类似xml的布局
 */
class MyActivityUI : AnkoComponent<ComponentActivity> {
    //用了with()函数
    override fun createView(ui: AnkoContext<ComponentActivity>) =
            with(ui) {
                verticalLayout {
                    val et = editText()
                    button("Say Hello") {
                        onClick { ctx.toast("hello：${et.text}") }
                    }
                }
            }

//也可以用apply()函数
//    override fun createView(ui: AnkoContext<ComponentActivity>) = ui.apply {
//        verticalLayout {
//            val name = editText()
//            button("Say Hello") {
//                onClick { ctx.toast("Hello, ${name.text}!") }
//            }
//        }
//    }.view
}