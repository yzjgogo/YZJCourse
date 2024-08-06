package com.yin.yzjcourse.Net;

//import com.artgo.picture.text2image.MyApplication;
//import com.artgo.picture.text2image.entity.CreateResponseEntity;
//import com.artgo.picture.text2image.entity.InputEntity;
//import com.artgo.picture.text2image.entity.TranslateRequestEntity;
//import com.artgo.picture.text2image.entity.TranslateResponseEntity;
//import com.artgo.picture.text2image.room.entity.PictureEntity;
//import com.artgo.picture.text2image.util.ConstantsKt;
//import com.artgo.picture.text2image.util.LogUtil;

//import com.zhl.xxxx.aphone.entity.UploadAvatarGetUrlEntity;
//import com.zhl.xxxx.aphone.eventbus.CallWebLogoutEvent;
//import com.zhl.xxxx.aphone.util.CacheUtils;
//import com.zhl.xxxx.aphone.util.LogUtil;
//import com.zhl.xxxx.aphone.util.ToastUtil;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.UUID;

import de.greenrobot.event.EventBus;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleObserver;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import zhl.common.request.VerityUtil;
import zhl.common.utils.DebugUrl;
import zhl.common.utils.JsonHp;
import zhl.common.utils.Tools;

public class ArtRequest {
    public static final int EXCEPTION_CODE = 9527;
    public static HttpURLConnection conn;
    public static HttpURLConnection inputConn;

