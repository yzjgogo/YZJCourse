/*
 * Copyright (c) 2013. wyouflf (wyouflf@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lidroid.xutils.exception;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

import zhl.common.utils.Tools;

public class PocHttpException extends HttpException {

    public PocHttpException(Throwable throwable) {
        super(getPocMsg("", throwable), throwable);
    }

    public PocHttpException(String detailMessage, Throwable throwable) {
        super(getPocMsg(detailMessage, throwable), throwable);
    }

    private static String getPocMsg(String detailMessage, Throwable throwable) {
        if (Tools.isEmpty(detailMessage)) {
            if (throwable instanceof ConnectException) {
                detailMessage = "网络连接超时";
            } else if (throwable instanceof SocketTimeoutException) {
                detailMessage = "网络连接超时";
            } else if (throwable instanceof IOException) {
                if (throwable.getMessage() != null) {
                    if (throwable.getMessage().toLowerCase().trim().startsWith("connection timed out"))
                        detailMessage = "网络连接超时";
                }
            } else {
                detailMessage = throwable.getMessage();
            }
        }

        return detailMessage;
    }
}
