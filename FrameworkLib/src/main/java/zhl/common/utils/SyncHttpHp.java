package zhl.common.utils;

import android.content.Context;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import zhl.common.oauth.FrameResult;

public class SyncHttpHp {

    // 用大写
    public static final String METHOD_POST = "POST";
    public static final String METHOD_GET = "GET";
    public static final String METHOD_PUT = "PUT";
    public static final String METHOD_DELETE = "DELETE";
    private static final int TIME_OUT = 1000 * 8; // 超时5秒
    private Context context;
    // 是否需要记录用户http异常信息
    private Boolean isNeedCrashSubmit = true;

    public SyncHttpHp(Context context) {
        this.context = context;
    }

    /**
     * 为ture时，必须传入当前上下文环境
     *
     * @param isNeedCrashSubmit
     * @throws Exception
     */
    public void setIsNeedCrashSubmit(Boolean isNeedCrashSubmit) throws Exception {
        if (isNeedCrashSubmit && context == null)
            throw new Exception("context  can not be null");
        this.isNeedCrashSubmit = isNeedCrashSubmit;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    /**
     * 模拟 Http请求 传入String类型时，对流进行处理
     *
     * @param url 请求地址，已/结尾
     * @param params 请求参数
     * @param method 请求方法
     * @param header 请求头信息
     */
    public FrameResult<String> http(String url, HashMap<String, Object> params, String method, HashMap<String, String> header) {
        return http(url, params, method, header, null);
    }

    /**
     * 模拟 Http请求 传入String类型时，对流进行处理
     *
     * @param url 请求地址，已/结尾
     * @param paraString 请求参数
     * @param method 请求方法
     * @param header 请求头信息
     */
    public FrameResult<String> http(String url, String paraString, String method, HashMap<String, String> header) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        return http(url, params, method, header, paraString);
    }

    /**
     * 模拟 Http请求 传入String类型时，对流进行处理
     *
     * @param url 请求地址，已/结尾
     * @param params 请求参数
     * @param method 请求方法
     * @param header 请求头信息
     * @param paramsStr 请求参数
     */
    public FrameResult<String> http(String url, HashMap<String, Object> params, String method, HashMap<String, String> header, String paramsStr) {
        if (header == null)
            header = new HashMap<String, String>();
        String paraString = "";

        if (method.equals(METHOD_GET)) {
            if (paramsStr != null && paramsStr.length() > 0)
                paraString = paramsStr;
            else
                paraString = getParams(params, true);
            url += "?" + paraString;
            header.put("accept", "*/*");
            return http(url, "", method, header, "");
        } else {
            if (paramsStr != null && paramsStr.length() > 0)
                paraString = paramsStr;
            else
                paraString = getParams(params, false);
            byte[] data = paraString.getBytes();
            header.put("Accept", "application/json");
            header.put("Connection", "Keep-Alive");
            header.put("Charset", "UTF-8");
            header.put("Content-Length", String.valueOf(data.length));
            header.put("Content-Type", "application/json");
            return http(url, paraString, method, header, "");
        }
    }

    /**
     * 模拟 Http请求 传入String类型时，对流进行处理
     *
     * @param url 请求地址，已/结尾
     * @param params 请求参数
     * @param method 请求方法
     * @param header 请求头信息
     */
    public FrameResult<InputStream> httpStream(String url, HashMap<String, Object> params, String method, HashMap<String, String> header) {
        if (header == null)
            header = new HashMap<String, String>();

        InputStream iStream = new InputStream() {
            public int read() throws IOException {
                return 0;
            }
        };
        if (method.equals(METHOD_GET)) {
            String paraString = getParams(params, true);
            url += "?" + paraString;
            header.put("accept", "*/*");
            return http(url, "", method, header, iStream);
        } else {
            String paraString = getParams(params, false);
            byte[] data = paraString.getBytes();
            header.put("Accept", "application/json");
            header.put("Connection", "Keep-Alive");
            header.put("Charset", "UTF-8");
            header.put("Content-Length", String.valueOf(data.length));
            header.put("Content-Type", "application/json");
            return http(url, paraString, method, header, iStream);
        }
    }