    /**
        发起一个post请求
     UploadAvatarGetUrlEntity 是响应的数据类型泛型
     * */
    public static void getPhotoUploadInfo(ICreateListener<UploadAvatarGetUrlEntity> listener) {
        Single.create(new SingleOnSubscribe<UploadAvatarGetUrlEntity>() {
            @Override
            public void subscribe(SingleEmitter<UploadAvatarGetUrlEntity> emitter) throws Exception {
                //此时在子线程，发起网络请求
                try {
                    conn = (HttpURLConnection) new URL("http://192.168.2.6:37604/api/yx/file/get-upload-url").openConnection();
                    conn.setRequestMethod("POST");
                    conn.setReadTimeout(100000);
                    conn.setConnectTimeout(100000);
                    conn.setDoOutput(true);
                    conn.setDoInput(true);
                    conn.setUseCaches(false);


                    conn.setRequestProperty("content-type", "application/json");
                    conn.setRequestProperty("Token", "");
                    conn.setRequestProperty("Scope", "com.fy.yx.study.aphone");

                    //4个请求参数：resource_type，file_name，file_type，content_type
                    HashMap<String, Object> param = new HashMap<String, Object>();
                    param.put("resource_type", 4);
                    param.put("file_name", "pic");
                    param.put("file_type", "png");
                    param.put("content_type", "image/png");

                    //类似小学学习，将请求参数json话，方便后面paramString.getBytes()，写进请求的OutputStream里
                    String paramString = VerityUtil.sortMap(param, 0l, "");
//                    LogUtil.eLog("创建图片的请求参数：" + paramString);

                    //获取输出流
                    OutputStream out = conn.getOutputStream();
                    out.write(paramString.getBytes());
                    out.flush();

                    //获取响应码
                    int responseCode = conn.getResponseCode();
//                    LogUtil.eLog("响应的code：" + responseCode);
                    //200代表，本次post请求成功，但是业务上是否成功，要看具体服务端的约定，从response里去看
                    if (responseCode == 200 || responseCode == 201) {
                        //获取响应的数据的输入流
                        InputStream is = conn.getInputStream();
                        //将响应的数据的输入流转为json字符串，方便解析
                        String response = Tools.getResponse(is);
                        //读取json数据，解析出业务数据
                        JSONObject jsonObject = new JSONObject(response);
                        if(jsonObject.getInt("code") == 0){
                            UploadAvatarGetUrlEntity responseEntity = JsonHp.getGsonConverter().fromJson(jsonObject.getString("data"), UploadAvatarGetUrlEntity.class);
//                            LogUtil.eLog("输出响应1：" + response);
//                            LogUtil.eLog("输出响应2：" + responseEntity);
                            emitter.onSuccess(responseEntity);
                        }else {
//                            ToastUtil.toast(response);
                            int errCode = jsonObject.getInt("code");
                            switch (errCode){
                                case 400:
                                case 100000:
                                case 100001:
                                case 100002:
                                case 100003:
                                case 100004:
                                case 400001:
                                case 400002:
                                case 400003:
                                case 400004:
                                case 400005:
                                case 400006:
                                case 400007:
//                                    EventBus.getDefault().post(new CallWebLogoutEvent());
                                    break;
                            }
                        }
                    } else {
                        //本次post请求失败
                        emitter.onError(new Throwable(conn.getResponseMessage()));
                    }
//                            */
                } catch (Exception exception) {
                    emitter.onError(exception);
                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<UploadAvatarGetUrlEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onSuccess(UploadAvatarGetUrlEntity responseEntity) {
                        //此时已经切换到主线程了
                        if (listener != null) {
                            listener.onSuccess(responseEntity);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (listener != null) {
                            listener.onFailure(-1, e.getMessage());
                        }
                    }
                });
    }

    /**
      使用HttpURLConnection将图片的二进制文件通过PUT请求发送给服务器
     * */
    public static void uploadPhoto(File imageFile, String requestURL, ICreateListener<Object> listener) {
        Single.create(new SingleOnSubscribe<Object>() {
            @Override
            public void subscribe(SingleEmitter<Object> emitter) throws Exception {
                DataOutputStream outputStream = null;
                try {
                    //获取图片文件的FileInputStream
                    FileInputStream fileInputStream = new FileInputStream(imageFile);
                    conn = (HttpURLConnection) new URL(requestURL).openConnection();
                    conn.setRequestMethod("PUT");
                    conn.setReadTimeout(100000);
                    conn.setConnectTimeout(100000);
                    conn.setDoOutput(true);
                    conn.setDoInput(true);
                    conn.setUseCaches(false);

                    conn.setRequestProperty("Token", "");
                    conn.setRequestProperty("Scope", "com.fy.yx.study.aphone");
                    conn.setRequestProperty("Connection", "Keep-Alive");
                    conn.setRequestProperty("Content-Type", "image/png");

                    //获取输出流，将图片文件的fileInputStream写入输出流，即完成图片二进制文件的提交
                    outputStream = new DataOutputStream(conn.getOutputStream());
                    // 读取文件并写入
                    byte[] buffer = new byte[4096];
                    int bytesRead;
                    while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    outputStream.flush();

                    int responseCode = conn.getResponseCode();
                    if (responseCode == 200 || responseCode == 201) {
                        InputStream is = conn.getInputStream();
                        String response = Tools.getResponse(is);
//                        LogUtil.eLog("PUT上传图片：" + response);
                        emitter.onSuccess(response);
                    } else {
                        emitter.onError(new Throwable(conn.getResponseMessage()));
                    }
                } catch (Exception exception) {
                    emitter.onError(exception);
                } finally {
                    try {
                        if (outputStream != null) outputStream.close();
                        if (conn != null) conn.disconnect();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onSuccess(Object responseEntity) {
                        if (listener != null) {
                            listener.onSuccess(responseEntity);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (listener != null) {
                            listener.onFailure(-1, e.getMessage());
                        }
                    }
                });
    }

    public static byte[] getFileData(File file) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        FileInputStream fis = new FileInputStream(file);
        byte[] buffer = new byte[1024];
        int read;
        while ((read = fis.read(buffer)) != -1) {
            bos.write(buffer, 0, read);
        }
        fis.close();
        return bos.toByteArray();
    }

    public static String generateBoundary() {
//        return "===" + UUID.randomUUID().toString() + "===";
        return UUID.randomUUID().toString();
    }

    /**
        获取图片或文件的字节数组，放到一个请求参数中，例如这里放到image中，发起post请求，提交给服务器
     * */
    public static void getCodeFromImage(File imageFile, ICreateListener<String> listener) {
        Single.create(new SingleOnSubscribe<String>() {
            @Override
            public void subscribe(SingleEmitter<String> emitter) throws Exception {
                DataOutputStream outputStream = null;
                try {
//                    long time1 = System.currentTimeMillis();
                    //获取图片的字节数组
                    byte[] dataImg = getFileData(imageFile);
//                    LogUtil.eLog("耗时1："+(System.currentTimeMillis()-time1)/1000.0f);
                    conn = (HttpURLConnection) new URL("http://192.168.2.6:37604/api/yx/member/getimagecardcode").openConnection();
                    conn.setRequestMethod("POST");
                    conn.setReadTimeout(100000);
                    conn.setConnectTimeout(100000);
                    conn.setDoOutput(true);
                    conn.setDoInput(true);
                    conn.setUseCaches(false);


                    conn.setRequestProperty("Connection", "Keep-Alive");
                    conn.setRequestProperty("content-type", "application/json");
                    conn.setRequestProperty("Token", "");
                    conn.setRequestProperty("Scope", "com.fy.yx.study.aphone");

                    //三个请求参数page_no，page_size，image，其中image存放的就是字节数组
                    HashMap<String, Object> param = new HashMap<String, Object>();
                    param.put("page_no", 1);
                    param.put("page_size", 1);
                    param.put("image", dataImg);
//
//
//                    long time2 = System.currentTimeMillis();
                    //同样需要将所有请求参数(包括image)json化，方便后面的paramString.getBytes()，写入OutputStream，发起网络请求
                    String paramString = VerityUtil.sortMap(param, 0l, "");
//                    LogUtil.eLog("耗时2："+(System.currentTimeMillis()-time2)/1000.0f);
//                    LogUtil.eLog("创建图片的请求参数：" + paramString);

                    //获取输出流
                    OutputStream out = conn.getOutputStream();
                    out.write(paramString.getBytes());
                    out.flush();


                    int responseCode = conn.getResponseCode();
//                    LogUtil.eLog("响应的code：" + responseCode);
                    if (responseCode == 200 || responseCode == 201) {
                        InputStream is = conn.getInputStream();
                        String response = Tools.getResponse(is);
                        JSONObject jsonObject = new JSONObject(response);
                        if(jsonObject.getInt("code") == 0){
//                            LogUtil.eLog("图片识别1：" + response);
                            emitter.onSuccess(response);
                        }else {
//                            emitter.onSuccess("");
//                            ToastUtil.toast(response);
                            int errCode = jsonObject.getInt("code");
                            switch (errCode){
                                case 400:
                                case 100000:
                                case 100001:
                                case 100002:
                                case 100003:
                                case 100004:
                                case 400001:
                                case 400002:
                                case 400003:
                                case 400004:
                                case 400005:
                                case 400006:
                                case 400007:
//                                    EventBus.getDefault().post(new CallWebLogoutEvent());
                                    break;
                            }
                        }
                    } else {
                        emitter.onError(new Throwable(conn.getResponseMessage()));
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                    emitter.onError(exception);
                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onSuccess(String responseEntity) {
                        if (listener != null) {
                            listener.onSuccess(responseEntity);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (listener != null) {
                            listener.onFailure(-1, e.getMessage());
                        }
                    }
                });
    }


    private static InputStream getInputStreamFromNetUrl(String imgUrl) throws Exception {
//        inputConn = (HttpURLConnection) new URL(imgUrl).openConnection(Proxy.NO_PROXY);
        inputConn = (HttpURLConnection) new URL(imgUrl).openConnection();
        inputConn.setRequestMethod("GET");
        inputConn.setReadTimeout(100000);
        inputConn.setConnectTimeout(100000);
        inputConn.setUseCaches(false);

        int responseCode = inputConn.getResponseCode();
        if (responseCode == 200) {
            return inputConn.getInputStream();
        }
        throw new Exception("fail");
    }

    /**
     * 返回FileEntity
     * */
    /*
    public static void createPicture2(HashMap<String, String> requestParam, String keyword, String style, ICreateListener listener) {
//        listener.onSuccess(null);
        Single.create(new SingleOnSubscribe<FileEntity>() {
                    @Override
                    public void subscribe(SingleEmitter<FileEntity> emitter) throws Exception {
                        try {
                            HttpURLConnection conn = (HttpURLConnection) new URL("https://dezgo.p.rapidapi.com/text2image").openConnection();
                            conn.setRequestMethod("POST");
                            conn.setReadTimeout(100000);
                            conn.setConnectTimeout(100000);
                            conn.setDoOutput(true);
                            conn.setDoInput(true);
                            conn.setUseCaches(false);


                            conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
                            conn.setRequestProperty("X-RapidAPI-Key", DEZGO_API_KEY);
                            conn.setRequestProperty("X-RapidAPI-Host", "dezgo.p.rapidapi.com");


                            if (!requestParam.containsKey("guidance")) {
                                requestParam.put("guidance", "7.5");
                            }
                            if (!requestParam.containsKey("width")) {
                                requestParam.put("width", "512");
                            }
                            if (!requestParam.containsKey("sampler")) {
                                requestParam.put("sampler", "k_lms");
                            }
                            if (!requestParam.containsKey("height")) {
                                requestParam.put("height", "512");
                            }
                            if (!requestParam.containsKey("steps")) {
                                requestParam.put("steps", "50");
                            }
//            HashMap<String, String> param = new HashMap<String, String>();
//            param.put("prompt","an astronaut riding a horse, digital art, highly-detailed masterpiece trending HQ");
//            param.put("guidance","7.5");
//            param.put("width","512");
//            param.put("sampler","k_lms");
//            param.put("height","512");
//            param.put("steps","50");
                            String paramString = VerityUtil.sortMap2(requestParam, 0l, "");
                            LogUtil.eLog("创建图片的请求参数：" + paramString);

                            //获取输出流
                            OutputStream out = conn.getOutputStream();
                            out.write(paramString.getBytes());
                            out.flush();

                            int responseCode = conn.getResponseCode();
                            LogUtil.eLog("响应的code：" + responseCode);
                            if (responseCode == 200) {
                                InputStream is = conn.getInputStream();
                                File tempFile = Tools.inputStreamToFile(MyApplication.appContext,is);
                                PictureEntity picture = new PictureEntity();
                                picture.setPrompt(keyword);
                                picture.setStyle(style);
                                picture.setPath(tempFile.getAbsolutePath());
                                picture.setName(ConstantsKt.getPicNameFromTime());
                                picture.setType(ConstantsKt.PICTURE_TYPE_HISTORY_0);
                                long key = MyApplication.myDatabase.pictureDao().insertPicture(picture);
                                emitter.onSuccess(new FileEntity(tempFile.getAbsolutePath(),key));
//                                emitter.onSuccess(Tools.read(is));
                            } else {
                                emitter.onError(new Throwable(conn.getResponseMessage()));
                            }
                        } catch (Exception exception) {
                            emitter.onError(exception);
                        }
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<FileEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onSuccess(FileEntity file) {
                        if (listener != null) {
                            listener.onSuccess(file);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (listener != null) {
                            listener.onFailure(-1, e.getMessage());
                        }
                    }
                });
    }
    */

    /*
    public static void createPicture2(HashMap<String, String> requestParam, ICreateListener listener) {
//        listener.onSuccess(new byte[0]);
        Single.create(new SingleOnSubscribe<byte[]>() {
                    @Override
                    public void subscribe(SingleEmitter<byte[]> emitter) throws Exception {
                        try {
                            HttpURLConnection conn = (HttpURLConnection) new URL("https://dezgo.p.rapidapi.com/text2image").openConnection();
                            conn.setRequestMethod("POST");
                            conn.setReadTimeout(100000);
                            conn.setConnectTimeout(100000);
                            conn.setDoOutput(true);
                            conn.setDoInput(true);
                            conn.setUseCaches(false);


                            conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
                            conn.setRequestProperty("X-RapidAPI-Key", DEZGO_API_KEY);
                            conn.setRequestProperty("X-RapidAPI-Host", "dezgo.p.rapidapi.com");


                            if (!requestParam.containsKey("guidance")) {
                                requestParam.put("guidance", "7.5");
                            }
                            if (!requestParam.containsKey("width")) {
                                requestParam.put("width", "512");
                            }
                            if (!requestParam.containsKey("sampler")) {
                                requestParam.put("sampler", "k_lms");
                            }
                            if (!requestParam.containsKey("height")) {
                                requestParam.put("height", "512");
                            }
                            if (!requestParam.containsKey("steps")) {
                                requestParam.put("steps", "50");
                            }
//            HashMap<String, String> param = new HashMap<String, String>();
//            param.put("prompt","an astronaut riding a horse, digital art, highly-detailed masterpiece trending HQ");
//            param.put("guidance","7.5");
//            param.put("width","512");
//            param.put("sampler","k_lms");
//            param.put("height","512");
//            param.put("steps","50");
                            String paramString = VerityUtil.sortMap2(requestParam, 0l, "");
                            LogUtil.eLog("创建图片的请求参数：" + paramString);

                            //获取输出流
                            OutputStream out = conn.getOutputStream();
                            out.write(paramString.getBytes());
                            out.flush();

                            int responseCode = conn.getResponseCode();
                            LogUtil.eLog("响应的code：" + responseCode);
                            if (responseCode == 200) {
                                InputStream is = conn.getInputStream();
                                emitter.onSuccess(Tools.read(is));
                            } else {
                                emitter.onError(new Throwable(conn.getResponseMessage()));
                            }
                        } catch (Exception exception) {
                            emitter.onError(exception);
                        }
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<byte[]>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onSuccess(byte[] bytes) {
                        if (listener != null) {
                            listener.onSuccess(bytes);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (listener != null) {
                            listener.onFailure(-1, e.getMessage());
                        }
                    }
                });
    }
    */


}
