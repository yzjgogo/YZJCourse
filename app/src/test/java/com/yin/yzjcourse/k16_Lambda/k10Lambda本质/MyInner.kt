package com.yin.yzjcourse.k16_Lambda.k10Lambda本质

class MyInner {
    /**
     * 非内联的Lambda在编译的时候会被编译成匿名类，
     * 但是如果要在Lambda中使用this关键字，this并不是指向这个匿名类实例，你可以把Lambda理解成普通的代码块，因此this指向包围lambda的最近的类的实例；
     */
}