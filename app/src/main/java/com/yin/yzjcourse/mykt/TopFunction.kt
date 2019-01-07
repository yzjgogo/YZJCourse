package com.yin.yzjcourse.mykt

import android.content.Context
import android.content.Intent

inline fun <reified T:Context> Context.startActivity(){
    startActivity(Intent(this,T::class.java))
}