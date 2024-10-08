/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.volley;

import android.net.TrafficStats;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import com.android.volley.VolleyLog.MarkerLog;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Base class for all network requests.
 *
 * @param <T> The type of parsed response this request expects.
 */
public abstract class Request<T> implements Comparable<Request<T>> {

    /**
     * Default encoding for POST or PUT parameters. See {@link #getParamsEncoding()}.
     */
    private static final String DEFAULT_PARAMS_ENCODING = "UTF-8";
    /** An event log tracing the lifetime of this request; for debugging. */
    private final MarkerLog mEventLog = MarkerLog.ENABLED ? new MarkerLog() : null;
    /**
     * Request method of this request. Currently supports GET, POST, PUT, DELETE, HEAD, OPTIONS,
     * TRACE, and PATCH.
     */
    private final int mMethod;
    /** URL of this request. */
    private final String mUrl;
    /** Default tag for {@link TrafficStats}. */
    private final int mDefaultTrafficStatsTag;
    /** Listener interface for errors. */
    private  Response.ErrorListener mErrorListener;
    /** Sequence number of this request, used to enforce FIFO ordering. */
    private Integer mSequence;
    /** The request queue this request is associated with. */
    private RequestQueue mRequestQueue;
    /** Whether or not responses to this request should be cached. */
    private boolean mShouldCache = true;
    /** Whether or not this request has been canceled. */
    private boolean mCanceled = false;
    /** Whether or not a response has been delivered for this request yet. */
    private boolean mResponseDelivered = false;
    /** Whether the request should be retried in the event of an HTTP 5xx (server) error. */
    private boolean mShouldRetryServerErrors = false;
    /** The retry policy for this request. */
    private RetryPolicy mRetryPolicy;
    /**
     * When a request can be retrieved from cache but must be refreshed from
     * the network, the cache entry will be stored here so that in the event of
     * a "Not Modified" response, we can be sure it hasn't been evicted from cache.
     */
    private Cache.Entry mCacheEntry = null;
    /** An opaque token tagging this request; used for bulk cancellation. */
    private Object mTag;
    /** 下载、上传loading进度的实现 */
    private Response.LoadingListener mLoadingListener;
    private ResponseDelivery mResponseDelivery;
    /** 请求的参数信息 */
    private RequestParams mRequestParams;
    /** 缓存策略 */
    private CachePolicy mCachePolicy = CachePolicy.DefaultCachePolicy;
    private HttpEntity mBody;

    public void setMustRefresh(boolean mustRefresh) {
        this.mustRefresh = mustRefresh;
    }

    public boolean isMustRefresh() {
        return mustRefresh;
    }

    /**
     * 是否强制刷新
     */
    private boolean mustRefresh = false;


    /**
     * Creates a new request with the given URL and error listener. Note that
     * the normal response listener is not provided here as delivery of responses
     * is provided by subclasses, who have a better idea of how to deliver an
     * already-parsed response.
     *
     * @deprecated Use {@link #Request(int, String, com.android.volley.Response.ErrorListener)}.
     */
    @Deprecated
    public Request(String url, Response.ErrorListener listener) {
        this(Method.DEPRECATED_GET_OR_POST, url, listener);
    }

    /**
     * Creates a new request with the given method (one of the values from {@link Method}),
     * URL, and error listener. Note that the normal response listener is not provided here as
     * delivery of responses is provided by subclasses, who have a better idea of how to deliver
     * an already-parsed response.
     */
    public Request(int method, String url, Response.ErrorListener listener) {
        mMethod = method;
        mUrl = url;
        mErrorListener = listener;
        setRetryPolicy(new DefaultRetryPolicy());

        mDefaultTrafficStatsTag = findDefaultTrafficStatsTag(url);
    }

