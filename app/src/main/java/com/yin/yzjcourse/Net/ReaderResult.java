package com.yin.yzjcourse.Net;

import android.text.TextUtils;

import com.android.volley.Request;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.Map;

import zhl.common.request.AbsResult;
import zhl.common.request.ZHLAbcPayRequest;
import zhl.common.request.ZHLRequest;
import zhl.common.request.ZHLUploadRequest;
import zhl.common.utils.DebugUrl;

/**
 * Created by zqs on 16/8/10 下午2:25.
 * Copyright © 2016年 BeiJingZhiHuiLiu. All rights reserved.
 */
public class ReaderResult<T> extends AbsResult<T> {

    public ReaderResult() {
        super();
    }

    public ReaderResult(Class<T> clsT) {
        super(clsT);
    }

    public ReaderResult(TypeToken<T> token) {
        super(token);
    }

    @Override
    public String getRequestClientKey() {
        return null;
    }

    @Override
    public String getRequestClientId() {
        return null;
    }

    @Override
    public String getRequestSecret() {
        return null;
    }

    @Override
    public int getRequestBusinessId() {
        return 0;
    }

    @Override
    public int getEditionId() {
        return 0;
    }

//    @Override
//    public String getRequestClientKey() {
//        return ConfigKey.CLIENT_KEY;
//    }
//
//    @Override
//    public String getRequestClientId() {
//        return ConfigKey.CLIENT_ID;
//    }
//
//    @Override
//    public String getRequestSecret() {
//        return ConfigKey.CLIENT_SECRET;
//    }
//
//    @Override
//    public int getRequestBusinessId() {
//        return ConfigKey.BUSINESS_ID;
//    }
//
//    @Override
//    public int getEditionId() {
//        return OwnApplicationLike.getEditionId();
//    }

    /**
     * 用户中心接口
     */
    public Request postUser(HashMap<String, Object> param) {
        //        if (BuildConfig.IS_JAVA_INTERFACE) {// TODO: 2018/4/23 0023  为了兼容学生端.net 和 java 接口的切换
        return post(param, DebugUrl.getEduUrl());
        //        } else {
        //            if (param.containsKey("op_path")) {
        //                return post(param, DebugUrl.getEduUrl());
        //            }
        //            return post(param,DebugUrl.getOauth2Api());
        //        }
    }

    /***
     * 支付中心接口
     */
    public Request postPay(HashMap<String, Object> param) {
        if (param.containsKey("op_path")) {
            return post(param, DebugUrl.getEduUrl());
        }
        return post(param, DebugUrl.getPayUrl());
    }


    /***
     * 消息中心接口
     */
    public Request postMessage(HashMap<String, Object> param) {
        return post(param, DebugUrl.getEduUrl());
    }

    /**
     * 公共资源库接口（学校数据等）
     */
    public Request postPublic(HashMap<String, Object> param) {
        return post(param, DebugUrl.getEduUrl());
    }

    public Request postDataCollect(HashMap<String, Object> param) {
        return post(param, DebugUrl.getDataCollectUrl());
    }

    /**
     * 课堂宝接口
     */
    public Request postKeTangBao(HashMap<String, Object> param) {
        return post(param, DebugUrl.getEduUrl());
    }

    /**
     * edu 接口
     */
    public Request postEdu(HashMap<String, Object> param) {
        return post(param, DebugUrl.getEduUrl());
    }
    public Request getEdu(HashMap<String, Object> param) {
        return get(param, DebugUrl.getEduUrl());
    }

    /**
     * 临时测试
     * @param param
     * @return
     */
    public Request postEduLocal(HashMap<String, Object> param) {
        //http://192.168.2.29:8099/zhl-english/api/prop/visualize-prop/get-prop-using
//        return post(param, "http://192.168.2.29:8099/zhl-english/api");
//        return post(param, "http://192.168.2.72:8099/zhl-english/api");
        //return post(param, "http://192.168.2.83:8191/zhl-english/api");
//        return post(param, "http://192.168.1.14:8099/zhl-english/api");
//        return post(param, "http://222.212.88.30:8113/lvww-8099/zhl-english/api");
        return post(param, "http://192.168.2.9:8099/zhl-english/api");
    }


