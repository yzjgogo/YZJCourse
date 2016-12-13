package com.dev.think.mylibrary;

import android.content.Context;
import android.util.Log;

import com.alibaba.sdk.android.push.MessageReceiver;
import com.alibaba.sdk.android.push.notification.CPushMessage;

import java.util.Map;
import java.util.Set;

/**
 * Created by think on 2016/12/8.
 */

public class AliPushReceiver extends MessageReceiver {
    /**
     * 收到自定义消息
     * @param context
     * @param cPushMessage
     */
    @Override
    protected void onMessage(Context context, CPushMessage cPushMessage) {
        super.onMessage(context, cPushMessage);
        Log.e("yin","执行onMessage:"+cPushMessage.getAppId()+","+cPushMessage.getMessageId()+","+cPushMessage.getTitle()+","+cPushMessage.getContent());
    }

    /**
     * 收到通知栏
     * @param context
     * @param title
     * @param summary
     * @param extraMap
     */
    @Override
    protected void onNotification(Context context, String title, String summary, Map<String, String> extraMap) {
        super.onNotification(context, title, summary, extraMap);
        Log.e("yin","执行onNotification："+title+","+summary);
        Set<Map.Entry<String,String>> entrys=extraMap.entrySet();
        for(Map.Entry<String,String> entry:entrys){
            String key=entry.getKey();
            String value=entry.getValue();
            Log.e("yin","执行onNotification："+key+","+value);
        }
    }

    /**
     * 打开通知栏时
     * @param context
     * @param title
     * @param summary
     * @param extraMap
     */
    @Override
    protected void onNotificationOpened(Context context, String title, String summary, String extraMap) {
        super.onNotificationOpened(context, title, summary, extraMap);
        Log.e("yin","执行onNotificationOpened："+title+","+summary+","+extraMap);
    }

    /**
     * 打开无跳转逻辑(open=4)通知时回调该方法
     * @param context
     * @param title
     * @param summary
     * @param extraMap
     */
    @Override
    protected void onNotificationClickedWithNoAction(Context context, String title, String summary, String extraMap) {
        super.onNotificationClickedWithNoAction(context, title, summary, extraMap);
        Log.e("yin","执行onNotificationClickedWithNoAction："+title+","+summary+","+extraMap);
    }

    /**
     * 删除通知时回调该方法
     * @param context
     * @param messageId
     */
    @Override
    protected void onNotificationRemoved(Context context, String messageId) {
        super.onNotificationRemoved(context, messageId);
        Log.e("yin","执行onNotificationRemoved："+messageId);
    }

    /**
     * 当用户创建自定义通知样式，并且设置推送应用内到达不创建通知弹窗时调用该回调，且此时不调用onNotification回调
     * @param context
     * @param title
     * @param summary
     * @param extraMap
     * @param openType
     * @param openActivity
     * @param openUrl
     */
    @Override
    protected void onNotificationReceivedInApp(Context context, String title, String summary, Map<String, String> extraMap, int openType, String openActivity, String openUrl) {
        super.onNotificationReceivedInApp(context, title, summary, extraMap, openType, openActivity, openUrl);
        Log.e("yin","执行onNotificationReceivedInApp:"+title+","+summary+","+openType+","+openActivity+","+openUrl);
        Set<Map.Entry<String,String>> entrys=extraMap.entrySet();
        for(Map.Entry<String,String> entry:entrys){
            String key=entry.getKey();
            String value=entry.getValue();
            Log.e("yin","执行onNotificationReceivedInApp："+key+","+value);
        }
    }
}
