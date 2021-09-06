package com.yin.yzjcourse.k22协程

import kotlinx.coroutines.*
import org.junit.Test
import kotlin.system.measureTimeMillis

class `3组合挂起函数` {


    /**
    measureTimeMillis()是一个普通函数，接收一个函数类型的参数，用于计算这行这个函数类型的参数需要多长时间
     */
    @Test
    fun test16() {
        runBlocking<Unit> {
            val time = measureTimeMillis {
                val one = doSomethingUsefulOne()
                val two = doSomethingUsefulTwo()
                println("The answer is ${one + two}")//The answer is 42   13+29=42
            }
            println("Completed in $time ms")//Completed in 2015 ms    1000L+1000L = 2015
        }
    }


    /**
     * 当你希望同时并行发起多个suspend函数时，可以使用async(),类似于launch(),async()也是启动一个协程，只是
     * 它不是直接返回一个Job,而是返回Job的子类Deferred(一个轻量级的非阻塞的future，表示稍后会提供结果，你可以使用.await()获取它这个稍后的结果)
     *
     */
    @Test
    fun test17() {
        runBlocking<Unit> {
            val time = measureTimeMillis {
                val one = async { doSomethingUsefulOne() }
//            one.await() //如果在这里调用one.await()则就相当于，耗时等待doSomethingUsefulOne()的结果了，就失去了多个async并行发起的意义了，可以打开注释看看
                val two = async { doSomethingUsefulTwo() }
                //前面的两个async，有点像只是注册一下，还没有实际发起doSomethingUsefulOne()和doSomethingUsefulTwo()的执行，直到调用了await()才真正发起，且是多个Deferred同时发起
                println("The answer is ${one.await() + two.await()}")//The answer is 42
            }
            //可见，并行发起总耗费的时间，以最长耗时那个方法为准，而不是两个方法时间的累加
            println("Completed in $time ms")//Completed in 1027 ms
        }
    }


    /**
        参考com.yin.yzjcourse.k22协程.1基础.test8，test9，test10
     */
    @Test
    fun test18() {
        runBlocking<Unit> {
            val time = measureTimeMillis {
                val one = async(start = CoroutineStart.LAZY) { doSomethingUsefulOne() }
//            one.await() //如果在这里调用one.await()则就相当于，耗时等待doSomethingUsefulOne()的结果了，就失去了多个async并行发起的意义了，可以打开注释看看
                val two = async(start = CoroutineStart.LAZY) { doSomethingUsefulTwo() }
                //触发LAZY的协程进入可执行调度的状态，但是还没被调用
                one.start()
                two.start()
                //join()和await()则是直接调用执行协程
//                one.join()
//                two.join()
                //前面的两个async，有点像只是注册一下，还没有实际发起doSomethingUsefulOne()和doSomethingUsefulTwo()的执行，直到调用了await()才真正发起，且是多个Deferred同时发起
                //前面one.start()和two.start()才能做到这里并发执行，否则只是顺序执行，时间是两个方法的总和
                println("The answer is ${one.await() + two.await()}")//The answer is 42
            }
            //可见，并行发起总耗费的时间，以最长耗时那个方法为准，而不是两个方法时间的累加
            println("Completed in $time ms")//Completed in 1027 ms
        }
    }


    /**
        一个async()并发调用 抽取的案例
     */
    @Test
    fun test19() {
        runBlocking<Unit> {
            val time = measureTimeMillis {
                println("The answer is ${concurrentSum()}")
            }
            println("Completed in $time ms")
        }
    }


    /**
    如果其中一个子协程（即 two）失败，第一个 async会被取消
     */
    @Test
    fun test20() {
        runBlocking<Unit> {
            try {
                failedConcurrentSum()
            } catch(e: ArithmeticException) {
                println("Computation failed with ArithmeticException:$isActive")//isActive == true
            }finally {
                println("Computation failed with ArithmeticException---:$isActive")//isActive == true
            }
        }
    }

    suspend fun doSomethingUsefulOne(): Int {
        delay(1000L) // 假设我们在这里做了些有用的事
        return 13
    }

    suspend fun doSomethingUsefulTwo(): Int {
        delay(1000L) // 假设我们在这里也做了一些有用的事
        return 29
    }
    suspend fun concurrentSum(): Int = coroutineScope {
        val one = async { doSomethingUsefulOne() }
        val two = async { doSomethingUsefulTwo() }
        one.await() + two.await()
    }
    //如果其中一个子协程（即 two）失败，第一个 async会被取消
    suspend fun failedConcurrentSum(): Int = coroutineScope {
        val one = async<Int> {
            try {
                delay(Long.MAX_VALUE) // 模拟一个长时间的运算
                42
            } finally {
                println("First child was cancelled:$isActive")//isActive == false
            }
        }
        val two = async<Int> {
            println("Second child throws an exception")
            throw ArithmeticException()
        }
        one.await() + two.await()
    }
}