package com.yin.yzjcourse.Base.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.lidroid.xutils.util.LogUtils;
import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;
import com.yin.yzjcourse.Utils;
import com.yin.yzjcourse.utils.DLog;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyBlueToothActivity extends BaseActivity {
    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothDevice erjiDevice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bluetooth);
        // 蓝牙开关监听
        IntentFilter discoveryFilter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(mReceiver, discoveryFilter);
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        findViewById(R.id.bt_test).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                test();
            }
        });
        findViewById(R.id.bt_test2).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                test2();
            }
        });
        findViewById(R.id.bt_discovery).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                doDiscovery();
            }
        });
        findViewById(R.id.bt_match).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                toMatch();
            }
        });
        findViewById(R.id.bt_dis_match).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                toDisMatch();
            }
        });
    }

    private void doDiscovery() {
        DLog.eLog("执行1");
        boolean isStart = mBluetoothAdapter.startDiscovery();
        DLog.eLog("开始发现"+isStart);
    }

    private void test() {
//        if(mBluetoothAdapter == null){
//            // 说明此设备不支持蓝牙操作
//        }
        if (mBluetoothAdapter.isEnabled()) {
            DLog.eLog("蓝牙已经打开");
        }else {
            DLog.eLog("蓝牙已经关闭");
            //打开蓝牙方式1
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent,10086);

            //打开蓝牙方式2
            mBluetoothAdapter.enable();
        }
    }

    private void test2() {
        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        DLog.eLog("已配对的设备");
//        DLog.eLog(pairedDevices.toString());
    }
    // 创建一个接受 ACTION_FOUND 的 BroadcastReceiver
    private final BroadcastReceiver mReceiver = new BroadcastReceiver(){

        public void onReceive(Context context, Intent intent){
//            DLog.eLog("执行2");
            String action = intent.getAction();
            // 当 Discovery 发现了一个设备
            if(BluetoothDevice.ACTION_FOUND.equals(action)){
                // 从 Intent 中获取发现的 BluetoothDevice
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                BluetoothClass bluetoothClass = intent.getParcelableExtra(BluetoothDevice.EXTRA_CLASS);
                // 将名字和地址放入要显示的适配器中
                DLog.eLog("查询到新设备");
                DLog.eLog(device.getName()+" | "+device.getAddress());
//                DLog.eLog(bluetoothClass);
                if(!TextUtils.isEmpty(device.getName()) && device.getName().contains("TD-602")){
                    DLog.eLog("找到了----------");
                    DLog.eLog(device.getName() + " | " + device.getAddress());
                    mBluetoothAdapter.cancelDiscovery();
                    erjiDevice = device;
                }
            }
        }
    };

    private void toDisMatch() {
        try {
            Method method = BluetoothDevice.class.getMethod("removeBond");
            method.invoke(erjiDevice);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private void toMatch() {
        try {
            Method method = BluetoothDevice.class.getMethod("createBond");
            method.invoke(erjiDevice);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10086){
            DLog.eLog("请求打开蓝牙的结果");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mReceiver != null) {
            unregisterReceiver(mReceiver);
        }
    }
}
