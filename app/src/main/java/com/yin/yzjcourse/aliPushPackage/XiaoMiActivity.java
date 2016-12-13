package com.yin.yzjcourse.aliPushPackage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

import com.alibaba.sdk.android.push.MiPushSystemNotificationActivity;

import java.util.Map;

/**
 * Created by think on 2016/12/12.
 */

public class XiaoMiActivity extends MiPushSystemNotificationActivity {

    /**
     * 用于其他Activty跳转到该Activity
     * @param context
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, XiaoMiActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String summary = intent.getStringExtra("summary");
        Log.e("yin","普通推送通道弹出：title:"+title+",summary:"+summary);
    }

    @Override
    protected void onMiPushSysNoticeOpened(String title, String content, Map<String, String> extraMap) {
        Log.e("yin", "小米辅助弹窗通道打开Receive XiaoMi notification, title: " + title + ", content: " + content + ", extraMap: " + extraMap);
    }
}
