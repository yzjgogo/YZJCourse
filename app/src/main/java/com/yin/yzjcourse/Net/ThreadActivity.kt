package com.yin.yzjcourse.Net

import android.os.Bundle
import android.view.View
import butterknife.ButterKnife
import butterknife.OnClick
import com.yin.yzjcourse.BaseActivity
import com.yin.yzjcourse.R
import com.yin.yzjcourse.utils.DLog

/**
 * https://www.cnblogs.com/liyutian/p/10196044.html
 */
class ThreadActivity : BaseActivity() {
    var thread: Thread? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thread)
        ButterKnife.bind(this)
    }

    @OnClick(R.id.bt_start, R.id.bt_stop, R.id.bt_interrupt, R.id.bt_boolean_start, R.id.bt_boolean)
    fun onViewClicked(view: View) {
        when (view.id) {
            R.id.bt_start -> startThread()
            R.id.bt_stop -> stopThread()
            R.id.bt_interrupt -> interruptThread()
            R.id.bt_boolean_start -> booleanThreadStart()
            R.id.bt_boolean -> booleanThread()
        }
    }

    /**
     *
     * stop()方法已经停止不了了
     * 主要配合interruptThread()方法看
     *
     */
    private fun startThread() {
        thread = Thread {
            for (index in 1..600000) {
                //sleep()，wait()等方法遇到interrupt()，会直接跑出InterruptedException，我们可以在这个异常的catch里处理逻辑
//                Thread.sleep(200)

                //参考interruptThread()的注释
                if (Thread.currentThread().isInterrupted) {//收到中断通知，我赶紧善后一下，然后break,退出循环，是run()嗖的一声执行完
                    DLog.eLog("线程被中断了:${Thread.currentThread().name},${Thread.currentThread().isInterrupted},${Thread.interrupted()}")
                    DLog.eLog("再次查看是否中断：${Thread.currentThread().isInterrupted},${Thread.interrupted()}")
                    break
                } else {
                    DLog.eLog("线程内执行:${Thread.currentThread().name}")
                }
            }
        }
        thread!!.start()
    }

    /**
     * 线程的stop()方法已经早就被废弃了，这大概是从设计到废弃用时最短的方法了，包括线程的suspend()和resume()方法也一样；不仅是java，其它很多语言都废弃了类似
     * stop()这种方法
     * 原因是这样：
     * 1，调用stop()方法会立刻终止run()方法的执行，导致其后面剩余的工作没机会执行，包括catch和finally里的逻辑，这就可能会导致一些请理性的工作无法完成例如数据库和流的
     * 关闭等等，产生无法预料的垃圾数据；
     * 2，调用stop()方法会立即释放该线程所持有的所有的锁，此时也许有另一个线程会立即访问相同的内存，导致数据得不到同步，出现数据不一致的问题。
     *
     * 实时上进入stop()方法的代码查看，它已经不能在停止线程了，而是直接跑出一个异常
     */
    private fun stopThread() {
        try {
            //stop()内部还是调用了stop(throwable)
            thread!!.stop()
//            thread!!.stop(Throwable("我是自定义的异常"))
        } catch (e: Exception) {
            DLog.eLog("捕获异常：${e.message},${e}")
        }
    }

    /**
     * interrupt()
     * 并不是像stop()一样，立刻终止线程run()方法的执行，而是通知线程我需要你终止，你在收到这个通知后，应该尽快处理后续逻辑，保证run()方法嗖的一声执行完，
     * 当然了，你不理会我的通知也是可以的，
     * 具体线程怎么收到通知呢，在调用thread.interrupt()后，有两个方式可获取到中断通知：
     * 1，thread.isInterrupted() //判断是否被中断，非静态方法
     * 2，Thread.interrupted() //判断是否被中断，静态方法，调用后会清除当前中断状态，即一旦调用了Thread.interrupted()，后面你再次
     *      调用thread.isInterrupted()或Thread.interrupted()返回的都是false,除非再次调用thread.interrupt()
     */
    private fun interruptThread() {
        thread!!.interrupt()
    }

    ///////////////////////////////////////////////////////////////////////////

    /**
        volatile的理解：https://www.cnblogs.com/dolphin0520/p/3920373.html

        kotlin中没有volatile但是有Volatile注解：https://www.jianshu.com/p/3963e64e7fe7
     */
    class MyThread:Thread(){
        //volatile修饰符用来保证其它线程读取的总是该变量的最新的值
        @Volatile var isExit:Boolean = false
        override fun run() {
            super.run()
            for(index in 1..600000){
                if (isExit) {
                    DLog.eLog("线程终止了:$index")
                    break
                }else{
                    DLog.eLog("线程执行中:$index")
                }
            }
        }
    }
    var mThread:MyThread?=null
    private fun booleanThreadStart() {
        mThread = MyThread()
        mThread!!.start()
    }

    private fun booleanThread() {
        mThread!!.isExit = true
    }
}