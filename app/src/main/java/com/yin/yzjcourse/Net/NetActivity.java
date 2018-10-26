package com.yin.yzjcourse.Net;

import android.os.Bundle;
import android.view.View;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;
import com.yin.yzjcourse.utils.DLog;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NetActivity extends BaseActivity {
    public static final String MAIN_URL = "http://c6acbe93.ngrok.io/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_ok, R.id.bt_temp})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_ok:
//                getRequest();
                testGetObject();
                break;
            case R.id.bt_temp:
                testPostObject();
                break;
        }
    }

    private void testGetObject() {
        Map<String, String> paras = new HashMap<>();
        paras.put("id", "id_get");
        paras.put("type", "type_get");
        doGetObject(MAIN_URL, paras, new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response != null) {
                    DLog.eLog("get响应：" +
                            "\nresponse.isSuccessful():" + response.isSuccessful() +
                            "\nresponse.protocol():" + response.protocol() +
                            "\nresponse.code():" + response.code() +
                            "\nresponse.message():" + response.message() +
                            "\nresponse.headers():\n" + response.headers().toString() +
                            "\nresponse.body():" + response.body().string() +
                            "\nresponse.networkResponse().request().headers():\n" + response.networkResponse().request().headers().toString() +
                            "\nresponse.request():" + response.request().toString());
                } else {
                    DLog.eLog("get响应为空");
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                DLog.eLog("get出错：" + e.toString());
            }
        });
    }


    private void testPostObject() {
        Map<String, String> paras = new HashMap<>();
        paras.put("id", "id_post");
        paras.put("type", "type_post");
        doPostObject(MAIN_URL + "signin", paras, new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response != null) {
                    DLog.eLog("post响应：" +
                            "\nresponse.isSuccessful():" + response.isSuccessful() +
                            "\nresponse.protocol():" + response.protocol() +
                            "\nresponse.code():" + response.code() +
                            "\nresponse.message():" + response.message() +
                            "\nresponse.headers():\n" + response.headers().toString() +
                            "\nresponse.body():" + response.body().string() +
                            "\nresponse.networkResponse().request().headers():\n" + response.networkResponse().request().headers().toString() +
                            "\nresponse.request():" + response.request().toString());
                } else {
                    DLog.eLog("post响应为空");
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                DLog.eLog("post出错：" + e.toString());
            }
        });
    }

    /**
     * get request:have no requestBody
     *
     * @param url
     * @param paras
     * @param callback
     */
    public static void doGetObject(String url, Map<String, String> paras, Callback callback) {
        //give 'get' request paras
        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
        if (paras != null) {
            for (Map.Entry<String, String> entry : paras.entrySet()) {
                urlBuilder.addQueryParameter(entry.getKey(), entry.getValue());
            }
        }

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
//                            .url(url)//直接传递url,例如:www.baidu.com  www.baidu.com/?id=1&type=2
                .url(urlBuilder.build())//http://024d8cee.ngrok.io/?id=id_para&type=type_para
                .addHeader("diy-head", "yzjcourse")//add request-header data
                .build();
        //request.headers() get you added header,here is diy-head:yzjcourse
        DLog.eLog("get请求url：" + url +
                "\nget请求头：" + request.headers().toString());
//        Response response = client.newCall(request).execute(); run at the main thread
        client.newCall(request).enqueue(callback);//run at the child thread
    }

    /**
     * post request:have request body
     *
     * @param url
     * @param paras
     * @param callback
     */
    public static void doPostObject(String url, Map<String, String> paras, Callback callback) {
        //give 'post' request paras
//        RequestBody formBody = new FormBody.Builder()
//                                .add("id","ids")
//                                .add("type","types")
//                                .build();
        FormBody.Builder formBody = new FormBody.Builder();
        if (paras != null) {
            for (Map.Entry<String, String> entry : paras.entrySet()) {
                formBody.add(entry.getKey(), entry.getValue());
            }
        }
        RequestBody requestBody = formBody.build();

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .addHeader("IM-POST", "impost")//添加请求头
                .build();
        DLog.eLog("post请求url：" + url +
                "\npost请求头：" + request.headers().toString());
        client.newCall(request).enqueue(callback);//run at the child thread
    }
}
