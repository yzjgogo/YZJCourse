package com.yin.yzjcourse.Net

import android.os.Bundle
import android.view.View
import butterknife.ButterKnife
import butterknife.OnClick
import com.google.gson.Gson
import com.yin.yzjcourse.BaseActivity
import com.yin.yzjcourse.R
import com.yin.yzjcourse.utils.DLog
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


val TOKEN: String = "d8ed1f5c5d76acb2f5adc1ab5bf49d5efe7b39577695b8ca6c30c53b9f8f11f4"
val BASE_URL: String = "http://file-manage.xxfz.com.cn/"

class RetrofitGetActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit_get)
        ButterKnife.bind(this)
    }

    @OnClick(R.id.bt_start_get)
    fun onViewClicked(view: View) {
        when (view.id) {
            R.id.bt_start_get -> {
                doGet()
            }
//            R.id.bt_stop_service -> {
//                stopService(Intent(this, MyLCService::class.java))
//            }
        }
    }

    private fun doGet() {
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
                .build()
        val service = retrofit.create(BosToolService::class.java)
        val call: Call<ResponseBody> = service.getBosData("zhledu", TOKEN)
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                try {
                    val rspBody: String = response.body()!!.string()
                    val gson = Gson()
                    val bosData = gson.fromJson<RspEntity>(rspBody, RspEntity::class.java)
                    DLog.eLog(bosData.toString())
                } catch (e: Exception) {
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                DLog.eLog("错误：$t")
            }

        })
    }

    /**
    第一步：定义网络请求接口

    @GET -> get请求

    "/baiduyun/baiduboskey/{zhledu}/bj" -> baseUrl后面跟的接口路径

    @Path -> 如果接口路径中某个路径希望动态传入，则使用之，对应路径变量用{}包裹
    注意：@Path不可用于get请求中请求参数的动态替换，例如http://file-manage.xxfz.com.cn//baiduyun/baiduboskey/zhledu/bj?token={mToken}，@Path("mToken") 是错误的

    @Query("token") -> 用于get请求的参数传递，其中token是参数名，不能乱写，后台怎么定义的就怎么写，发起网络请求是会自动拼到网络请求地址后面(例如 ?token=mToken&....)
     */
    interface BosToolService {
        @GET("/baiduyun/baiduboskey/{dirpath}/bj")
        fun getBosData(
                @Path("dirpath") dir: String,
                @Query("token") mToken: String
        ): Call<ResponseBody>
    }
}