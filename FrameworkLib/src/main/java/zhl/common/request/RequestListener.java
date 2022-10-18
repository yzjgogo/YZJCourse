package zhl.common.request;


/**
 * Created by zqs on 16/8/10 下午2:18.
 * Copyright © 2016年 BeiJingZhiHuiLiu. All rights reserved.
 */
public interface RequestListener {

    void onSuccess(ZHLRequest request, AbsResult result);

    void onFailure(ZHLRequest request, String msg);
}
