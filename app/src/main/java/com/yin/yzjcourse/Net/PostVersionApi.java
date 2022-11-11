package com.yin.yzjcourse.Net;

import com.google.gson.reflect.TypeToken;

import java.util.HashMap;

import zhl.common.request.BaseApi;
import zhl.common.request.ZHLRequest;

public class PostVersionApi extends BaseApi {
    // https://mobile.11tohome.com/device/auth/check-app-version
    public static ZHLRequest postVersion(int task_id) {
        // 请求服务器参数
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("versionType","2");
        param.put("versionCode","51000");
        param.put("op_path", "auth.check-app-version");
        TypeToken<Object> typeToken = new TypeToken<Object>() {
        };
        ReaderResult<Object> result = new ReaderResult<>(typeToken);
        return (ZHLRequest) result.postEdu(param);
    }

    @Override
    public ZHLRequest getRequest(Object... params) {
        return postVersion((int)params[0]);
    }
}
