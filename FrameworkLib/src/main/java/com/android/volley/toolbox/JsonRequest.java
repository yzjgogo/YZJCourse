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

package com.android.volley.toolbox;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestParams;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;

import java.io.UnsupportedEncodingException;

/**
 * A request for retrieving a T type response body at a given URL that also
 * optionally sends along a JSON body in the request specified.
 *
 * @param <T> JSON type of response expected
 */
public abstract class JsonRequest<T> extends Request<T> {
    /** Default charset for JSON request. */
    protected static final String PROTOCOL_CHARSET = "utf-8";

    /** Content type for request. */
    private static final String PROTOCOL_CONTENT_TYPE = String.format("application/json; charset=%s", PROTOCOL_CHARSET);

    private final Listener<T> mListener;

    private String requestBody;

    public JsonRequest(String url, String requestBody, Listener<T> listener, ErrorListener errorListener) {
        this(Method.POST, url, requestBody, listener, errorListener);
    }

    public JsonRequest(int method, String url, String requestBody, Listener<T> listener, ErrorListener errorListener) {
        super(method, url, errorListener);
        mListener = listener;
        this.requestBody = requestBody;
    }

    @Override
    public void setRequestParams(RequestParams requestParams) {
        super.setRequestParams(requestParams);
        if (requestParams != null && requestBody != null) {
            try {
                requestParams.addBodyData(requestBody.getBytes(PROTOCOL_CHARSET)); // json request 直接通过byte[]传输
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }

    @Override
    abstract protected Response<T> parseNetworkResponse(NetworkResponse response);

    @Override
    public String getBodyContentType() {
        return PROTOCOL_CONTENT_TYPE;
    }
}