    public Request postChineseLocal(HashMap<String, Object> param) {
        return post(param, "http://192.168.1.14:8010/zhl-chinese/api");
    }

    public Request postQKLocal(HashMap<String, Object> param) {
        return post(param, "http://192.168.2.29:8085/zhl-qiaokao/api");
    }

    /**
     * 桥考 接口
     */
    public Request postQK(HashMap<String, Object> param) {
        return post(param, DebugUrl.getQKUrl());
    }

    /**
     * qk 接口 巧考课件-自己拼接前面请求地址
     */
    public Request postCourseWare(HashMap<String, Object> param) {
        return get(param, DebugUrl.getCourseWareUrl());
    }


    /**
     * 国际音标 接口
     */
    public Request postCommunity(HashMap<String, Object> param) {
        return post(param, DebugUrl.getCommunityUrl());
    }

    /**
     * upload 接口
     */
    public Request uploadEdu(HashMap<String, Object> param) {
        return upload(param, DebugUrl.getUploadUrl());
    }

    /**
     * post 多音频地址合成一个地址接口
     */
    public Request postAudio(HashMap<String, Object> param) {
        return post(param, DebugUrl.getUploadUrl());
    }

    /**
     * 作业资源库接口
     */
    public Request postHomework(HashMap<String, Object> param) {
        return post(param, DebugUrl.getEduUrl());
    }

    /**
     * 数学接口
     *
     * @param param
     * @return
     */
    public Request postMath(HashMap<String, Object> param) {
        if (null != param) {
            String opPath = (String) param.get("op_path");
            if (!TextUtils.isEmpty(opPath) && !opPath.startsWith("math.")) {
                param.put("op_path", "math." + opPath);
            }
        }
        return post(param, DebugUrl.getNewMathUrl());
    }


    public Request postMathLocal(HashMap<String, Object> param) {
        if (null != param) {
            String opPath = (String) param.get("op_path");
            if (!TextUtils.isEmpty(opPath) && !opPath.startsWith("math.")) {
                param.put("op_path", "math." + opPath);
            }
        }
        return post(param, "http://192.168.1.14:8088/zhl-math/api");
    }

    public Request postChinese(HashMap<String, Object> param) {
        return post(param, DebugUrl.getNewChineseUrl());
    }

    /***
     * 资源中心站点(apk更新等数据)
     */
    public Request postResource(HashMap<String, Object> param) {
        return post(param, DebugUrl.getEduUrl());
    }
    public Request postResourceLocal(HashMap<String, Object> param) {
        return post(param, "http://192.168.1.2:8099/zhl-english/api");
    }

    public ZHLRequest post(Map<String, Object> param, String url) {
        String newUrl;
        if (param.get("op") != null) {
            newUrl = url;
//            newUrl = URLConfig.getURL(url, param.get("op").toString());
//            if (newUrl == null) {
//                newUrl = url;
//            }
        } else {
            newUrl = url;
        }
        if (param.get("op_path") != null) {
            String opPath = param.get("op_path").toString();
            if (!TextUtils.isEmpty(opPath)) {
                newUrl = url + "/" + opPath.replace(".", "/");
            }
        }
        return super.post(param, newUrl);
    }

    public ZHLRequest get(Map<String, Object> param, String url) {
        String newUrl = url;
        if (param.get("op_path") != null) {
            String opPath = param.get("op_path").toString();
            if (!TextUtils.isEmpty(opPath)) {
                newUrl = url + "/" + opPath.replace(".", "/");
            }
        }
        return super.get(param, newUrl);
    }


    public ZHLRequest upload(Map<String, Object> param, String url) {
        String newUrl = url;
        if (param.get("op_path") != null) {
            String opPath = param.get("op_path").toString();
            if (!TextUtils.isEmpty(opPath)) {
                newUrl = url + "/" + opPath.replace(".", "/");
            }
        }
        return new ZHLUploadRequest(newUrl, param, this);
    }

    public ZHLRequest abcPay(Map<String, Object> param, String url) {
        return new ZHLAbcPayRequest(url, param, this);
    }
}
