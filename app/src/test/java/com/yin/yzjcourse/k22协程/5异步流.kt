package com.yin.yzjcourse.k22协程

//import kotlinx.coroutines.delay
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import org.junit.Test

class `5异步流` {

    @InternalCoroutinesApi
    @Test
    fun test1() {
        runBlocking<Unit> {
            // 启动并发的协程以验证主线程并未阻塞
            launch {
                for (k in 1..3) {
                    println("I'm not blocked $k,${Thread.currentThread().name}")
                    delay(100)
                }
            }
            // 收集这个流，其实下面的那个flow(block)里的block函数，就是这个flowCollector的扩展函数，
            //简单过程就是：flowCollector.block() -> block()里又调用了flowCollector.emit(i),这样就打印出来了，也就是说block()里面调用的那个emit(i)，就是这里的这个override suspend fun emit(value: Int)
            val flowCollector = object : FlowCollector<Int> {
                override suspend fun emit(value: Int) {
                    println("一个个打印:${value},${Thread.currentThread().name}")
                }
            }
            //流是冷的，当调用simple()时，flow(block)里的block并没有运行，这个block函数类型只是作为SafeFlow(block)的一个属性返回这个SafeFlow类型的示例，因此没有什么耗时操作，这也就是为什么
            //simple()没有定义成suspend函数；
            //一旦调用了collect(flowCollector),才热起来，因此suspend设计成suspend函数，根据之前分析的调用流程，就会触发flowCollector.block()，此时block才执行，又因为block可能耗时，因为要把block定义成suspend函数，block()里调用了emit()，emit()也
            //可能耗时，所以emit()也定义成了suspend
            simple().collect(flowCollector)

            //综上：其实上面绕了一大圈，又是定义simple()，又是collect的，其实最终一聚会，就是执行了flowCollector.block()
        }
    }

    /**
    flow(block)，这个block是一个suspend函数，可以被挂起
    但是simple无需用suspend修饰

    flow(block) 返回一个 SafeFlow(block),相当于simple()返回SafeFlow(block)
    然后，SafeFlow(block).collect(FlowCollector),实际上是AbstractFlow.collect(FlowCollector),在这个方法里面会调用collectSafely(safeCollector)，实际上就是SafeFlow(block).collectSafely(safeCollector),
    在这个方法里面就调用了safeCollector.block(),这样就是一个完整的调用流程


     */
    fun simple(): Flow<Int> = flow { // 流构建器
        for (i in 1..3) {
            delay(100) // 假装我们在这里做了一些有用的事情
            println("发射:${i},${Thread.currentThread().name}")
            emit(i) // 发送下一个值
        }
    }


    /**
    Emitting 1
    一个个打印:1,main @coroutine#1
    Emitting 2
    一个个打印:2,main @coroutine#1
    Done

    流的取消
     */
    @InternalCoroutinesApi
    @Test
    fun test2() {
//        /*
        runBlocking<Unit> {
            //250ms后超时，超时自动执行协程的取消操作，delay()支持取消，取消后没有什么异常，没来得及emit()的将不会有机会发射而已，因为取消了
            withTimeoutOrNull(7000) { // 在 250 毫秒后超时
                simple2().collect(object : FlowCollector<Int> {
                    override suspend fun emit(value: Int) {
                        println("一个个打印:${value},${Thread.currentThread().name}")
                    }
                })
            }
            println("Done")
        }
//        */


/*
这样测试取消也可以
        runBlocking {
            val job = launch {
                simple2().collect(object : FlowCollector<Int> {
                    override suspend fun emit(value: Int) {
                        println("一个个打印:${value},${Thread.currentThread().name}")
                    }
                })
            }
            delay(7000)
            job.cancelAndJoin()
        }
        */
    }

    fun simple2(): Flow<Int> = flow {
        for (i in 1..3) {
            delay(3000)
            println("Emitting $i")
            emit(i)
        }
    }


    @Test
    fun test3(){
        runBlocking {
            flowOf(10010).collect {
                println("打印出：$it")
            }
            /**
                同理flowOf(10010)是冷的，并没有发生数据
                直到collect()才热起来，因此collect()是suspend函数，它里面的action也是action函数

                整个调用逻辑参考：flowof.png


             */
        }
    }
}