package com.yin.yzjcourse.Net;

import com.google.gson.reflect.TypeToken;

import java.util.HashMap;

import zhl.common.request.BaseApi;
import zhl.common.request.ZHLRequest;
public class SubmitNewBieTaskApi extends BaseApi {
    // https://mobile.11tohome.com/device/auth/check-app-version
    //https://mobile.11tohome.com/device/auth/get-digest-salt
//    public static ZHLRequest submitNewBieTask(int task_id) {
//        // 请求服务器参数
//        HashMap<String, Object> param = new HashMap<String, Object>();
//        param.put("versionType","2");
//        param.put("versionCode","51000");
//        param.put("op_path", "auth.check-app-version");
//        TypeToken<Object> typeToken = new TypeToken<Object>() {
//        };
//        ReaderResult<Object> result = new ReaderResult<>(typeToken);
//        return (ZHLRequest) result.postEdu(param);
//    }
    public static ZHLRequest submitNewBieTask(int task_id) {
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
        return submitNewBieTask((int)params[0]);
    }
}
