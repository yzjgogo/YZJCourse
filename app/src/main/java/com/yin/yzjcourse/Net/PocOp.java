package com.yin.yzjcourse.Net;


import zhl.common.request.ApiClass;
import zhl.common.request.BasePocConfig;

public class PocOp extends BasePocConfig {
    @ApiClass(ApiClassName = PostVersionApi.class)
    public static final int POST_VERSION_API = 1;
    @ApiClass(ApiClassName = GetSaltApi.class)
    public static final int GET_SALT_API = 2;
}
