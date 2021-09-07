package com.yin.yzjcourse.k22协程

import kotlinx.coroutines.*
import org.junit.Test
import java.io.File
import kotlin.coroutines.CoroutineContext

class `4协程上下文与调度器` {
    /**
    单例GlobalScope是CoroutineScope的子类，CoroutineScope有CoroutineContext属性
    object GlobalScope:interface CoroutineScope(CoroutineContext)



    interface Job : CoroutineContext.Element : CoroutineContext



    object EmptyCoroutineContext : CoroutineContext

    Unconfined: CoroutineDispatcher: Element : CoroutineContext

    Default: CoroutineDispatcher: Element : CoroutineContext

    IO: CoroutineDispatcher: Element : CoroutineContext

    Main: CoroutineDispatcher: Element : CoroutineContext

    协程总是运行在一些以 CoroutineContext 类型为代表的上下文中

    创建协程时，可以传入一个协程调度器(CoroutineDispatcher:IO,Default,Unconfined,Main等)，它确定了相关的协程在哪个线程或哪些线程上执行。
    协程调度器可以将协程限制在一个特定的线程执行，或将它分派到一个线程池，亦或是让它不受限地运行。

    所有的协程构建器诸如 launch 和 async 接收一个可选的 CoroutineContext 参数，它可以被用来显式的为一个新协程或其它上下文元素指定一个调度器。
     */
    @Test
    fun test1() {
        runBlocking<Unit> {
            //当调用 launch { …… } 时不传参数，它从启动了它的 CoroutineScope 中承袭了上下文（以及调度器）。在这个案例中，它从 main 线程中的 runBlocking 主协程承袭了上下文。
            //以上说法在coroutineScope和runBlocking里调用launch{}是对的，但是在GlobalScope.launch{launch{}}里面再调用launch{}就是不对的，会开辟新的线程
            launch { // 运行在父协程的上下文中，即 runBlocking 主协程
                println("main runBlocking      : I'm working in thread：${Thread.currentThread().id}, ${Thread.currentThread().name}")
            }

            //Dispatchers.Unconfined 是一个特殊的调度器且似乎也运行在 main 线程中，但实际上， 它是一种不同的机制，这会在后文中讲到。
            launch(Dispatchers.Unconfined) { // 不受限的——将工作在主线程中
                println("Unconfined            : I'm working in thread：${Thread.currentThread().id}, ${Thread.currentThread().name}")
            }

            //当协程在 GlobalScope 中启动时，使用的是由 Dispatchers.Default 代表的默认调度器。 默认调度器使用共享的后台线程池。 所以 launch(Dispatchers.Default) { …… } 与 GlobalScope.launch { …… } 使用相同的调度器。
            launch(Dispatchers.Default) { // 将会获取默认调度器
                println("Default               : I'm working in thread：${Thread.currentThread().id}, ${Thread.currentThread().name}")
            }

            //newSingleThreadContext 为协程的运行启动了一个线程。 一个专用的线程是一种非常昂贵的资源。 在真实的应用程序中两者都必须被释放，当不再需要的时候，使用 close 函数，或存储在一个顶层变量中使它在整个应用程序中被重用。
            launch(newSingleThreadContext("MyOwnThread")) { // 将使它获得一个新的线程
                println("newSingleThreadContext: I'm working in thread：${Thread.currentThread().id}, ${Thread.currentThread().name}")
            }

            //开辟一个新的线程
            launch(Dispatchers.IO) {
                println("IO                    : I'm working in thread：${Thread.currentThread().id}, ${Thread.currentThread().name}")
            }

            //好像在@Test里会报错,在项目里不会
//            launch(Dispatchers.Main) {
//                println("Main                  : I'm working in thread：${Thread.currentThread().id}, ${Thread.currentThread().name}")
//            }
        }
    }

    /**
    非受限调度器 vs 受限调度器1

    在调用suspend函数(doData1)之前的线程是父协程所在的线程；
    调用suspend挂起执行完成后，恢复时，并不是恢复到调用suspend(daData1)之前的线程，而是这个suspend函数的执行线程；

    课件Dispatchers.Unconfined开辟的协程，suspend函数挂起前和挂起后分别可能是两个不同的线程
     */
    @Test
    fun test2() {
        runBlocking<Unit> {
            //使用了Dispatchers.Unconfined的协程的上下文继承自外面的协程，这里是runBlocking
            launch(Dispatchers.Unconfined) {
                println("第1： ${Thread.currentThread().name},${System.identityHashCode(coroutineContext)}")//第1： main @coroutine#2,2030562336
                //doData1()之前使用的是外部协程的所在的线程
                //doData1()之后使用的是doData1()这个suspend函数所在的执行线程
                doData1()//里面1：DefaultDispatcher-worker-1 @coroutine#2
                println("第2： ${Thread.currentThread().name},${System.identityHashCode(coroutineContext)}")//第2： DefaultDispatcher-worker-1 @coroutine#2,2030562336
            }
        }
    }

