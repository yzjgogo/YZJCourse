package com.yin.yzjcourse.Net

import android.os.Bundle
import android.view.View
import butterknife.ButterKnife
import butterknife.OnClick
import com.yin.yzjcourse.BaseActivity
import com.yin.yzjcourse.Net.VerityUtil.VALIDATE_KEY
import com.yin.yzjcourse.R
import com.yin.yzjcourse.utils.DLog
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

private val BASE_URL: String = "http://101.132.26.110:8098/api/"

class RetrofitPostActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit_post)
        ButterKnife.bind(this)
    }

    @OnClick(R.id.bt_start_post)
    fun onViewClicked(view: View) {
        when (view.id) {
            R.id.bt_start_post -> {
                doPost()
            }
        }
    }

    private fun doPost() {
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL).build()
        val loginService = retrofit.create(LoginService::class.java)
        //之所以定义map,是因为用来小学学习的请求短信验证码的接口，要使用VerityUtil
        val map = HashMap<String, Any>()
        map["phone"] = "18117843721"
        map["device_id"] = "87953F9CD4AFA5A6097376DC5B6A72ED"
        map["type"] = 15
        map["business_id"] = 1
        map["edition_id"] = 625
        map["op_path"] = "message.security.applyregist"
        VerityUtil.sortMap(map, 0, "75da472a-1baf-4f06-a995-7e5b3b33acbf")
        val call: Call<ResponseBody> = loginService.doLogin(
                map["phone"] as String,
                map["device_id"] as String,
                map["type"] as Int,
                map["business_id"] as Int,
                map["edition_id"] as Int,
                map["op_path"] as String,
                map[VALIDATE_KEY] as String)
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {

                try {
                    DLog.eLog("请求头：${call.request().headers()}")
                    DLog.eLog("响应码：${response.code()}")
                    DLog.eLog("${response.isSuccessful}," +
                            "响应码：${response.code()}," +
                            "响应错误体：${response.errorBody()}," +
                            "响应头：${response.headers().toString()}," +
                            "信息：${response.message()}")


                    DLog.eLog("2是ResponseBody吗？：${response.body() is ResponseBody}")//是ResponseBody吗？：true
                    val rspBody: String = response.body()!!.string()
                    DLog.eLog(rspBody)
                } catch (e: Exception) {
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    /**
    @Headers --> 用于添加请求体，不区分get还是post

    @FormUrlEncoded --> 一般post方式时配合Field注解使用，使用该注解,表示请求正文将使用表单网址编码。字段应该声明为参数，并用@Field注释或FieldMap注释

    @POST --> 说明请求方法是post请求

    @Field("paramName") --> post请求时可以用这个注解请求参数,paramName是后台定义的请求参数
     */
    interface LoginService {
        @Headers("Accept: application/json",
                "Connection: Keep-Alive",
                "Charset: UTF-8",
                "User-Agent: (Linux; Android; OS/Android10; Terminal/1; zh_CN_#Hans; deviceType/HRY-AL00a; DeviceId/87953F9CD4AFA5A6097376DC5B6A72ED; Build/HONORHRY-AL00a; Scope/com.zhl.fep.aphone; VersionCode/5060;)",
                "Content-Type: application/json")
        @FormUrlEncoded
        @POST("message/security/applyregist")
        fun doLogin(@Field("phone") phone: String,
                    @Field("device_id") device_id: String,
                    @Field("type") type: Int,
                    @Field("business_id") business_id: Int,
                    @Field("edition_id") edition_id: Int,
                    @Field("op_path") op_path: String,
                    @Field("validate") validate: String): Call<ResponseBody>
    }
}