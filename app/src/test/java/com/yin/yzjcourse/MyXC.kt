package com.yin.yzjcourse

import kotlinx.coroutines.*
import org.junit.Test



fun main(args: Array<String>) {
    /*
    GlobalScope.launch { // 在后台启动一个新的协程并继续
        delay(1000L)
        println("World!：${Thread.currentThread().id},${Thread.currentThread().name}") // 新线程
    }
    println("Hello,:${Thread.currentThread().id},${Thread.currentThread().name}")
//    sleep()的目的是保证在launch()的协程执行完后，jvm仍然存活，发起launch()的线程仍然存在
    Thread.sleep(2000L)
    */


/*
    GlobalScope.launch {
        delay(1000L)
        println("第1：${Thread.currentThread().id},${Thread.currentThread().name}") //新线程
    }
    println("第2:${Thread.currentThread().id},${Thread.currentThread().name}")
    //会阻塞所在的线程，不会开辟新的线程,目的是阻塞所在线程使有足够的时间等到launch加载的协程执行完
    //之所以用runBlocking包裹，可以用runBlocking实现一个协程，因为delay()是suspend函数，需要在协程内或另一个suspend函数内调用
    runBlocking {
        //不是新的线程，仍然是外部那个线程
        delay(2000L)
        println("第3:${Thread.currentThread().id},${Thread.currentThread().name}")
    }
    */


    /*
    runBlocking {
        GlobalScope.launch {
            delay(1000L)
            println("第1：${Thread.currentThread().id},${Thread.currentThread().name}") //新线程
        }
        println("第2:${Thread.currentThread().id},${Thread.currentThread().name}")
        //会阻塞所在的线程，不会开辟新的线程
        runBlocking {
            //不是新的线程，仍然是外部那个线程
            delay(2000L)
            println("第3:${Thread.currentThread().id},${Thread.currentThread().name}")
        }
    }
     */


    /*
    runBlocking {
        val job:Job = GlobalScope.launch {
            delay(3000L)
            println("第1：${Thread.currentThread().id},${Thread.currentThread().name}") // 新线程
        }
        println("第2:${Thread.currentThread().id},${Thread.currentThread().name}")
        //这样就不需要sleep()或delay()一段时间等launch()执行完了，当launch()执行完直接从join()这里继续向下走，时间更加精准，无缝衔接
        job.join()
        println("第3:${Thread.currentThread().id},${Thread.currentThread().name}")
    }
     */

/*
    //runBlocking作用域中启动所有的协程执行完，runBlocking才算执行完，且它启动的所有协程都没有开辟新的线程
    runBlocking {
        launch {
            //没有创建新的线程，而是创建新的协程，作用域仍然是runBlocking创建的作用域
            delay(3000L)
            println("第1：${Thread.currentThread().id},${Thread.currentThread().name}") // 原来的线程
        }
        println("第2:${Thread.currentThread().id},${Thread.currentThread().name}")
    }
    */


/*
    GlobalScope.launch { // 在后台启动一个新的协程并继续
        delay(1000L)
        println("第1：${Thread.currentThread().id},${Thread.currentThread().name}") // 新线程
        //与上面的runBlocking里的launch相比，为什么这个launch就能创建新的线程
        launch {
            println("第2：${Thread.currentThread().id},${Thread.currentThread().name}") // 新线程
        }
    }
    println("第3:${Thread.currentThread().id},${Thread.currentThread().name}")
    Thread.sleep(9000L)
    */

    /**
        runBlocking的launch类似post,会先向下继续执行，然后执行launch体

        coroutineScope则不是，而是
     */
    /*
    runBlocking { // this: CoroutineScope
        launch {
//            println("第10:${Thread.currentThread().id},${Thread.currentThread().name}")
//            delay(200L)
            println("第1:${Thread.currentThread().id},${Thread.currentThread().name}")
        }

        coroutineScope { // 创建一个协程作用域
            launch {
//                println("第20:${Thread.currentThread().id},${Thread.currentThread().name}")
//                delay(500L)
                println("第2:${Thread.currentThread().id},${Thread.currentThread().name}")
            }
//            println("第30:${Thread.currentThread().id},${Thread.currentThread().name}")
//            delay(100L)
            println("第3:${Thread.currentThread().id},${Thread.currentThread().name}")
        }
        println("第4:${Thread.currentThread().id},${Thread.currentThread().name}")
    }
    */
}



class MyXC {
//    @Test
//    fun test1(){
//        println("sdfsdfsd")
//    }
}