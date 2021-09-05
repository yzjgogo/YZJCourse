package com.yin.yzjcourse.mykt

import android.content.Intent
import android.os.Bundle
import android.view.View
import butterknife.ButterKnife
import butterknife.OnClick
import com.yin.yzjcourse.BaseActivity
import com.yin.yzjcourse.R
import com.yin.yzjcourse.mykt.AndroidExtensions.ExtensionActivity
import com.yin.yzjcourse.mykt.MyAnko.MyAnkoActivity
import com.yin.yzjcourse.utils.DLog
import com.yin.yzjcourse.utils.IOUtils
import kotlinx.android.synthetic.main.activity_my_kt.*
import kotlinx.coroutines.*
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class MyKtActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_kt)
        ButterKnife.bind(this)
    }

    @OnClick(R.id.bt_extension,R.id.bt_anko,R.id.bt_xiecheng)
    fun onViewClicked(view: View) {
        when (view.id) {
            R.id.bt_extension -> {
                startActivity(Intent(this, ExtensionActivity::class.java))
            }
            R.id.bt_anko -> {
                startActivity(Intent(this, MyAnkoActivity::class.java))
            }
            R.id.bt_xiecheng -> {
//                Thread {
//                    requestData()
//                }.start()

                /*
                DLog.eLog("第1：${Thread.currentThread().name}")
//                GlobalScope.launch() {
                GlobalScope.launch(Dispatchers.Main) {
                    DLog.eLog("第2：${Thread.currentThread().name}\"")
                    withContext(Dispatchers.IO){
                        DLog.eLog("第5：${Thread.currentThread().name}\"")
                        requestData()
                        DLog.eLog("第6：${Thread.currentThread().name}\"")
                    }
//                    requestData()
                    DLog.eLog("第3：${Thread.currentThread().name}\"")

                    launch(Dispatchers.IO) {
                        DLog.eLog("第7：${Thread.currentThread().name}\"")
                        requestData2()
                        DLog.eLog("第8：${Thread.currentThread().name}\"")
                    }
                    DLog.eLog("第9：${Thread.currentThread().name}\"")
                }
                DLog.eLog("第4：${Thread.currentThread().name}\"")
                */

                /**
                 * 协程仅仅只是Kotlin为了更方便操作线程，而对线程API的封装，本质上还是线程在工作，这与android中的Handler和AsyncTask没什么区别；
                 *
                 * 协程：例如launch{"代码"}，包裹的那部分"代码"就是协程；
                 *
                 * 挂起(suspend)：launch{"代码"}就好像android里post{"代码"}了一段代码，当这段代码运行到一个suspend函数时，此时，会兵分两路，
                 * 一路：原本运行这段协程的线程执行完毕，不再执行从这个suspend函数开始往后的所有代码，这个线程该干嘛干嘛去，例如继续刷新界面等等；
                 * 二路：这个suspend函数会开辟一个线程执行耗时操作，执行完成后会自动再切回原来这段协程所在的线程，从调用suspend函数的下一行开始继续执行；
                 * 一般有耗时操作的函数我们会定义成suspend函数，提醒函数的调用者你需要在协程里调用我
                 *
                 * 非阻塞式调用：用同步的代码写出异步的调用的方式，避免了接口回调，这不是协程所独有的，完全可以通过且线程的方式实现非阻塞式调用，只有单线程的上下两行代码
                 * 才是阻塞式的
                 * */
                GlobalScope.launch(Dispatchers.Main) {
                    DLog.eLog("开始：${Thread.currentThread().id},${Thread.currentThread().name}")
                    val result = requestNetData()
                    DLog.eLog("后面1：$result,${Thread.currentThread().id},${Thread.currentThread().name}")
                }
                DLog.eLog("后面2：${Thread.currentThread().id},${Thread.currentThread().name}")
            }
        }
    }

    suspend fun requestNetData():String{
        return withContext(Dispatchers.IO){
            return@withContext requestData()
        }
    }
    fun requestData():String{
        Thread.sleep(5000)
        val url = "http://file-manage.xxfz.com.cn//baiduyun/baiduboskey/zhledu/bj?token=d8ed1f5c5d76acb2f5adc1ab5bf49d5efe7b39577695b8ca6c30c53b9f8f11f4"
        val stsUrl = URL(url)
        val conn:HttpURLConnection = stsUrl.openConnection() as HttpURLConnection
        val input: InputStream = conn.inputStream
        val jsonText = IOUtils.readStreamAsString(input,"utf-8")
//        DLog.eLog("请求结果：${jsonText.javaClass}")
        return "请求结果：${jsonText.javaClass}"
    }

    fun requestData2(){
        Thread.sleep(4000)
        val url = "http://file-manage.xxfz.com.cn//baiduyun/baiduboskey/zhledu/bj?token=d8ed1f5c5d76acb2f5adc1ab5bf49d5efe7b39577695b8ca6c30c53b9f8f11f4"
        val stsUrl = URL(url)
        val conn:HttpURLConnection = stsUrl.openConnection() as HttpURLConnection
        val input: InputStream = conn.inputStream
        val jsonText = IOUtils.readStreamAsString(input,"utf-8")
        DLog.eLog("请求结果2：$jsonText")
    }
}
