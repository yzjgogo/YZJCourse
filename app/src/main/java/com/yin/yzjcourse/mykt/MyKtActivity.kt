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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
            }
        }
    }

    fun requestData(){
        Thread.sleep(3000)
        val url = "http://file-manage.xxfz.com.cn//baiduyun/baiduboskey/zhledu/bj?token=d8ed1f5c5d76acb2f5adc1ab5bf49d5efe7b39577695b8ca6c30c53b9f8f11f4"
        val stsUrl = URL(url)
        val conn:HttpURLConnection = stsUrl.openConnection() as HttpURLConnection
        val input: InputStream = conn.inputStream
        val jsonText = IOUtils.readStreamAsString(input,"utf-8")
        DLog.eLog("请求结果：$jsonText")
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