    /**
     * @return The hashcode of the URL's host component, or 0 if there is none.
     */
    private static int findDefaultTrafficStatsTag(String url) {
        if (!TextUtils.isEmpty(url)) {
            Uri uri = Uri.parse(url);
            if (uri != null) {
                String host = uri.getHost();
                if (host != null) {
                    return host.hashCode();
                }
            }
        }
        return 0;
    }

    /**
     * Return the method for this request. Can be one of the values in {@link Method}.
     */
    public int getMethod() {
        return mMethod;
    }

    /**
     * 设置加载进度监听
     *
     * @param loadingListener
     * @return
     * @author zjh
     * @createTime 2016-3-29 下午5:30:23
     */
    public Request<?> setLoadingListener(Response.LoadingListener loadingListener) {
        mLoadingListener = loadingListener;
        return this;
    }

    /**
     * 设置请求下发类
     *
     * @param delivery
     * @return
     * @author zjh
     * @createTime 2016-3-29 下午5:47:27
     */
    public Request<?> setResponseDelivery(ResponseDelivery delivery) {
        mResponseDelivery = delivery;
        return this;
    }

    /**
     * Returns this request's tag.
     *
     * @see Request#setTag(Object)
     */
    public Object getTag() {
        return mTag;
    }

    /**
     * Set a tag on this request. Can be used to cancel all requests with this
     * tag by {@link RequestQueue#cancelAll(Object)}.
     *
     * @return This Request object to allow for chaining.
     */
    public Request<?> setTag(Object tag) {
        mTag = tag;
        return this;
    }

    /**
     * 获取缓存策略
     *
     * @return
     */
    public CachePolicy getCachePolicy() {
        return mCachePolicy;
    }

    /**
     * 设置缓存策略
     *
     * @param mCachePolicy
     */
    public void setCachePolicy(CachePolicy mCachePolicy) {
        this.mCachePolicy = mCachePolicy;
    }

    /**
     * @return this request's {@link com.android.volley.Response.ErrorListener}.
     */
    public Response.ErrorListener getErrorListener() {
        return mErrorListener;
    }

    public void setErrorListener(Response.ErrorListener mErrorListener) {
        this.mErrorListener = mErrorListener;
    }
    /**
     * @return A tag for use with {@link TrafficStats#setThreadStatsTag(int)}
     */
    public int getTrafficStatsTag() {
        return mDefaultTrafficStatsTag;
    }

    /**
     * Adds an event to this request's event log; for debugging.
     */
    public void addMarker(String tag) {
        if (MarkerLog.ENABLED) {
            mEventLog.add(tag, Thread.currentThread().getId());
        }
    }

    /**
     * Notifies the request queue that this request has finished (successfully or with error).
     * <p>
     * <p>
     * Also dumps all events from this request's event log; for debugging.
     * </p>
     */
    public void finish(final String tag) {
        if (mRequestQueue != null) {
            mRequestQueue.finish(this);
            //释放 requestQueue
            mRequestQueue = null;
            mResponseDelivery = null;
            mLoadingListener = null;
        }
        if (MarkerLog.ENABLED) {
            final long threadId = Thread.currentThread().getId();
            if (Looper.myLooper() != Looper.getMainLooper()) {
                // If we finish marking off of the main thread, we need to
                // actually do it on the main thread to ensure correct ordering.
                Handler mainThread = new Handler(Looper.getMainLooper());
                mainThread.post(new Runnable() {
                    @Override
                    public void run() {
                        mEventLog.add(tag, threadId);
                        mEventLog.finish(this.toString());
                    }
                });
                return;
            }

            mEventLog.add(tag, threadId);
            mEventLog.finish(this.toString());
        }
    }

    /**
     * Associates this request with the given queue. The request queue will be notified when this
     * request has finished.
     *
     * @return This Request object to allow for chaining.
     */
    public Request<?> setRequestQueue(RequestQueue requestQueue) {
        mRequestQueue = requestQueue;
        return this;
    }

