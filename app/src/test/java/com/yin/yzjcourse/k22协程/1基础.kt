package com.yin.yzjcourse.k22协程

import kotlinx.coroutines.*
import org.junit.Test

class `1基础` {

    @Test
    fun test1() {
        //launch有点类似android的post
        GlobalScope.launch { // 在后台启动一个新的协程并继续
            //delay十一个suspend函数，会挂起，兵分两路
            delay(1000L)
            println("World!：${Thread.currentThread().id},${Thread.currentThread().name}") // 新线程
        }
        println("Hello,:${Thread.currentThread().id},${Thread.currentThread().name}")
//因为现在是单元测试，不是在app里    sleep()的目的是保证在launch()的协程执行完后，jvm仍然存活，发起launch()的线程仍然存在
        Thread.sleep(2000L)
    }

    @Test
    fun test2() {
        GlobalScope.launch {
            delay(1000L)
            println("第1：${Thread.currentThread().id},${Thread.currentThread().name}") //新线程
        }
        println("第2:${Thread.currentThread().id},${Thread.currentThread().name}")
        //会阻塞所在的线程，不会开辟新的线程,目的是阻塞所在线程使有足够的时间等到launch加载的协程执行完
        //之所以用runBlocking包裹，可以用runBlocking实现一个协程，因为delay()是suspend函数，需要在协程内或另一个suspend函数内调用
        //runBlocking不是suspend函数，只是一个普通函数,是阻塞式的
        runBlocking {
            //不是新的线程，仍然是外部那个线程
            delay(2000L)
            println("第3:${Thread.currentThread().id},${Thread.currentThread().name}")
        }
    }

    @Test
    fun test3() {
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
    }

    @Test
    fun test4() {
        runBlocking {
            val job: Job = GlobalScope.launch {
                delay(3000L)
                println("第1：${Thread.currentThread().id},${Thread.currentThread().name}") // 新线程
            }
            println("第2:${Thread.currentThread().id},${Thread.currentThread().name}")
            //这样就不需要sleep()或delay()一段时间等launch()执行完了，当launch()执行完直接从join()这里继续向下走，时间更加精准，无缝衔接
            job.join()
            println("第3:${Thread.currentThread().id},${Thread.currentThread().name}")
        }
    }

    @Test
    fun test5() {
        //这里就不需要sleep或join了，因为runBlocking使用了launch,整个都会执行完
        //runBlocking作用域中启动所有的协程执行完，runBlocking才算执行完，且它启动的所有协程都没有开辟新的线程
        runBlocking {
            launch {
                //没有创建新的线程，而是创建新的协程，作用域仍然是runBlocking创建的作用域
                delay(3000L)
                println("第1：${Thread.currentThread().id},${Thread.currentThread().name}") // 原来的线程
            }
            println("第2:${Thread.currentThread().id},${Thread.currentThread().name}")
        }
    }

    @Test
    fun test6() {
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
    }

    /**
    runBlocking 与 coroutineScope的区别
    test7_1和test7_2

    第1
    第2 等5000ms
    第3
    第4

    前提：只看里面的runBlocking,不看外面的，外面的只是为了和test7_2保持一致，一样的协程环境
     结论：runBlocking方法会阻塞当前线程等待协程的执行，
     例如这里：“第1”执行后，遇到了runBlocking,则这个runBlocking会阻塞其所在的线程，直到整个runBlocking协程及其所有子协程执行完后才会解除阻塞，因此接着这行“第2”
     等了5000ms后执行“第3”，这时，整个runBlocking执行完毕，线程的阻塞解除，就轮到“第4”执行了

     注意无论runBlocking传入的什么CoroutineContext效果都是一样的
     */
    @Test
    fun test7_1() {
        runBlocking {
            println("第1")
//            runBlocking(Dispatchers.IO) {
//            runBlocking(Dispatchers.Unconfined) {
            runBlocking {
                println("第2")
                delay(5000)
                println("第3")
            }
            println("第4")
        }
    }
    @Test
    fun test7_2() {
        /*
        runBlocking {
            println("第11")
//            runBlocking(Dispatchers.IO) {
//            runBlocking(Dispatchers.Unconfined) {
            coroutineScope {
                println("第22")
                delay(5000)
                println("第33")
            }
            println("第44")
        }
        */
        println("第1")
        GlobalScope.launch(Dispatchers.IO) {
            println("第2")
            coroutineScope {
                println("第3")
                delay(5000)
                println("第4")
            }
            println("第5")
        }
        println("第6")
        Thread.sleep(9000)
    }

    /**
    第1
    第3
    第2

    start = CoroutineStart.DEFAULT
    默认值，从这个协程创建之初，就可以被立刻执行，直接进入等待被执行调度的状态，
    比如这里无需什么触发条件，可以正常按逻辑执行launch{"协程"}到对应的协程代码
     */
    @Test
    fun test8() {
        runBlocking {
            println("第1")
            launch(start = CoroutineStart.DEFAULT) {
                delay(2000L)
                println("第2")
            }
            println("第3")
            Thread.sleep(3000)
        }
    }

    /**
    第1
    第3

    start = CoroutineStart.LAZY
    需要触发，才会进入可被执行调度的状态，如果没有触发，就是一堆死代码，跟不存在一样，这里这个协程就没有执行的机会
    具体怎么触发，看test10()
     */
    @Test
    fun test9() {
        runBlocking {
            println("第1")
            launch(start = CoroutineStart.LAZY) {
                delay(2000L)
                println("第2")
            }
            println("第3")
            Thread.sleep(3000)
        }
    }

    /**
    第1
    第3

    start = CoroutineStart.LAZY
    接着test9()触发CoroutineStart.LAZY类型的协程
     */
    @Test
    fun test10() {
        runBlocking {
            println("第1")
            val job = launch(start = CoroutineStart.LAZY) {
                delay(2000L)
                println("第2")
            }

            //start()只是触发协程进入可被执行调度的状态，相当于(CoroutineStart.LAZY+start())==CoroutineStart.DEFAULT，并没有开始调用
            //join()和await()则是直接发起该协程的调用

//            job.start()//可以触发CoroutineStart.LAZY的协程进入可被执行的状态，但是还没被执行，(CoroutineStart.LAZY+start())==CoroutineStart.DEFAULT
            job.join()//也可以触发CoroutineStart.LAZY但同时开始执行了，相当于在这里创建了一个CoroutineStart.DEFAULT的协程(把上面的job的创建的代码赋值到了这里且start=CoroutineStart.DEFAULT一样)
            //如果是用async()创建的协程，await()也可以触发CoroutineStart.LAZY但同时开始执行了
            println("第3")
            Thread.sleep(3000)
        }
    }


}