    /**
    非受限调度器 vs 受限调度器2
    再次验证
     */
    @Test
    fun test3() {
        runBlocking<Unit> {
            //使用了Dispatchers.Unconfined的协程的上下文继承自外面的协程，这里是runBlocking
            launch(Dispatchers.Unconfined) {
                println("第1： ${Thread.currentThread().name},${System.identityHashCode(coroutineContext)}")//第1： main @coroutine#2,2030562336
                //doData2()之前使用的是外部协程的所在的线程
                //doData2()之后使用的是doData1()这个suspend函数所在的执行线程
                doData2()//里面2：MyOwnThread @coroutine#2
                println("第2： ${Thread.currentThread().name},${System.identityHashCode(coroutineContext)}")//第2： MyOwnThread @coroutine#2,2030562336
            }
        }
    }

    /**
    非受限调度器 vs 受限调度器3
    再次验证
    这个时候因为传入的上下文就是Dispatchers.Unconfined开辟的协程的父协程(runBlocking)的上下文，所以suspend函数doData3的执行线程就是父协程(runBlocking)的所在的线程
    因此doData3调用前后，是同一个线程
     */
    @Test
    fun test4() {
        runBlocking<Unit> {
            //使用了Dispatchers.Unconfined的协程的上下文继承自外面的协程，这里是runBlocking
            launch(Dispatchers.Unconfined) {
                println("第1： ${Thread.currentThread().name},${System.identityHashCode(coroutineContext)}")//第1： main @coroutine#2,2030562336
                //doData3()之前使用的是外部协程的所在的线程
                //doData3()之后使用的是doData1()这个suspend函数所在的执行线程
                doData3(coroutineContext)//里面2：MyOwnThread @coroutine#2
                println("第2： ${Thread.currentThread().name},${System.identityHashCode(coroutineContext)}")//第2： MyOwnThread @coroutine#2,2030562336
            }
        }
    }

    suspend fun doData1() {
        withContext(Dispatchers.IO) {
            println("里面1：${Thread.currentThread().name}")
        }
    }

    suspend fun doData2() {
        withContext(newSingleThreadContext("MyOwnThread")) {
            println("里面2：${Thread.currentThread().name}")
        }
    }

    suspend fun doData3(context: CoroutineContext) {
        withContext(context) {
            println("里面3：${Thread.currentThread().name}")
        }
    }

    /**
    调试协程与线程

    1：用 IDEA 调试
    2：用日志调试

    https://www.kotlincn.net/docs/reference/coroutines/coroutine-context-and-dispatchers.html

    后面补充学习
     */
    @Test
    fun test5() {

    }

    /**

    在不同线程间跳转：

    Started in ctx1:Ctx1 @coroutine#1
    Working in ctx2:Ctx2 @coroutine#1
    Back to ctx1:Ctx1 @coroutine#1

    newSingleThreadContext("str")是CoroutineDispatcher类型也是CoroutineContext类型

    T.use(block: (T) -> R): R
    是任何类型的扩展函数，其参数是函数类型的参数，该函数类型是一个参数一个返回值，这个参数就是调用use的对象
    实现了Closeable接口的对象可调用use函数
    use函数会自动关闭调用者（无论中间是否出现异常）
    Kotlin的File对象和IO流操作变得行云流水
    use函数内部实现也是通过try-catch-finally块捕捉的方式，所以不用担心会有异常抛出导致程序退出
    close操作在finally里面执行，所以无论是正常结束还是出现异常，都能正确关闭调用者
     */
    @ObsoleteCoroutinesApi
    @Test
    fun test6() {
        newSingleThreadContext("Ctx1").use { ctx1 ->
            newSingleThreadContext("Ctx2").use { ctx2 ->
                runBlocking(ctx1) {//指定协程运行在Ctx1线程中
                    println("Started in ctx1:${Thread.currentThread().name}")
                    withContext(ctx2) {//挂起后在Ctx2中运行
                        println("Working in ctx2:${Thread.currentThread().name}")
                    }
                    //又会恢复到Ctx1中
                    println("Back to ctx1:${Thread.currentThread().name}")
                }
            }
        }

        File("/home/test.txt").readLines()
                .forEach { println(it) }
    }

