package com.yin.yzjcourse.k22协程

import kotlinx.coroutines.*
import org.junit.Test

class `2取消与超时` {

    /**
    取消协程的执行，例如你关闭activity时，需要再onDestroy中取消
    job: I'm sleeping 0 ...
    job: I'm sleeping 1 ...
    job: I'm sleeping 2 ...
    main: I'm tired of waiting!
    main: Now I can quit.
     */
    @Test
    fun test8() {
        runBlocking {
            val job = launch {
                repeat(3) { i ->
                    println("job: I'm sleeping $i ...")
                    delay(500L)
                }
            }
            delay(2300L) // delay a bit，给jvm足够的存活时间，因为我们现在在test里，不是在app里
            println("main: I'm tired of waiting!")
            job.cancel() // cancels the job
            job.join() // waits for job's completion，同样要join，因为被取消也是一种结束，本质上与自动执行完是一样的道理，
//            job.cancelAndJoin() 相当于cancel()和join()同时使用
            println("main: Now I can quit.")
        }
    }

    /**
    job: I'm sleeping 0 ...
    job: I'm sleeping 1 ...
    job: I'm sleeping 2 ...
    main: I'm tired of waiting!
    job: I'm sleeping 3 ...
    job: I'm sleeping 4 ...
    main: Now I can quit.

    虽然调用了cancelAndJoin()，但是while循环仍然在执行，并没有真正意义上取消
    解决方法看test10()
     */
    @Test
    fun test9() {
        runBlocking {
            val startTime = System.currentTimeMillis()
            val job = launch(Dispatchers.Default) {
                var nextPrintTime = startTime
                var i = 0
                while (i < 5) { // computation loop, just wastes CPU
                    // print a message twice a second
                    if (System.currentTimeMillis() >= nextPrintTime) {
                        println("job: I'm sleeping ${i++} ...")
                        nextPrintTime += 500L
                    }
                }
            }
            delay(1300L) // delay a bit
            println("main: I'm tired of waiting!")
            job.cancelAndJoin() // 虽然调用了cancelAndJoin()，但是while循环仍然在执行，并没有真正意义上取消
            println("main: Now I can quit.")
        }
    }

    /**
    job: I'm sleeping 0 ...
    job: I'm sleeping 1 ...
    job: I'm sleeping 2 ...
    main: I'm tired of waiting!
    main: Now I can quit.

    isActive是CoroutineScope的扩展属性，可以实时判断当前协程有没有别取消
     参考:com.yin.yzjcourse.k22协程.4协程上下文与调度器.test7
     */
    @Test
    fun test10() {
        runBlocking {
            val startTime = System.currentTimeMillis()
            val job = launch(Dispatchers.Default) {
                var nextPrintTime = startTime
                var i = 0
                while (isActive) { // cancellable computation loop
                    // print a message twice a second
                    if (System.currentTimeMillis() >= nextPrintTime) {
                        println("job: I'm sleeping ${i++} ...")
                        nextPrintTime += 500L
                    }
                }
            }
            delay(1300L) // delay a bit
            println("main: I'm tired of waiting!")
            job.cancelAndJoin() // cancels the job and waits for its completion
            println("main: Now I can quit.")
        }
    }


    /**
    join 和 cancelAndJoin 等待了所有的终结动作执行完毕， 所以运行示例得到了下面的输出：

    job: I'm sleeping 0 ...
    job: I'm sleeping 1 ...
    job: I'm sleeping 2 ...
    main: I'm tired of waiting!
    job: I'm running finally
    main: Now I can quit.

    我们通常使用如下的方法处理在被取消时抛出 CancellationException 的可被取消的挂起函数。
    但是不知道什么时候会跑出CancellationException，虽然job.cancel(但是不知道什么时候会跑出CancellationException)可以传入但是，我尝试传入后也没有跑出这个异常，为什么？
     */
    @Test
    fun test11() {
        runBlocking {
            val job = launch {
                try {
                    repeat(1000) { i ->
                        println("job: I'm sleeping $i ...")
                        delay(500L)
                    }
                } finally {
                    println("job: I'm running finally")
                }
            }
            delay(1300L) // 延迟一段时间
            println("main: I'm tired of waiting!")
            job.cancelAndJoin() // 取消该作业并且等待它结束
            println("main: Now I can quit.")
        }
    }

    /**
    withContext(NonCancellable)用法1
    当我们希望一个协程不能被取消时，可以使用NonCancellable实现
     */
    @Test
    fun test12() {
        runBlocking {
            val job = launch {
                withContext(NonCancellable) {
                    repeat(1000) { i ->
                        println("job: I'm sleeping $i ...")
                        delay(500L)
                    }
                }
            }
            delay(1300L) // 延迟一段时间
            println("main: I'm tired of waiting!")
            job.cancelAndJoin() // 取消该作业并等待它结束
            println("main: Now I can quit.")
        }
    }

    /**
    withContext(NonCancellable)用法2
    当我们希望一个协程被取消后，仍然想调用suspend函数时，可以用NonCancellable
     */
    @Test
    fun test13() {
        runBlocking {
            val job = launch {
                try {
                    repeat(1000) { i ->
                        println("job: I'm sleeping $i ...")
                        delay(500L)
                    }
                } finally {
                    withContext(NonCancellable) {
                        println("job: I'm running finally")
                        delay(1000L)
                        println("job: And I've just delayed for 1 sec because I'm non-cancellable")
                    }
                    //如果不用withContext(NonCancellable)包裹，则在delay(1000L)这个suspend函数已经出错，它后面的那个println()不会执行，但是并没有异常信息是为什么呢？
//                    println("job: I'm running finally")
//                    delay(1000L)
//                    println("job: And I've just delayed for 1 sec because I'm non-cancellable")
                }
            }
            delay(1300L) // 延迟一段时间
            println("main: I'm tired of waiting!")
            job.cancelAndJoin() // 取消该作业并等待它结束
            println("main: Now I can quit.")
        }
    }

    /**
    超时1：
    在指定的1300L内没有完成协程任务，跑出异常：kotlinx.coroutines.TimeoutCancellationException: Timed out waiting for 1300 ms
     */
    @Test
    fun test14() {
        runBlocking {
            withTimeout(1300L) {
                repeat(1000) { i ->
                    println("I'm sleeping $i ...")
                    delay(500L)
                }
            }
//            println("输出:${result}")
        }
    }

    /**
    超时2：
    在指定的1300L内没有完成协程任务，而是返回一个null值
     */
    @Test
    fun test15() {
        runBlocking {
            val result = withTimeoutOrNull(1300L) {
                repeat(1000) { i ->
                    println("I'm sleeping $i ...")
                    delay(500L)
                }
            }
            println("输出:${result}")
        }
    }

}