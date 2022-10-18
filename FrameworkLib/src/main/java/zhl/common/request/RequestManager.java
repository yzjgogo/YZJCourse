package zhl.common.request;

import android.content.Context;
import android.text.TextUtils;

import com.android.volley.CachePolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.ServerError;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.Volley;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;

/**
 * Created by zqs on 16/8/19 下午4:59.
 * Copyright © 2016年 BeiJingZhiHuiLiu. All rights reserved.
 */
public class RequestManager {

    private static final String FILE_URL = "file-manage.xxfz.com.cn";
    private static final String PAY_URL = "api-pay.xxfz.com.cn";
    private static final String EDUCATION_URL = "zhl-education.xxfz.com.cn";
    private static RequestQueue mCommonRequestQueue;
    private static RequestQueue mFileRequestQueue;
    private static RequestQueue mPayRequestQueue;
    private static RequestQueue mDefaultRequestQueue;

    // private static Map<String, RequestQueue> requestQueueMap = new HashMap<>();

    private RequestManager() {
    }

    public static void initialize(Context context) {
        if (mDefaultRequestQueue == null) {
            //线程池多而无用，更浪费资源，
//            mCommonRequestQueue = Volley.newRequestQueue(context);
//            mFileRequestQueue = Volley.newRequestQueue(context);
//            mPayRequestQueue = Volley.newRequestQueue(context);
            mDefaultRequestQueue = Volley.newRequestQueue(context);
        }
    }

    public static void execute(Request request) {
      /*  if (TextUtils.isEmpty(request.getUrl())) {
            request.deliverError(new ServerError());
        } else if (request.getUrl().contains(FILE_URL)) {
            mFileRequestQueue.add(request);
        } else if (request.getUrl().contains(PAY_URL)) {
            mPayRequestQueue.add(request);
        } else if (request.getUrl().contains(EDUCATION_URL)) {
            mCommonRequestQueue.add(request);
        } else {
            mDefaultRequestQueue.add(request);
        }*/

        if (TextUtils.isEmpty(request.getUrl())) {
            request.deliverError(new ServerError());
        } else {
            mDefaultRequestQueue.add(request);
        }
    }

    public static void execute(ZHLRequest request, RequestListener listener) {
        request.setRequestListener(listener);
        execute(request);
    }

    public static void execute(ZHLRequest request, RequestListener listener, CachePolicy policy) {
        request.setCachePolicy(policy);
        execute(request, listener);
    }

    public static void registerRequestConfigClass(Class<? extends BasePocConfig> requestTypeClass) {
        BaseRequest.registerRequestConfigClass(requestTypeClass);
    }

    public static void cancleAllByTag(final Object tag) {
   /*     mCommonRequestQueue.cancelAll(new RequestQueue.RequestFilter() {
            @Override
            public boolean apply(Request<?> request) {
                return tag.equals(request.getTag());
            }
        });
        mFileRequestQueue.cancelAll(new RequestQueue.RequestFilter() {
            @Override
            public boolean apply(Request<?> request) {
                return tag.equals(request.getTag());
            }
        });
        mPayRequestQueue.cancelAll(new RequestQueue.RequestFilter() {
            @Override
            public boolean apply(Request<?> request) {
                return tag.equals(request.getTag());
            }
        });*/
        if (mDefaultRequestQueue == null) return;
        mDefaultRequestQueue.cancelAll(new RequestQueue.RequestFilter() {
            @Override
            public boolean apply(Request<?> request) {
                return tag.equals(request.getTag());
            }
        });
    }

    public static void clearCache() {
//        mCommonRequestQueue.getCache().clear();
//        mFileRequestQueue.getCache().clear();
//        mPayRequestQueue.getCache().clear();
        mDefaultRequestQueue.getCache().clear();
    }

    public static Observable<AbsResult> executeRxRequest(final ZHLRequest request) {
        return Observable.defer(new Callable<ObservableSource<? extends AbsResult>>() {
            @Override
            public ObservableSource<AbsResult> call() {
                try {
                    return Observable.just(getRouteData(request));
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                    String msg = e.getMessage();
                    if (msg.contains(":")) {
                        msg = msg.substring(msg.indexOf(":") + 1);
                    }
                    return Observable.error(new Exception(msg));
                }
            }
        });
    }


    private static AbsResult getRouteData(ZHLRequest request) throws ExecutionException, InterruptedException {
        RequestFuture<AbsResult> future = RequestFuture.newFuture();
        request.setErrorListener(future);
        request.setResponseListener(future);
        execute(request);
        return future.get();
    }
}