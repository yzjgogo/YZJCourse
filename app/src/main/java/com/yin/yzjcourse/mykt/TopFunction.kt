package com.yin.yzjcourse.mykt

import android.content.Context
import android.content.Intent

/**
 * 这是我使用泛型运行时的实化 类型参数技术封装的启动activity的方法，
 * 我写这个的时候还没有用过Anko,
 * 其实Anko也有类似的方法，所以这个方法暂时没有被调用，但是也是 可以 使用的。
 */
//inline fun <reified T : Context> Context.startActivity() {
//    startActivity(Intent(this, T::class.java))
//}