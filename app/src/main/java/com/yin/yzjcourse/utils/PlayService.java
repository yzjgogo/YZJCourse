package com.yin.yzjcourse.utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;


import com.yin.yzjcourse.MainActivity;
import com.yin.yzjcourse.MyApplication;
import com.yin.yzjcourse.R;
import com.yin.yzjcourse.bean.EnglishSentenceEntity;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;
import zhl.common.oauth.OauthApplicationLike;
import zhl.common.utils.CommonApkShare;
import zhl.common.utils.DebugUrl;
import zhl.common.utils.JsonHp;

public class PlayService extends Service {
//    private static final String WS = "ws://192.168.3.211/homework/record?token=e2ea66236d7369ceacce2b2ac98337ac861fff988375f7f296fac2e350a3c88a&scope=com.zhl.xby.web&class_id=6015757";
//    private static final String WS = "ws://192.168.3.153:8000/ws"; //debug
////    private static final String WS = "ws://101.132.26.110:8000/ws"; //110
//
//    private WebSocket webSocket;
//    private WebSocketCallback webSocketCallback;
//    private int reconnectTimeout = 18000;
//    private boolean connected = false;

//    private Timer mTimer;
//    private TimerTask mTimerTask;

//    private HeartChecker heartChecker;
    NotificationCompat.Builder notification; //创建服务对象
    NotificationManager manager;
    private boolean isShowNotification = false;
    private int currentPosition = 0;
    private List<EnglishSentenceEntity> sentenceEntityList;
    private Handler handler = new Handler(Looper.getMainLooper());

//    @Nullable
//    @Override
//    public IBinder onBind(Intent intent) {
//        return new LocalBinder();
//    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        showNotification();

    }

    public void showNotification() {
        if (notification == null || manager == null) {
            manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            String ID = "com.example.lalala";	//这里的id里面输入自己的项目的包的路径
            String NAME = "Channel One";
            Intent intent = new Intent(PlayService.this, MainActivity.class);
            PendingIntent pendingIntent;
            if (Build.VERSION.SDK_INT >= 31) {
                pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);
            } else {
                pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
            }
//            NotificationCompat.Builder notification; //创建服务对象
//            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel(ID, NAME, manager.IMPORTANCE_HIGH);
                channel.enableLights(true);
                channel.setShowBadge(true);
                channel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
                manager.createNotificationChannel(channel);
                notification = new NotificationCompat.Builder(PlayService.this).setChannelId(ID);
            } else {
                notification = new NotificationCompat.Builder(PlayService.this);
            }
            notification.setContentTitle("yzjCourse")
                    .setContentText("音频播放中")
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                    .setContentIntent(pendingIntent)
                    .build();
            Notification notification1 = notification.build();
            startForeground(1,notification1);
            isShowNotification = true;
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        sentenceEntityList = MediaPlayerSingleton.getInstance().getSentenceList();
        startPlay();
        return START_STICKY;
    }

    private void startPlay() {
        try {
            String order = String.valueOf(currentPosition+1);
            if((currentPosition+1)<10){
                order = ("0"+order);
            }
            TranslationRequest.translate(order, sentenceEntityList.get(currentPosition).english, new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    if((System.currentTimeMillis()- MyApplication.startTimes)>MyApplication.durationTime*60*1000){
                        DLog.eLog("时间到了停止播放");
                    }else {
                        playNext();
                    }
                }
            });
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private void playNext() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                playNextNow();
            }
        }, MyApplication.intervalTime * 1000);
    }

    private void playNextNow() {
        if(currentPosition == (sentenceEntityList.size()-1)){
            DLog.eLog("播放一遍了");
            currentPosition = 0;
        }else {
            currentPosition++;
        }
        startPlay();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
