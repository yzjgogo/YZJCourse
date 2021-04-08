package com.yin.yzjcourse.ForeService;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import androidx.annotation.Nullable;
import android.util.Log;

/**
 * Created by think on 2016/11/2.
 */

public class RealForeService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Log.i("yin","RealForeService执行onCreate");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("yin","RealForeService执行onStartCommand");

//        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
//        builder.setPriority(Notification.PRIORITY_MIN);
        Notification notification = new Notification();
        notification.flags = Notification.FLAG_ONGOING_EVENT;
        notification.flags |= Notification.FLAG_NO_CLEAR;
        notification.flags |= Notification.FLAG_FOREGROUND_SERVICE;
        startForeground(1, notification);

        Intent myIntent = new Intent(this, FakeForeService.class);
        stopService(myIntent);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.i("yin","RealForeService执行onDestroy");
//        stopForeground(true);
        super.onDestroy();
    }
}
