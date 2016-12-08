package com.yin.yzjcourse.aliPushPackage;

import android.content.Context;
import android.util.Log;

import com.alibaba.sdk.android.push.MessageReceiver;
import com.alibaba.sdk.android.push.notification.CPushMessage;

import java.util.Map;

/**
 * Created by think on 2016/12/8.
 */

public class AliPushReceiver extends MessageReceiver {
    @Override
    protected void onMessage(Context context, CPushMessage cPushMessage) {
        super.onMessage(context, cPushMessage);
        Log.e("yin","执行onMessage:"+cPushMessage.getAppId()+","+cPushMessage.getMessageId()+","+cPushMessage.getTitle()+","+cPushMessage.getContent());
    }

    @Override
    protected void onNotification(Context context, String title, String summary, Map<String, String> extraMap) {
        super.onNotification(context, title, summary, extraMap);
        Log.e("yin","执行onNotification："+title+","+summary);
    }

    @Override
    protected void onNotificationOpened(Context context, String title, String summary, String extraMap) {
        super.onNotificationOpened(context, title, summary, extraMap);
    }

    @Override
    protected void onNotificationClickedWithNoAction(Context context, String title, String summary, String extraMap) {
        super.onNotificationClickedWithNoAction(context, title, summary, extraMap);
    }

    @Override
    protected void onNotificationRemoved(Context context, String messageId) {
        super.onNotificationRemoved(context, messageId);
    }

    @Override
    protected void onNotificationReceivedInApp(Context context, String title, String summary, Map<String, String> extraMap, int openType, String openActivity, String openUrl) {
        super.onNotificationReceivedInApp(context, title, summary, extraMap, openType, openActivity, openUrl);
    }
}