    /**

    My job is1 "coroutine#1":BlockingCoroutine{Active}@7cdbc5d3,main @coroutine#1
    My job is3 "coroutine#3":StandaloneCoroutine{Active}@6a1947fb,DefaultDispatcher-worker-1 @coroutine#3
    My job is2 "coroutine#2":StandaloneCoroutine{Active}@340f438e,main @coroutine#2

    协程的 Job 是上下文的一部分，并且可以使用 coroutineContext[Job]表达式在上下文中检索它,并输出协程的信息

    请注意，CoroutineScope 中的 isActive 只是 coroutineContext[Job]?.isActive == true 的一种方便的快捷方式。参考：com.yin.yzjcourse.k22协程.2取消与超时.test10
     */
    @Test
    fun test7() {
        runBlocking<Unit> {
            //[Job] 运算符重载 operator fun <E : Element> get(key: Key<E>): E?
            println("My job is1 ${coroutineContext[Job]},${Thread.currentThread().name}")
            launch {
                println("My job is2 ${coroutineContext[Job]},${Thread.currentThread().name}")
            }
            GlobalScope.launch {
                println("My job is3 ${coroutineContext[Job]},${Thread.currentThread().name}")
            }
        }
    }


    /**
    子协程

    当一个协程被其它协程在CoroutineScope中启动的时候， 它将通过 CoroutineScope.coroutineContext 来承袭上下文，并且这个新协程的 Job 将会成为父协程作业的子协程。
    当一个父协程被取消的时候，所有它的子协程也会被递归的取消。

    然而，当使用 GlobalScope来启动一个协程时，则新协程与这个启动的作用域无关且独立运作。
     */
    @Test
    fun test8() {
        runBlocking<Unit> {
            // 启动一个协程来处理某种传入请求（request）
            val request = launch {
                // 孵化了两个子作业, 其中一个通过 GlobalScope 启动
                GlobalScope.launch {
                    println("job1: 我由GlobalScope启动，并且独立执行，并没有承袭父协程")
                    delay(1000)
                    println("job1: 父协程被取消并不会影响我")
                }
                // 另一个则承袭了父协程的上下文
                launch {
                    delay(100)
                    println("job2:我是request协程的子协程，我承袭了request")
                    delay(1000)
                    println("job2: 如果request协程被取消，我也会被递归取消")
                }
            }
            delay(500)
            request.cancel() // 取消请求（request）的执行
            delay(1000) // 延迟一秒钟来看看发生了什么
            println("main: Who has survived request cancellation?")
        }
    }


    /**

    Started main coroutine:Test worker @main#1
    Computing v1:Test worker @v1coroutine#2
    Computing v2:Test worker @v2coroutine#3
    The answer for v1 / v2 = 42

        给协程命名，方便调试，命名后，输出线程名称Thread.currentThread().name，时就会带有协程信息
        值得注意的是CoroutineName也是CoroutineContext类型，但是我传入了名称怎么也传入指定的线程呢，看test10
     */
    @Test
    fun test9() {
        runBlocking(CoroutineName("main")) {
            println("Started main coroutine:${Thread.currentThread().name}")
            // 运行两个后台值计算
            val v1 = async(CoroutineName("v1coroutine")) {
                delay(500)
                println("Computing v1:${Thread.currentThread().name}")
                252
            }
            val v2 = async(CoroutineName("v2coroutine")) {
                delay(1000)
                println("Computing v2:${Thread.currentThread().name}")
                6
            }
            println("The answer for v1 / v2 = ${v1.await() / v2.await()}")
        }
    }


    /**

        如果我既想给协程传入一个名字，又同时想给协程指定一个线程可通过“+”实现
     例如这里给协程指定线程Dispatchers.Default，同时指定名字“test”

     */
    @Test
    fun test10() {
        runBlocking<Unit> {
            launch(Dispatchers.Default + CoroutineName("test")) {
                //我工作在线程： DefaultDispatcher-worker-1 @test#2
                println("我工作在线程： ${Thread.currentThread().name}")
            }
        }
    }
}