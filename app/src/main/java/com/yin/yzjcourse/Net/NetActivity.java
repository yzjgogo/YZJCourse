package com.yin.yzjcourse.Net;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;
import com.yin.yzjcourse.tools.ToolsActivity;
import com.yin.yzjcourse.utils.DLog;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;

public class NetActivity extends BaseActivity {
    public static final String MAIN_URL = "https://pzx.pagekite.me/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_ok, R.id.bt_temp,R.id.bt_original_socket,R.id.bt_web_socket})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_ok:
//                getRequest();
                testGetObject();
                break;
            case R.id.bt_temp:
                testPostObject();
                break;
            case R.id.bt_original_socket:
                Toast.makeText(this,"没有跳转",Toast.LENGTH_SHORT).show();
                break;
            case R.id.bt_web_socket:
                Toast.makeText(this,"看看这里的代码",Toast.LENGTH_SHORT).show();
                /**
                 * 重连websocket
                 * 此方法在handler中调用，由于重新连接websocket的线程如果与原先连接websocket的线程相同，会报异常，
                 * 所以切换到HandlerThread的线程中重连
                 */
    /*
    private void reConnectWebSocket() {
        if (null != client && !client.isOpen() && !isReConnecting) {
            //LogUtils.showLog("socket onStartConnect");
            client.reconnect();
        }
    }
    */

//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//
//        try {
//            LogUtils.showLog("socket service onDestroy()");
//            client.close();
//        } catch (Exception e) {
//
//        }
//}


    /*
    1、重连机制需要自己来写：

            （1）监听网络连接， 当从断开到连上就可以调用重连

（2）在client回调的onError和onClose中调用重连

2、心跳机制需要自己写

    自己写一个循环的handler， 隔20秒就发送一个心跳：client.sendPing()， 这时候onWebsocketPong()会回调一次，这就是一次心跳

3、后端传回来的消息在onMessage中返回来， 我们可以跟后台定格式，哪些业务需要处理什么格式的数据，然后根据type来分发给业务。
*/
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
                            "\n响应头response.headers():\n" + response.headers().toString() +
                            "\nresponse.body():" + response.body().string() +
                            "\n请求头response.networkResponse().request().headers():\n" + response.networkResponse().request().headers().toString() +
                            "\nresponse.request():" + response.request().toString());//Request{method=GET, url=https://pzx.pagekite.me/?type=type_get&id=id_get, tags={}}
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
        //1:give 'post' request paras throw RequestBody directly
//        RequestBody formBody = new FormBody.Builder()
//                                .add("id","ids")
//                                .add("type","types")
//                                .build();

        //2:throw MediaType create RequestBody
//        MediaType mediaType = MediaType.parse("text/x-markdown; charset=utf-8");
//        RequestBody requestBody = RequestBody.create(mediaType,"requestBody");

        //3:post way to submit stream
//        RequestBody streamBody = new RequestBody() {
//            @Nullable
//            @Override
//            public MediaType contentType() {
//                return MediaType.parse("text/x-markdown; charset=utf-8");
//            }
//
//            @Override
//            public void writeTo(BufferedSink sink) throws IOException {
//                sink.writeUtf8("I am Jdqm.");
//            }
//        };

        //4:post way to submit file.
//        MediaType mediaType = MediaType.parse("text/x-markdown; charset=utf-8");
//        File file = new File("test.md");
//        RequestBody fileBody = RequestBody.create(mediaType, file);

        //5：POST方式提交分块请求
//        String IMGUR_CLIENT_ID = "...";
//        MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
//        MultipartBody body = new MultipartBody.Builder("AaB03x")
//                .setType(MultipartBody.FORM)
//                .addPart(
//                        Headers.of("Content-Disposition", "form-data; name=\"title\""),
//                        RequestBody.create(null, "Square Logo"))
//                .addPart(
//                        Headers.of("Content-Disposition", "form-data; name=\"image\""),
//                        RequestBody.create(MEDIA_TYPE_PNG, new File("website/static/logo-square.png")))
//                .build();
//
//        Request request = new Request.Builder()
//                .header("Authorization", "Client-ID " + IMGUR_CLIENT_ID)
//                .url("https://api.imgur.com/3/image")
//                .post(body)
//                .build();


        //3:post way to submit form data.
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
