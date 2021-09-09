package com.yin.yzjcourse.k22协程

//import kotlinx.coroutines.delay
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import org.junit.Test

/**
 * 弥补
 * // 流暂时到这里，有空继续研究：https://www.kotlincn.net/docs/reference/coroutines/flow.html，搜索test10()里的关键字就找到学到哪里了
 * */
class `5异步流` {

    /**
    参考图片：flow_normal.jpg
     */
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


    /**
    其它流构建器：flowOf()
     */
    @Test
    fun test3() {
        /**
        创建一个flow产生给定的值
         */
        runBlocking {
            // flowOf(vararg elements: T) 的简单版
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


    /**
    其它流构建器：asFlow

    调用过程类似test3()的flowOf(),

     */
    @Test
    fun test4() {
        runBlocking<Unit> {
            // 将一个整数区间转化为流，同样asFlow()是冷的，直到collect()才热
            (1..3).asFlow().collect { value -> println(value) }

            //集合转换为流
            val list = listOf<Int>(1, 2, 3)
            list.asFlow().collect {
                println("输出集合flow:$it")
            }

            //序列转换为流
            list.asSequence().asFlow().collect {
                println("序列转换为flow:$it")
            }
        }
    }


    /**

    操作符：map  可以一个个转换流中的每一个元素、
    操作符：filter  可以一个个转换流中的每一个元素满足条件的保留，不满足条件的淘汰
    这些操作符也是冷操作符，因此这些操作符不是suspend函数，只是针对流的定义，当真正要使用流中的数据时，例如调用collect()
    函数时操作符内部的逻辑(这里是performRequest())才真正执行，因此操作符的函数类型的参数是suspend函数


     */
    @Test
    fun test5() {
        runBlocking<Unit> {
            (1..3).asFlow() // 一个请求流
                    .map { request -> performRequest(request) }
                    .collect { response -> println("使用map：$response") }


            (1..3).asFlow().filter {
                if (it > 1) {
                    return@filter true
                }
                return@filter false
            }.collect {
                println("使用filter:$it")
            }
        }
    }

    suspend fun performRequest(request: Int): String {
//        println("打印")//只有调用了collect()performRequest才真正执行，因此操作符也是冷的
        delay(1000) // 模仿长时间运行的异步工作
        return "response $request"
    }


    /**
    操作符：transform，支持高度自定义，完全能够实现map,filter等等操作符的功能；
     */
    @Test
    fun test6() {
        runBlocking<Unit> {
            (1..3).asFlow() // 一个请求流
                    .transform { request ->
                        //可在transform中根据业务发射指定的数据
                        emit("Making request $request")
                        emit(performRequest2(request))
                    }
                    .collect { response -> println(response) }
        }
    }

    suspend fun performRequest2(request: Int): String {
        delay(6000) // 模仿长时间运行的异步任务
        return "response $request"
    }


    /**
    限长操作符：take(n),无论这个流打算发射多少个，我最多取n个，没发射的也不会再执行发射操作
     */
    @Test
    fun test7(){
        runBlocking<Unit> {
            numbers()
                    .take(2) // 只获取前两个
                    .collect { value -> println(value) }
        }
    }
    fun numbers(): Flow<Int> = flow {
        try {
            emit(1)
            emit(2)
            println("This line will not execute")
            emit(3)
        } finally {
            println("Finally in numbers")
        }
    }


    /**
    输出：1,4
    输出：5,9
    输出：14,16
    输出：30,25
    55

        末端操作符1：
        reduce:计算出所有值通过一定逻辑一一合并的结果
        例如流 T(a),T(b),T(c),T(d)
        T(a),T(b) --> T(ab)
        T(ab),T(c) --> T(abc)
  sum = T(abc),T(d) --> T(abcd)

     */
    @Test
    fun test8(){
        runBlocking<Unit> {
            val sum = (1..5).asFlow()
                    .map { it * it } // 数字 1 至 5 的平方
                    .reduce { a, b -> println("输出：$a,$b"); a + b } // 求和（末端操作符）

            /*
            val list = listOf(Dog("a"),Dog("b"),Dog("c"))
            val sum = list.asFlow()
                    .reduce { a, b -> println("输出：$a,$b"); Dog(a.toString()+b.toString()) } // 求和（末端操作符）
            */
            println(sum)
        }
    }
    data class Dog(val name:String)


    /**
    末端操作符2：
     */
    @Test
    fun test9(){
        runBlocking {
            val list = (1..3).asFlow().toList()
            println("流转为list：$list")

            val set = (1..3).asFlow().toSet()
            println("流转为set：$set")

            val first = (1..3).asFlow().first()
            println("第一个值：$first")

            val single = (1..1).asFlow().single()
            println("唯一的值：$single")
        }
    }


    /**
    流是连续的,可以连续调用多个操作符，且不启动新的协程
     */
    @Test
    fun test10(){
        runBlocking<Unit> {
            (1..5).asFlow()
                    .filter {
                        println("Filter $it")
                        it % 2 == 0
                    }
                    .map {
                        println("Map $it")
                        "string $it"
                    }.collect {
                        println("Collect $it")
                    }
        }
    }
    // 流暂时到这里，有空继续研究：https://www.kotlincn.net/docs/reference/coroutines/flow.html，搜索test10()里的关键字就找到学到哪里了
}