package com.yin.yzjcourse.BroadcastPackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import com.yin.yzjcourse.ForeService.RealForeService;

/**
 * Created by hunter on 2015/10/9.
 */
public class NetStatusBroadcastReceiver extends BroadcastReceiver {
    private ConnectivityManager connectivityManager;
    private NetworkInfo netInfo;

    @Override
    public void onReceive(Context context, Intent intent) {
//        Toast.makeText(context,"网络变化",Toast.LENGTH_SHORT).show();
        Log.e("yin", "NetStatusBroadcastReceiver onReceive");
        String action = intent.getAction();
        if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            netInfo = connectivityManager.getActiveNetworkInfo();
            if (netInfo != null && netInfo.isAvailable()) {
                if (netInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                    Log.e("yin", "NetStatusBroadcastReceiver onReceive, TYPE_WIFI");

                    Intent realIntent = new Intent(context, RealForeService.class);
                    context.startService(realIntent);
                } else if (netInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                    Log.e("yin", "NetStatusBroadcastReceiver TYPE_MOBILE");
                } else {
                    Log.e("yin", "NetStatusBroadcastReceiver OFFLINE");
                }
            } else {
                Log.e("yin", "NetStatusBroadcastReceiver OFFLINE 2");
            }
        }
    }

}
