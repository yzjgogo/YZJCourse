package com.yin.yzjcourse.utils;

import android.media.MediaPlayer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MediaPlayerSingleton {
    private volatile static MediaPlayerSingleton uniqueInstance;
    private MediaPlayer mediaPlayer;

    private MediaPlayerSingleton() {
        mediaPlayer = new MediaPlayer();
    }

    public static MediaPlayerSingleton getInstance() {
        if (uniqueInstance == null) {
            synchronized (MediaPlayerSingleton.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new MediaPlayerSingleton();
                }
            }
        }
        return uniqueInstance;
    }

    public void play(String url){
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepare();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mediaPlayer.start();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public static void destroySingleton() {
//        if (uniqueInstance != null) {
//            if (uniqueInstance.tasks != null) {
//                uniqueInstance.tasks.clear();
//            }
//            uniqueInstance = null;
//        }
//    }
}
