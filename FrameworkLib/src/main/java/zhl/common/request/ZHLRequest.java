package zhl.common.request;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.RequestParams;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.exception.HttpException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import de.greenrobot.event.EventBus;
import zhl.common.oauth.OAuthApi;
import zhl.common.oauth.OauthApplicationLike;
import zhl.common.oauth.OauthErrorEvent;
import zhl.common.oauth.TokenEntity;
import zhl.common.request.eas.AesEncryptUtils;
import zhl.common.utils.MLog;
import zhl.common.utils.Tools;

/**
 * Created by zqs on 16/8/10 下午2:10.
 * Copyright © 2016年 BeiJingZhiHuiLiu. All rights reserved.
 */
public class ZHLRequest extends com.android.volley.Request<String> {

    private static final String TAG = "ZHLRequest";

    /** ==========================================绕过ssl验证======================================= */
//    static {
//        disableSslVerification();
//    }

    private RequestListener mListener;
    private AbsResult mResult;
    private int requestType;
    private PreprocessDataListener preprocessDataListener;
    private String cacheKey;
    private Map<String, Object> param;

    public ZHLRequest(String url, Map<String, Object> param, AbsResult result) {
        super(Method.POST, url, null);
        mResult = result;
        this.param = param;
        initRequestParams(param);
    }

    public ZHLRequest(int method,String url, Map<String, Object> param, AbsResult result) {
        super(method, url, null);
        mResult = result;
        initRequestParams(param);
    }


    /*private static void disableSslVerification() {
        try {
            // Create a trust manager that does not validate certificate chains
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }};

            // Install the all-trusting trust manager
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            // Create all-trusting host name verifier
            HostnameVerifier allHostsValid = new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    System.out.println("hostname=" + hostname + ",PeerHost= " + session.getPeerHost());
                    return true;
                }
            };
            // Install the all-trusting host verifier
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
    }*/

    public void setRequestListener(RequestListener mListener) {
        this.mListener = mListener;
    }

