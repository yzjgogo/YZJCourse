package com.yin.yzjcourse.Net;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.yin.yzjcourse.R;
import com.yin.yzjcourse.utils.DLog;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import zhl.common.base.BaseActivity;
import zhl.common.request.AbsResult;
import zhl.common.request.BaseRequest;
import zhl.common.request.RequestListener;
import zhl.common.request.VerityUtil;
import zhl.common.request.ZHLRequest;

public class FrameworkActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_work);
        findViewById(R.id.bt_post).setOnClickListener(this);
        findViewById(R.id.bt_get).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.bt_post:
                doPost();
                break;
            case R.id.bt_get:
                doGet();
                break;
        }
    }

    private void doGet() {
        execute(BaseRequest.getRequest(PocOp.GET_SALT_API, 2), new RequestListener() {
            @Override
            public void onSuccess(ZHLRequest request, AbsResult result) {

            }

            @Override
            public void onFailure(ZHLRequest request, String msg) {

            }
        });
    }

    private void doPost() {
        execute(BaseRequest.getRequest(PocOp.POST_VERSION_API, 2), new RequestListener() {
            @Override
            public void onSuccess(ZHLRequest request, AbsResult result) {

            }

            @Override
            public void onFailure(ZHLRequest request, String msg) {

            }
        });
    }
    private void doConn(){


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String codeUrl = "https://mobile.11tohome.com/device/auth/check-app-version";
//                    if (DebugUrl.HOST_TYPE == DebugUrl.TYPE_DEBUG) {
//                        codeUrl = "http://111.204.225.50:8113/debug-8099/zhl-english/api/message/security/checkmsgcode";
//                    }else if(DebugUrl.HOST_TYPE == DebugUrl.TYPE_TEST){
//                        codeUrl = "http://101.132.26.110:8098/api/message/security/checkmsgcode";
//                    }else if(DebugUrl.HOST_TYPE == DebugUrl.TYPE_ONLINE || DebugUrl.HOST_TYPE == DebugUrl.TYPE_DISTRIBUTION){
//                        codeUrl = "https://zhl-education.xxfz.com.cn/api/message/security/checkmsgcode";
//                    }

                    HttpURLConnection conn = (HttpURLConnection) new URL(codeUrl).openConnection();
                    conn.setRequestMethod("POST");
                    conn.setReadTimeout(10000);
                    conn.setConnectTimeout(10000);
                    conn.setDoOutput(true);
                    conn.setDoInput(true);
                    conn.setUseCaches(false);

                    //addRequestProperty:如果key不存在则添加；如果key存在则在原来的基础上追加，
                    //例如原来("Content-Type","application/json"),则addRequestProperty("Content-Type","charset=utf-8")后就是("Content-Type","application/json;charset=utf-8")；
                    //setRequestProperty:如果key不存在则就是添加；如果key存在则覆盖原来的无论原来有几个；
                    conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
                    conn.setRequestProperty("Connection","Keep-Alive");
                    conn.setRequestProperty("Mingyi-Version","2,V5.1.0,M2007J17C,11");
                    conn.setRequestProperty("App-Version","1");
                    conn.setRequestProperty("digest","FMent1y81WWSRmz1z4mYcw==");

//                    conn.setRequestProperty("Accept","application/json");
//                    conn.setRequestProperty("Charset","UTF-8");
//                    String agent = UserAgentUtils.get(LoginActivity.this);
//                    conn.setRequestProperty("User-Agent",agent);
//                        conn.setRequestProperty("Content-Type","application/json");
//                        conn.addRe



                    HashMap<String, Object> param = new HashMap<String, Object>();// 传入请求参数
                    param.put("versionType", "2");
                    param.put("versionCode", "51000");
//                    param.put("code", inputCode);
//                    param.put("type", GetValidateCode.CODE_REQUEST_TYPE_LOGIN_15);
//                    param.put("business_id", ConfigKey.BUSINESS_ID);
//                    if (OwnApplicationLike.getEditionId()!=0) {
//                        param.put("edition_id", OwnApplicationLike.getEditionId());
//                    }
//                    param.put("op_path", "message.security.checkmsgcode");
                    String paramString = VerityUtil.sortMap(param, 0l, "");

                    //获取输出流
                    OutputStream out = conn.getOutputStream();
//                    out.write(paramString.getBytes());
                    out.write("versionType=2&versionCode=51000".getBytes());
                    out.flush();


                    int responseCode = conn.getResponseCode();
                    Log.e("zhl","响应的code："+responseCode);
                    if (responseCode == 200){
                        // 获取响应的输入流对象
                        InputStream is = conn.getInputStream();
                        // 创建字节输出流对象
                        ByteArrayOutputStream message = new ByteArrayOutputStream();
                        // 定义读取的长度
                        int len = 0;
                        // 定义缓冲区
                        byte buffer[] = new byte[1024];
                        // 按照缓冲区的大小，循环读取
                        while ((len = is.read(buffer)) != -1) {
                            // 根据读取的长度写入到os对象中
                            message.write(buffer, 0, len);
                        }
                        // 释放资源
                        is.close();
                        message.close();
                        // 返回字符串
                        String responseData = new String(message.toByteArray());
                        Log.e("zhl","响应的数据："+responseData);
//                        Handler mHandler = new Handler(Looper.getMainLooper());
//                        mHandler.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                try {
//                                    JSONObject jsonObject = new JSONObject(responseData);
//                                    MessageValidateEntity messageValidateEntity = JsonHp.getGsonConverter().fromJson(jsonObject.getString("data"), MessageValidateEntity.class);
//                                    LogUtil.eLog("响应的实体："+messageValidateEntity.toString());
//                                    doPhoneLogin(messageValidateEntity);
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