    @SuppressWarnings("unchecked")
    /**
     * 超时状态吗：600,程序异常：700，无网络连接：800，其他按httpcode
     */
    private <T> FrameResult<T> http(String urlStr, String params, String method, HashMap<String, String> header, T tclass) {
        FrameResult<T> r = new FrameResult<T>();
        r.setR(true);
        URL url = null;
        HttpURLConnection conn = null;
        InputStream inStream = null;
        String response = null;
        try {
            url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(TIME_OUT);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod(method);
            // 添加请求头信息
            addHeader(conn, header);
            if (params.length() > 0) {
                conn.setDoOutput(true);
            }
            conn.connect();
            // 请求主体不为空，传入请求主体
            addContent(conn, params);
            int responseCode = conn.getResponseCode();
            if (responseCode == conn.HTTP_OK) {
                inStream = conn.getInputStream();
                if (tclass instanceof String) {
                    response = getResponse(inStream);
                    r.setT((T) response);
                } else {
                    r.setT((T) inStream);
                }
                r.setCode(responseCode);
            } else {
                r.setR(false);
                r.setMsg(String.valueOf(responseCode));
                r.setCode(responseCode);
                // 网络超时捕获
                // if (isNeedCrashSubmit)
                // Umeng.reportErrorHttp(null, conn, header, params, context);
            }
        } catch (ConnectException e) {
            e.printStackTrace();
            r.setR(false);
            r.setMsg("connect time out:" + e.getMessage());
            r.setCode(600);
        } catch (SocketTimeoutException ex) {
            ex.printStackTrace();
            r.setR(false);
            r.setMsg("socket time out:" + ex.getMessage());
            r.setCode(600);
        } catch (IOException exio) {
            exio.printStackTrace();

            r.setR(false);
            r.setMsg("IOException:" + exio.getMessage());
            r.setCode(700);
            // 网络超时捕获
            if (exio != null && exio.getMessage() != null) {
                if (exio.getMessage().toLowerCase().trim().equals("connection timed out"))
                    r.setCode(600);
            }
            // 异常提报
            // if (r.getcode() != 600 && isNeedCrashSubmit)
            // Umeng.reportErrorHttp(exio, conn, header, params, context);
        } catch (Exception exc) {
            exc.printStackTrace();
            r.setR(false);
            r.setMsg("Exception:" + exc.getMessage());
            r.setCode(700);
            // 异常提报
            // if (isNeedCrashSubmit)
            // Umeng.reportErrorHttp(exc, conn, header, params, context);
        } finally {
            if (conn != null)
                conn.disconnect();
            // 打印请求信息
            MLog.i("url", urlStr);
            if (header != null && header.size() > 0)
                MLog.i("url", header.toString());
            if (params != null && !params.equals(""))
                MLog.i("url", params);
            MLog.i("url", r.getR() + "/" + r.getMsg());
            if (r.getT() != null)
                MLog.i("url", r.getT() == null ? "" : r.getT().toString());

        }
        return r;
    }

    private void addContent(HttpURLConnection conn, String params) throws IOException {
        if (params != null && params != "" && conn != null) {

            byte[] data = params.getBytes();
            DataOutputStream outputStream = new DataOutputStream(conn.getOutputStream());
            outputStream.write(data);
            outputStream.flush();
            outputStream.close();
        }
    }

    private String getParams(HashMap<String, Object> params, Boolean encode) {
        String paramsStr = "";
        if (params != null && params.size() > 0) {
            paramsStr = JsonHp.getGsonConverter().toJson(params);
        }
        return paramsStr;
    }

    private void addHeader(HttpURLConnection conn, HashMap<String, String> header) {

        if (conn != null && header != null && header.size() > 0) {
            for (Iterator<Entry<String, String>> it = header.entrySet().iterator(); it.hasNext(); ) {
                Map.Entry<String, String> mEntry = (Entry<String, String>) it.next();
                conn.setRequestProperty(mEntry.getKey(), mEntry.getValue());
            }
        }
    }

    private String getResponse(InputStream inStream) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        int len = -1;
        byte[] buffer = new byte[1024];
        while ((len = inStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, len);
        }

        byte[] data = outputStream.toByteArray();
        return new String(data, "utf-8");
    }
}
