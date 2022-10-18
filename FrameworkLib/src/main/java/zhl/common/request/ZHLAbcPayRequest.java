package zhl.common.request;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestParams;

import java.io.File;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import zhl.common.oauth.OauthApplicationLike;
import zhl.common.utils.MLog;

/**
 * Created by LXY on 18/8/27 下午2:10.
 *
 */
public class ZHLAbcPayRequest extends ZHLRequest {

    private static final String TAG = "ZHLAbcPayRequest";

    /** ==========================================绕过ssl验证======================================= */
//    static {
//        disableSslVerification();
//    }


    public ZHLAbcPayRequest(String url, Map<String, Object> param, AbsResult result) {
        super(url, param, result);
    }

    /*private static void disableSslVerification() {
        try {
            // Create a trust manager that does not validate certificate chains
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
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
    }
*/
    @Override
    protected void initRequestParams(Map<String, Object> param) {
        MLog.e("initParams", "initRequestParams");
        // --------------------------智慧流请求固定格式------------------------------
        Context context = OauthApplicationLike.getOauthApplicationContext();
        RequestParams rp = new RequestParams();
        // 设置请求参数
        for (String s : param.keySet()) {
            Object value = param.get(s);
            if (value instanceof Double) {
                value  = ((Double) value).intValue();
            }
            rp.addBodyParameter(s, String.valueOf(value));
        }
        super.setParamsInside(rp);
        // 设置请求超时
        setRetryPolicy(new DefaultRetryPolicy(20000, 0, 1f)); // 60秒超时,不重试
    }

    @Override
    public String getCacheKey() {
        return null;
    }

    @Override
    public String getBodyContentType() {
        return super.getBodyContentTypeInside();
    }

    private String getResponse(byte[] bytes) throws IOException {
        return new String(bytes, "utf-8");
    }


}