    /**
     * Returns the sequence number of this request.
     */
    public final int getSequence() {
        if (mSequence == null) {
            throw new IllegalStateException("getSequence called before setSequence");
        }
        return mSequence;
    }

    /**
     * Sets the sequence number of this request. Used by {@link RequestQueue}.
     *
     * @return This Request object to allow for chaining.
     */
    public final Request<?> setSequence(int sequence) {
        mSequence = sequence;
        return this;
    }

    /**
     * Returns the URL of this request.
     */
    public String getUrl() {
        return mUrl;
    }

    /**
     * Returns the cache key for this request. By default, this is the URL.
     */
    public String getCacheKey() {
        return getUrl();
    }

    /**
     * Returns the annotated cache entry, or null if there isn't one.
     */
    public Cache.Entry getCacheEntry() {
        return mCacheEntry;
    }

    /**
     * Annotates this request with an entry retrieved for it from cache.
     * Used for cache coherency support.
     *
     * @return This Request object to allow for chaining.
     */
    public Request<?> setCacheEntry(Cache.Entry entry) {
        mCacheEntry = entry;
        return this;
    }

    /**
     * Mark this request as canceled. No callback will be delivered.
     */
    public void cancel() {
        mCanceled = true;
    }

    /**
     * Returns true if this request has been canceled.
     */
    public boolean isCanceled() {
        return mCanceled;
    }

    /**
     * Returns a list of extra HTTP headers to go along with this request. Can
     * throw {@link AuthFailureError} as authentication may be required to
     * provide these values.
     *
     * @throws AuthFailureError In the event of auth failure
     */
    public Map<String, String> getHeaders() throws AuthFailureError {
        // 添加
        if (mRequestParams != null && mRequestParams.getHeadersParams() != null) {
            return mRequestParams.getHeadersParams();
        }
        return Collections.emptyMap();
    }

    /**
     * Returns a Map of parameters to be used for a POST or PUT request.
     * <p>
     * Note that you can directly override {@link #getBody()} for custom data.
     * </p>
     */
    protected RequestParams getRequestParams() {
        return mRequestParams;
    }

    public void setRequestParams(RequestParams requestParams) {
        mRequestParams = requestParams;

        RequestParams params = getRequestParams();
        if (params != null) {
            Map<String, RequestParams.FileContent> filesParams = params.getFilesParams();
            if (filesParams != null && !filesParams.isEmpty()) {
                setRetryPolicy(new DefaultRetryPolicy(20000, 0, 1f));
            }
        }
    }

    /**
     * Returns which encoding should be used when converting POST or PUT parameters returned by
     * <p>
     * <p>
     * This controls both encodings:
     * <ol>
     * <li>The string encoding used when converting parameter names and values into bytes prior
     * to URL encoding them.</li>
     * <li>The string encoding used when converting the URL encoded parameters into a raw
     * byte array.</li>
     * </ol>
     */
    protected String getParamsEncoding() {
        return DEFAULT_PARAMS_ENCODING;
    }

    /**
     * Returns the content type of the POST or PUT body.
     */
    public String getBodyContentType() {
        createHttpEntityIfNeed();
        if (mBody != null && mBody.getContentType() != null) {
            return mBody.getContentType().getValue();
        }
        return "application/x-www-form-urlencoded; charset=" + getParamsEncoding();
    }

