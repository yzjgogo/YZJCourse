package com.yin.yzjcourse.Net;

import com.google.gson.reflect.TypeToken;

import java.util.HashMap;

import zhl.common.request.BaseApi;
import zhl.common.request.ZHLRequest;

public class GetSaltApi extends BaseApi {
    //https://mobile.11tohome.com/device/auth/get-digest-salt
    public static ZHLRequest getSalt(int task_id) {
        // 请求服务器参数
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("op_path", "auth.get-digest-salt");
        TypeToken<Object> typeToken = new TypeToken<Object>() {
        };
        ReaderResult<Object> result = new ReaderResult<>(typeToken);
        return (ZHLRequest) result.getEdu(param);
    }

    @Override
    public ZHLRequest getRequest(Object... params) {
        return getSalt((int)params[0]);
    }
}
