package com.yin.yzjcourse.k1函数.k8内联函数inline

import org.junit.Test

/**
 * https://www.jianshu.com/p/8a0d5bae9cdf
 */
class MyInline {
    /**
     * 内联函数：用inline修饰的函数
     * 如果一个函数用inline修饰，那么在调用这个函数时，并不是普通意义上的函数调用，而是将这个内联函数的代码
     * 复制一份到调用处。
     *
     * 为什么要定义内联函数呢？
     * 因为lambda表达式会被编译成匿名类实例，如果这个lambda表达式引用了外部变量，那么多次访问这个lambda还
     * 会创建多个匿名类实例，这会影响性能，而如果以内联的方式则不会创建实例。提高性能
     *
     * 将一个lambda表达式传入一个内联函数或者内联函数中的某个lambda表达式，这个lambda不一定会被内联，具体
     * 有没有被内联，要看这个lambda有没有被立即使用，如果被使用了肯定被内联了，如果没有被使用而只是在这个内联
     * 函数中传递一道，又给其它函数或对象使用了，则就不会被内联，具体遇到了你会感觉到的
     */

    //函数foo被声明成内联的，函数类型参数b被声明成禁止内联。
    inline fun foo(a: () -> Unit, noinline b: () -> Unit) {
        //...do something
    }

    /**
    Tools -> Kotlin -> Show Kotlin Bytecode -> Decompile查看
     发现：
     1：show()没有被inline修饰，则doSomething()是真正的调用show(),是方法栈的压栈进栈
     2：handle()被inline修饰，则doSomething()只是在调用处将handle()整个方法体的代码复制
        到调用处，本质上不是方法的调用，而是代码的拷贝，因此也不存在压栈进栈；
        且需要注意的是，这个代码拷贝的过程发生在编译阶段，而不是运行阶段；
     */
    @Test
    fun doSomething(){
        println("调用前")
        show()
        handle()
        println("调用后")
    }

    fun show(){
        println("显示一些东西")
    }

    inline fun handle(){
        println("处理一些东西")
    }


    /**
     * Tools -> Kotlin -> Show Kotlin Bytecode -> Decompile查看
     *
     * doTTT()里调用doUUU(),doUUU()的参数是一个lambda，
     * 如果doUUU没有用inline修饰，则一方面doUUU入方法栈，另一方面因为doUUU()的参数是一个lambda
     * 本质上是一个包含了invoke()方法的FunctionN接口，则同时这里需要创建一个Function0对象作为
     * doUUU()的参数传入；
     * 如多doUUU用inline修饰，一方面doUUU不用入栈，因为实际是并不存在方法的调用，而是将doUUU()的
     * 函数体的代码复制到调用处，另一方面不用创建FunctionN接口的示例对应，只是将invoke()对应的函数体
     * 代码复制到调用处而已
     */
    fun doTTT() {
        doUUU {
            println("ddd")
        }
    }

     fun doUUU(block: () -> Unit) {
        println("开始UUU")
        block.invoke()
        println("结束UUU")
    }




    @Test
    fun doMMM() {
        doNNN {
            println("nnn")
            //doNNN()没有用inline，修饰这里不能直接return，编译错误
            //return@doNNN 这样可以，但是只能从doNNN()结束，不能结束doMMM()
//            return 语法错误，报红线
        }
        doOOO {
            println("ooo")
            //doOOO()用inline修饰，说明这离调用doOOO只是invoke()代码复制到这里，不存在方法
            //的调用，可以直接通过return结束doMMM()，无需用@
            //并且因为inline的处理发生在编译阶段，所以实际编译出的代码，直接把println("结束OOO")和println("eee")
            //都去掉了，因为这里return了，在java里return之后的代码都不应该存在
            return
        }
        println("eee")
    }

    fun doNNN(block: () -> Unit) {
        println("开始NNN")
        block.invoke()
        println("结束NNN")
    }

    inline fun doOOO(block: () -> Unit) {
        println("开始OOO")
        block.invoke()
        println("结束OOO")
    }


    /**
     * 因为doPPP()是inline函数，
     * 如果不给其参数oper用'noinline'修饰
     * 则doQQQ(oper)会报错，因为内联函数的函数类型的参数不能传递给非内联函数，除非其函数类型的参数用
     * noinline修饰，即禁止这个函数类型的参数内联，这个也很好理解，因为如果这个函数类型的参数内联了
     * 就相当于仅仅只是这个函数类型的参数对应的FunctionN的invoke()方法的函数体(一段代码)是doPPP()的参数，
     * 把这个函数体(一段代码)传入doQQQ(oper)，显然是不能满足doQQQ(oper)的参数类型的，因此报错
     */
    @Test
    inline fun doPPP(noinline oper:()->Unit){
        ///要求oper不能被内联，因为oper被内联了实际上就不是函数类型了，而是一段代码，不能满足doQQQ(oper)的参数类型
        doQQQ(oper)
        //oper是否内联都可以，因为doRRR(oper)本身是内联的，它的参数oper也是内联的，因此可以传入对应
        // invoke()的函数体代码，说白了就是一段代码的复制转移
        doRRR(oper)
    }

    fun doQQQ(oper: () -> Unit){

    }
    inline fun doRRR(oper: () -> Unit){

    }


    /***
     * 如果一个函数没有参数，或者虽然有参数但是参数类型没有函数类型的参数，则无需用inline修饰，jvm已经对这类方法
     * 进行了内联处理优化，你用inline修饰，在性能上也微乎其微；
     *
     * 如果一个函数的参数有函数类型，则可以根据需要决定这个函数是否改inline
     *
     * noinline,参考doPPP()
     */

}