    /**
     * Converts <code>params</code> into an application/x-www-form-urlencoded encoded string.
     */
    @Deprecated
    private byte[] encodeParameters(Map<String, String> params, String paramsEncoding) {
        StringBuilder encodedParams = new StringBuilder();
        try {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                encodedParams.append(URLEncoder.encode(entry.getKey(), paramsEncoding));
                encodedParams.append('=');
                encodedParams.append(URLEncoder.encode(entry.getValue(), paramsEncoding));
                encodedParams.append('&');
            }
            return encodedParams.toString().getBytes(paramsEncoding);
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("Encoding not supported: " + paramsEncoding, uee);
        }
    }

    /**
     * Returns the raw POST or PUT body to be sent.
     * <p>
     * <p>
     * By default, the body consists of the request parameters in
     * application/x-www-form-urlencoded format. When overriding this method, consider overriding
     * {@link #getBodyContentType()} as well to match the new body format.
     *
     * @throws AuthFailureError in the event of auth failure
     */
    public HttpEntity getBody() throws AuthFailureError {
        createHttpEntityIfNeed();
        return mBody;
    }

    private HttpEntity createHttpEntityIfNeed() {
        if (mBody != null) {
            return mBody;
        }

        RequestParams params = getRequestParams();
        if (params != null) {
            Map<String, RequestParams.StringContent> stringsParams = params.getStringsParams();
            Map<String, RequestParams.FileContent> filesParams = params.getFilesParams();
            byte[] bodyData = params.getBodyData();

            if (filesParams != null && !filesParams.isEmpty()) { // form post file
                SimpleMultipartEntity multipartEntity = new SimpleMultipartEntity(this);

                if (stringsParams != null) {
                    for (String key : stringsParams.keySet()) {
                        RequestParams.StringContent stringContent = stringsParams.get(key);
                        multipartEntity.addPartWithCharset(key, stringContent.getValue(), stringContent.getCharset());
                    }
                }

                for (String key : filesParams.keySet()) {
                    RequestParams.FileContent fileContent = filesParams.get(key);
                    multipartEntity.addPart(key, fileContent.getFile(), fileContent.getMimeType());
                }
                mBody = multipartEntity;
            } else if (bodyData != null) { // 自定义byte[]
                mBody = new ByteArrayEntity(bodyData);
            } else if (stringsParams != null && !stringsParams.isEmpty()) { // form post value
                try {
                    mBody = new UrlEncodedFormEntity(buildParams(stringsParams), getParamsEncoding());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private List<NameValuePair> buildParams(Map<String, RequestParams.StringContent> postParams) {
        List<NameValuePair> result = new ArrayList<NameValuePair>(postParams.size());
        for (String key : postParams.keySet()) {
            result.add(new BasicNameValuePair(key, postParams.get(key).getValue()));
        }
        return result;
    }

    /**
     * Set whether or not responses to this request should be cached.
     *
     * @return This Request object to allow for chaining.
     */
    public final Request<?> setShouldCache(boolean shouldCache) {
        mShouldCache = shouldCache;
        return this;
    }

    /**
     * Returns true if responses to this request should be cached.
     */
    public final boolean shouldCache() {
        return mShouldCache;
    }

    /**
     * Sets whether or not the request should be retried in the event of an HTTP 5xx (server) error.
     *
     * @return This Request object to allow for chaining.
     */
    public final Request<?> setShouldRetryServerErrors(boolean shouldRetryServerErrors) {
        mShouldRetryServerErrors = shouldRetryServerErrors;
        return this;
    }

    /**
     * Returns true if this request should be retried in the event of an HTTP 5xx (server) error.
     */
    public final boolean shouldRetryServerErrors() {
        return mShouldRetryServerErrors;
    }

    /**
     * Returns the {@link Priority} of this request; {@link Priority#NORMAL} by default.
     */
    public Priority getPriority() {
        return Priority.NORMAL;
    }

    /**
     * Returns the socket timeout in milliseconds per retry attempt. (This value can be changed
     * per retry attempt if a backoff is specified via backoffTimeout()). If there are no retry
     * attempts remaining, this will cause delivery of a {@link TimeoutError} error.
     */
    public final int getTimeoutMs() {
        return mRetryPolicy.getCurrentTimeout();
    }

    /**
     * Returns the retry policy that should be used for this request.
     */
    public RetryPolicy getRetryPolicy() {
        return mRetryPolicy;
    }

    /**
     * Sets the retry policy for this request.
     *
     * @return This Request object to allow for chaining.
     */
    public Request<?> setRetryPolicy(RetryPolicy retryPolicy) {
        mRetryPolicy = retryPolicy;
        return this;
    }

    /**
     * Mark this request as having a response delivered on it. This can be used
     * later in the request's lifetime for suppressing identical responses.
     */
    public void markDelivered() {
        mResponseDelivered = true;
    }

    /**
     * Returns true if this request has had a response delivered for it.
     */
    public boolean hasHadResponseDelivered() {
        return mResponseDelivered;
    }

    /**
     * Subclasses must implement this to parse the raw network response
     * and return an appropriate response type. This method will be
     * called from a worker thread. The response will not be delivered
     * if you return null.
     *
     * @param response Response from the network
     * @return The parsed response, or null in the case of an error
     */
    abstract protected Response<T> parseNetworkResponse(NetworkResponse response);

    /**
     * Subclasses can override this method to parse 'networkError' and return a more specific error.
     * <p>
     * <p>
     * The default implementation just returns the passed 'networkError'.
     * </p>
     *
     * @param volleyError the error retrieved from the network
     * @return an NetworkError augmented with additional information
     */
    protected VolleyError parseNetworkError(VolleyError volleyError) {
        return volleyError;
    }

    /**
     * Subclasses must implement this to perform delivery of the parsed
     * response to their listeners. The given response is guaranteed to
     * be non-null; responses that fail to parse are not delivered.
     *
     * @param response The parsed response returned by
     * {@link #parseNetworkResponse(NetworkResponse)}
     */
    abstract protected void deliverResponse(T response);

    /**
     * override this to prevent default function: entity to bytes
     *
     * @param httpResponse
     * @return byte[] return null if you do not handle this
     */
    public byte[] handleRawResponse(HttpResponse httpResponse) throws IOException, ServerError, CanceledError {
        return null;
    }

    /**
     * Delivers error message to the ErrorListener that the Request was
     * initialized with.
     *
     * @param error Error details
     */
    public void deliverError(VolleyError error) {
        if (mErrorListener != null) {
            mErrorListener.onErrorResponse(error);
        }
    }

    protected void deliverProgress(boolean isUpload, long current, long total) {
        if (mLoadingListener != null) {
            mLoadingListener.onLoading(isUpload, total, current);
        }
    }

    public void postProgress(boolean isUpload, long current, long total) {
        if (mLoadingListener != null && mResponseDelivery != null) {
            mResponseDelivery.postProgress(this, isUpload, current, total);
        }
    }

    /**
     * Our comparator sorts from high to low priority, and secondarily by
     * sequence number to provide FIFO ordering.
     */
    @Override
    public int compareTo(Request<T> other) {
        Priority left = this.getPriority();
        Priority right = other.getPriority();

        // High-priority requests are "lesser" so they are sorted to the front.
        // Equal priorities are sorted by sequence number to provide FIFO ordering.
        return left == right ? this.mSequence - other.mSequence : right.ordinal() - left.ordinal();
    }

    @Override
    public String toString() {
        String trafficStatsTag = "0x" + Integer.toHexString(getTrafficStatsTag());
        return (mCanceled ? "[X] " : "[ ] ") + getUrl() + " " + trafficStatsTag + " " + getPriority() + " " + mSequence;
    }

    public void log(String tag, String msg, Throwable throwable) {
        VolleyLog.e(throwable, "%s:%s", tag, msg);
    }

    /**
     * Priority values. Requests will be processed from higher priorities to
     * lower priorities, in FIFO order.
     */
    public enum Priority {
        LOW, NORMAL, HIGH, IMMEDIATE
    }

    /**
     * Supported request methods.
     */
    public interface Method {
        int DEPRECATED_GET_OR_POST = -1;
        int GET = 0;
        int POST = 1;
        int PUT = 2;
        int DELETE = 3;
        int HEAD = 4;
        int OPTIONS = 5;
        int TRACE = 6;
        int PATCH = 7;
    }
}
