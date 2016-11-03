package com.yin.yzjcourse;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by think on 2016/11/2.
 */

public class FakeForeService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Log.i("yin","FakeForeService执行onCreate");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("yin","FakeForeService执行onStartCommand");



//        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
//        builder.setPriority(Notification.PRIORITY_MIN);
        Notification notification = new Notification();
        notification.flags = Notification.FLAG_ONGOING_EVENT;
        notification.flags |= Notification.FLAG_NO_CLEAR;
        notification.flags |= Notification.FLAG_FOREGROUND_SERVICE;
        startForeground(1, notification);
//        stopSelf(startId);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.i("yin","FakeForeService执行onDestroy");
        stopForeground(true);
        super.onDestroy();
    }
}