    protected void initRequestParams(Map<String, Object> param) {
        // --------------------------智慧流请求固定格式------------------------------
        Context context = OauthApplicationLike.getOauthApplicationContext();
        RequestParams rp = new RequestParams();
        // 头部参数：userAgent、token
        // rp.addHeaderParameter("User-Agent", UserAgentUtils.get(context));
        // json请求
        String paramString = null;
        try {
            paramString = VerityUtil.sortMap(param, OauthApplicationLike.getUserId(), mResult.getSecret());
            cacheKey = getUrl() + OauthApplicationLike.getUserId() + param.get(VerityUtil.VALIDATE_KEY);
        } catch (HttpException e) {
            e.printStackTrace();
        }
        if (AesEncryptUtils.isNeedEncrypt(paramString)) {
            //checkmsgcodeencrypted这个验证码检测接口特殊处理，避免参数明文，因此采用aes加密
            try {
                rp.addBodyData(AesEncryptUtils.getRequestBodyAfterAesEncrypt(paramString).getBytes());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            rp.addBodyData(paramString.getBytes());
        }
        // 设置请求参数
        super.setRequestParams(rp);
        // 设置请求超时
        setRetryPolicy(new DefaultRetryPolicy(20000, 0, 1f)); // 20秒超时,不重试
    }


    protected void setParamsInside(RequestParams params) {
        super.setRequestParams(params);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Context context = OauthApplicationLike.getOauthApplicationContext();
        Map<String, String> headers = new HashMap<>(super.getHeaders());
        TokenEntity accessTokenEntity = null;
        try {
            accessTokenEntity = OAuthApi.getInstance(context).getValidToken(OauthApplicationLike.getUserId(), mResult);
        } catch (DbException e) {
            e.printStackTrace();
        }
        if (accessTokenEntity != null) {
            headers.put("Authorization", Tools.getAccessToken(accessTokenEntity.access_token));
        }
        headers.put("Accept", "application/json");
        headers.put("Connection", "Keep-Alive");
        headers.put("Charset", "UTF-8");
        // 异步处理，子线程报错不影响主线程
        String agent = UserAgentUtils.get(context);
        headers.put("User-Agent", agent);
        return headers;
    }

    @Override
    protected RequestParams getRequestParams() {
        return super.getRequestParams();
    }

    public Map<String, Object> getParams() {
        return param;
    }

    @Override
    public void setRequestParams(RequestParams requestParams) { // 智慧流json请求、不允许自定义属性参数
        throw new RuntimeException("不允许重复设置请求参数");
    }

    private String getStringParams(){
        StringBuilder result = new StringBuilder();
        if (param!=null && !param.isEmpty()) {
            for (Map.Entry<String, Object> entry : param.entrySet()) {
                if (result.length() > 0)
                    result.append(" & ");

                result.append(entry.getKey());
                result.append("=");
                if (entry.getValue() == null) {
                    result.append("null");
                }else {
                    result.append(entry.getValue().toString());
                }
            }
        }
        return result.toString();
    }
    @Override
    protected void deliverResponse(String response) {
//        try {
//            if (!BuildConfig.IS_RELEASE) {
//                LogUtils.e(LogUtils.TAG,getUrl()+"\n"+getStringParams()+"\n"+response);
//            }
//        } catch (Exception e) {
//        }
        try {
            if (!VerityUtil.validateResponse(response, OauthApplicationLike.getUserId(), this.mResult.getSecret())) {
                String msg = "返回数据不合法";
                MLog.e("msg", msg);
                // 失败回调
                if (mListener != null) {
                    mListener.onFailure(this, msg);
                }
                if(getErrorListener() != null) getErrorListener().onErrorResponse(new VolleyError(msg));
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (mListener != null) {
                mListener.onFailure(this, "验证失败，请重试");
            }
            if(getErrorListener() != null) getErrorListener().onErrorResponse(new VolleyError("验证失败，请重试"));
        }
        if (null != mResult) {
            try {
                mResult.setRawContent(response);
                if (mResult.getCode() == 400000) {
                    mResult.setMsg("数据请求失败，请重试！");
                    MLog.e("msg", "数据请求失败，请重试！");
                }

                if (preprocessDataListener != null) {
                    preprocessDataListener.preprocessResultData(mResult);
                }
                if (mListener != null) {
                    mListener.onSuccess(this, mResult);
                }
                if(responseListener != null )responseListener.onResponse(mResult);
            } catch (JSONException e) {
                e.printStackTrace();
//                String one = "1,"+getUrl()+"\n"+getStringParams()+"\n"+response;
//                Log.e("zhl",one);
                String msg = "返回数据无法解析！";
                MLog.e("msg", msg);
                if (mListener != null) {
                    mListener.onFailure(this, msg);
                }
                if(getErrorListener() != null) getErrorListener().onErrorResponse(new VolleyError(msg));
            } catch (Exception ex) {
                ex.printStackTrace();
//                String two = "2,"+getUrl()+"\n"+getStringParams()+"\n"+response;
//                Log.e("zhl",two);
                String msg = "返回数据无法解析！";
                MLog.e("msg", msg);
                if (mListener != null) {
                    mListener.onFailure(this, msg);
                }
                if(getErrorListener() != null) getErrorListener().onErrorResponse(new VolleyError(msg));
            }
        }
    }

    @Override
    public void deliverError(VolleyError error) {
        String msg = "";
        if (error instanceof TimeoutError) {
            // 连接超时设置
            msg = "网络连接超时";
        } else if (error instanceof NetworkError) {
            msg = "网络请求失败，请检查您的网络设置";
        } else if (error instanceof ServerError || error instanceof AuthFailureError) {
            String response = null;
            // 服务器错误
            if (error.networkResponse != null && error.networkResponse.data != null) {
                // 接口权限和未登录异常处理
                try {
                    response = this.getResponse(error.networkResponse.data);
                    int code = VerityUtil.checkTokenValid(response);
                    if (OauthErrorEvent.findErrorEventByCode(code) != null) {
                        EventBus.getDefault().post(OauthErrorEvent.findErrorEventByCode(code));
                    }

                    // 包装错误信息
                    if (null != mResult) {
                        mResult.setR(false);
                        mResult.setCode(1);
                        JSONObject jsonObject = new JSONObject(response);
                        msg = jsonObject.optString("msg", "数据请求失败，请重试！");
//                        if (code > 400000 && code < 400008 || msg.contains("重新登录") || msg.contains("未登录")) {
//                            Context oauthApplicationContext = OauthApplicationLike.getOauthApplicationContext();
//                            Intent intent = new Intent("com.zhl.xxxx.aphone.action.login");
//                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                            oauthApplicationContext.startActivity(intent);
//                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    msg = "数据请求失败，请重试！";
                }
            } else {
                msg = "数据请求失败，请重试！";
            }
        } else {
            msg = error.getMessage();
            if (TextUtils.isEmpty(msg)) {
                msg = "数据请求失败，请重试！";
            }
        }
        Log.e("msg", msg);
        // 错误处理，不调用error
        if (mListener != null) {
            mListener.onFailure(this, msg);
        }
        if(getErrorListener() != null) getErrorListener().onErrorResponse(new VolleyError(msg));
    }

    @Override
    public boolean isCanceled() {
        addMarker("isCancled" + super.isCanceled());
        return super.isCanceled();
    }

    @Override
    public void cancel() {
        addMarker("cancel");
        super.cancel();
    }

    @Override
    public String getCacheKey() {
        return cacheKey;
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        String responseStr;
        try {
            responseStr = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
        } catch (UnsupportedEncodingException e) {
            responseStr = new String(response.data);
        }
        if (getCachePolicy().getCacheTime() != 0) {
            addMarker("write cacheEntity");
            Cache.Entry entry = new Cache.Entry();
            long time = System.currentTimeMillis();
            entry.data = responseStr.getBytes();
            entry.softTtl = getCachePolicy().getCacheTime() + time;
            entry.ttl = getCachePolicy().getCacheTime() + time;
            entry.serverDate = time;
            entry.lastModified = time;
            entry.responseHeaders = response.headers;
            return Response.success(responseStr, entry);
        } else {
            return Response.success(responseStr, HttpHeaderParser.parseCacheHeaders(response));
        }
    }

    @Override
    public String getBodyContentType() {
        return "application/json; charset=utf-8";
    }

    protected String getBodyContentTypeInside() {
        return super.getBodyContentType();
    }

    private String getResponse(byte[] bytes) throws IOException {
        return new String(bytes, "utf-8");
    }

    public int getRequestType() {
        return requestType;
    }

    public void setRequestType(int type) {
        this.requestType = type;
    }

    public PreprocessDataListener getPreprocessDataListener() {
        return preprocessDataListener;
    }

    public void setPreprocessDataListener(PreprocessDataListener preprocessDataListener) {
        this.preprocessDataListener = preprocessDataListener;
    }


    @Override
    public void finish(final String tag) {
        super.finish(tag);
        this.mListener = null;
        this.mResult = null;
    }

    /**
     * 返回结果预处理函数
     *
     * @return
     */
    public interface PreprocessDataListener {
        public void preprocessResultData(AbsResult result);
    }
    private Response.Listener responseListener;

    public void setResponseListener(Response.Listener responseListener) {
        this.responseListener = responseListener;
    }
}