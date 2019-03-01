package com.yin.yzjcourse.Base.ThreadPool;

import android.os.Bundle;
import android.os.SystemClock;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;
import com.yin.yzjcourse.utils.DLog;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 为什么需要线程池：
 * 1：当有过多的线程任务需要执行时，需要大量且频繁的创建和销毁线程，因此会造成较大的性能开销。
 * 2：因为CPU会为每个线程分配时间片，当太多线程一起工作时可能出现互相抢夺CPU资源的情况，进而导致阻塞的现象。
 *
 * 线程池解决了哪些问题：
 * 1：重用线程池中的线程，避免因为线程的创建和销毁所带来的性能开销。
 * 2：能有效控制线程池的最大并发数，避免大量的线程之间因互相抢占系统资源而导致的阻塞现象。
 * 3：能够对线程进行简单的管理，并提供定时执行以及指定时间间隔循环执行等功能。
 *
 * Android中的线程池延用了Java的Executor,Executor是一个接口，真正的线程池的实现为ThreadPoolExecutor,ThreadPoolExecutor提供了
 * 一些列参数用于配置线程池，通过不同的参数可以创建不同的线程池。
 *
 *
 public ThreadPoolExecutor(int corePoolSize,
                             int maximumPoolSize,
                             long keepAliveTime,
                             TimeUnit unit,
                             BlockingQueue<Runnable> workQueue,
                             ThreadFactory threadFactory)
 */
public class TPActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tp);
        ButterKnife.bind(this);
    }


    private void runThreadPool() {
        Runnable command = new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(2000);
                DLog.eLog("执行了："+Thread.currentThread().getName());
            }
        };

        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(4);
        fixedThreadPool.execute(command);

        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        cachedThreadPool.execute(command);

        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(4);
        // 2000ms后执行command
        scheduledThreadPool.schedule(command, 2000, TimeUnit.MILLISECONDS);
        // 延迟10ms后，每隔1000ms执行一次command
        scheduledThreadPool.scheduleAtFixedRate(command, 10, 1000, TimeUnit.MILLISECONDS);

        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        singleThreadExecutor.execute(command);
    }

    @OnClick(R.id.bt_pool)
    public void onClick() {
        runThreadPool();
    }
}
