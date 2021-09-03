package com.yin.yzjcourse.Net

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import butterknife.ButterKnife
import butterknife.OnClick
import com.yin.yzjcourse.BaseActivity
import com.yin.yzjcourse.R
import com.yin.yzjcourse.utils.DLog
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query



private val TOKEN: String = "d8ed1f5c5d76acb2f5adc1ab5bf49d5efe7b39577695b8ca6c30c53b9f8f11f4"
private val BASE_URL: String = "http://file-manage.xxfz.com.cn/"
class ConverterFactoryActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_converter_factory)
        ButterKnife.bind(this)
    }

    @OnClick(R.id.bt_start_get)
    fun onViewClicked(view: View) {
        when (view.id) {
            R.id.bt_start_get -> {
                doGet()
            }
        }
    }

    private fun doGet() {
        //要使用GsonConverterFactory，需要引入 implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val service = retrofit.create(BosToolService::class.java)
        //服务端同事定义的响应数据格式就对应RspEntity，使用ConverterFactory后，Retrofit会自动将ResponseBody转换为RspEntity存放到响应数据的body()里，代替默认的ResponseBody
        val call: Call<RspEntity> = service.getBosData("zhledu", TOKEN)
        call.enqueue(object : Callback<RspEntity> {
            override fun onResponse(call: Call<RspEntity>, response: Response<RspEntity>) {
                try {
                    DLog.eLog("${response.isSuccessful}," +
                            "${response.code()}," +
                            "${response.errorBody()}," +
                            "${response.headers().toString()}," +
                            "${response.message()}")


                    DLog.eLog("1是ResponseBody吗？：${response.body() is ResponseBody}")//是ResponseBody吗？：true
                    DLog.eLog("${response.body()!!.javaClass}")
                    DLog.eLog("response.body()就是RspEntity:${response.body()}")
                } catch (e: Exception) {
                }
            }

            override fun onFailure(call: Call<RspEntity>, t: Throwable) {
                DLog.eLog("错误：$t")
            }

        })
    }
    interface BosToolService {
        @GET("/baiduyun/baiduboskey/{dirpath}/bj")
        fun getBosData(
                @Path("dirpath") dir: String,
                @Query("token") mToken: String
        ): Call<RspEntity>
    }
}