package com.yin.yzjcourse.utils;

import android.content.Context;
import android.media.MediaPlayer;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.lidroid.xutils.util.LogUtils;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;
import com.liulishuo.filedownloader.FileDownloader;
import com.yin.yzjcourse.MyApplication;
import com.yin.yzjcourse.bean.TranslateResultEntity;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import zhl.common.utils.Tools;

public class TranslationRequest {
    private static final String BASE_URL = "http://openapi.youdao.com/api";
    private static final String APP_KEY = "7085d0603c50438b";
    private static final String APP_SECRET = "FCkqhCGZlfRsYXemdqcaveca0RETm7Ot";

    private RequestQueue requestQueue;
    private Gson gson;

//    public TranslationRequest(Context context) {
//        requestQueue = Volley.newRequestQueue(context);
//        gson = new Gson();
//    }

//    public void translate(String query, Response.Listener<TranslationResponse> listener, Response.ErrorListener errorListener) {
//        String url = BASE_URL + \"?q=\" + query + \"&appKey=\" + APP_KEY + \"&appSecret=\" + APP_SECRET;
//        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
//                response -> {
//                    TranslationResponse translationResponse = gson.fromJson(response.toString(), TranslationResponse.class);
//                    listener.onResponse(translationResponse);
//                },
//                errorListener);
//        requestQueue.add(request);
//    }
private static Map<String, String> createRequestParams(String english) {
    /*
     * note: 将下列变量替换为需要请求的参数
     * 取值参考文档: https://ai.youdao.com/DOCSIRMA/html/%E8%87%AA%E7%84%B6%E8%AF%AD%E8%A8%80%E7%BF%BB%E8%AF%91/API%E6%96%87%E6%A1%A3/%E6%96%87%E6%9C%AC%E7%BF%BB%E8%AF%91%E6%9C%8D%E5%8A%A1/%E6%96%87%E6%9C%AC%E7%BF%BB%E8%AF%91%E6%9C%8D%E5%8A%A1-API%E6%96%87%E6%A1%A3.html
     */
//    String q = "Let me demonstrate it for you one more time, like this, stand it up.";
//    String q = "It's 9 o'clock. Should we freshen up and get ready for bed? It's more comfortable to play in bed.";
//    String q = "Spit it out, spit it out.";
    String q = english;
    String from = "en";
    String to = "zh-CHS";
    String vocabId = "您的用户词表ID";
//    String voice = "1";
    String voice = "0";

    return new HashMap<String, String>() {{
        put("q", q);
        put("from", from);
        put("to", to);
        put("voice", voice);
//        put("vocabId", new String[]{vocabId});
    }};
}
    public static void translate(String order, String english, MediaPlayer.OnCompletionListener completionListener) throws NoSuchAlgorithmException {

        File dir = new File(Tools.getSDPath()+"/ycourse/english");
        DLog.eLog("目录："+dir.exists()+ "," +dir.isDirectory());
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.getName().startsWith(order + "-")) {
                DLog.eLog("找到本地音频："+file.getAbsolutePath());
                MediaPlayerSingleton.getInstance().play(file.getAbsolutePath(),completionListener);
                return;
            }
        }
        DLog.eLog("没有找到本地音频");

        Map<String, String> params = createRequestParams(english);
        // 添加鉴权相关参数
        AuthV3Util.addAuthParams(APP_KEY, APP_SECRET, params);
        // 请求api服务
//        Map<String, String> paras = new HashMap<>();
//        paras.put("q", "good");
//        paras.put("from", "en");
//        paras.put("to", "zh-CHS");
//        paras.put("salt", "saltffff6hf");
//        paras.put("appKey", "7085d0603c50438b");
//        paras.put("appSecret", "FCkqhCGZlfRsYXemdqcaveca0RETm7Ot");
        doGetObject("http://openapi.youdao.com/api", params, new Callback() {
            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {
                if (response != null) {
//                    DLog.eLog("get响应：" +
//                            "\nresponse.isSuccessful():" + response.isSuccessful() +
//                            "\nresponse.protocol():" + response.protocol() +
//                            "\nresponse.code():" + response.code() +
//                            "\nresponse.message():" + response.message() +
//                            "\n响应头response.headers():\n" + response.headers().toString() +
//                            "\nresponse.body():" + response.body().string() +
//                            "\n请求头response.networkResponse().request().headers():\n" + response.networkResponse().request().headers().toString() +
//                            "\nresponse.request():" + response.request().toString());//Request{method=GET, url=https://pzx.pagekite.me/?type=type_get&id=id_get, tags={}}

                    DLog.eLog("原文：");
                    String fileName;
                    String newEnglish = english;
                    if(english.contains("?")){
                        newEnglish = english.replace("?","");
                    }
                    if(newEnglish.endsWith(".")){
                        DLog.eLog(order+"-"+newEnglish+"mp3");
                        fileName = order+"-"+newEnglish+"mp3";
                    }else {
                        DLog.eLog(order+"-"+newEnglish+".mp3");
                        fileName = order+"-"+newEnglish+".mp3";
                    }
                    String respData = response.body().string();
//                    DLog.eLog("响应的内容："+respData);
                    Gson gson = new Gson();
                    TranslateResultEntity translateResultEntity = gson.fromJson(respData, TranslateResultEntity.class);
                    DLog.eLog("获取音频地址：");
                    DLog.eLog(translateResultEntity.speakUrl);
//                    MediaPlayerSingleton.getInstance().play(translateResultEntity.speakUrl);


                    downloadAudio(fileName, translateResultEntity,completionListener);


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

    private static void downloadAudio(String fileName, TranslateResultEntity translateResultEntity,MediaPlayer.OnCompletionListener completionListener) {
        FileDownloader.setup(MyApplication.appContext);
        FileDownloader.getImpl().create(translateResultEntity.speakUrl)
                .setPath(getDownloadPath(fileName))
                .setListener(new FileDownloadListener() {
                    @Override
                    protected void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {

                    }

                    @Override
                    protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {

                    }

                    @Override
                    protected void completed(BaseDownloadTask task) {
                    MediaPlayerSingleton.getInstance().play(getDownloadPath(fileName),completionListener);
                        DLog.eLog("执行completed："+ fileName +" , "+ translateResultEntity.speakUrl);
                    }

                    @Override
                    protected void paused(BaseDownloadTask task, int soFarBytes, int totalBytes) {

                    }

                    @Override
                    protected void error(BaseDownloadTask task, Throwable e) {
                        DLog.eLog("执行error:"+e.getMessage()+" , "+ fileName +" , "+ translateResultEntity.speakUrl);
                        if (completionListener!=null) {
                            completionListener.onCompletion(null);
                        }
                    }

                    @Override
                    protected void warn(BaseDownloadTask task) {
                        DLog.eLog("执行warn："+ fileName +" , "+ translateResultEntity.speakUrl);
                    }
                }).start();
    }

    public static void doGetObject(String url, Map<String, String> paras, Callback callback) {
        //give 'get' request paras
        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
        if (paras != null) {
            for (Map.Entry<String, String> entry : paras.entrySet()) {
                urlBuilder.addQueryParameter(entry.getKey(), entry.getValue());
            }
        }

        OkHttpClient client = new OkHttpClient();
        okhttp3.Request request = new okhttp3.Request.Builder()
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
    private static String getDownloadPath(String name){
        return Tools.getSDPath()+"/ycourse/" + "english/"+name;
    }
}
