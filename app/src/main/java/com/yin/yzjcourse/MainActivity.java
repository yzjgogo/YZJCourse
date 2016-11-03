package com.yin.yzjcourse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.bt_start_fore_service).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_start_fore_service:
                /**
                 * 1：正常启动前台服务的流程：在Service的onStartCommmand方法中调用startForeground(int id, Notification notification)；
                 *    在Service的onDestroy方法中调用stopForeground(boolean removeNotification)。
                 * 2：根据Android官方的初衷，正常启动前台服务，顶部的通知栏是肯定存在的。但是这里我们使用了一种取巧的方法：
                 *    a:先用startForeground(int id, Notification notification)启动一个假的Service，在这个假的Service的onDestroy方法中调用
                 *      stopForeground(boolean removeNotification)。
                 *    b:再用相同的通知栏id，相同的通知栏启动我们实际要用的真的Service，启动成功后立即调用stopService(myIntent);销毁掉之前假的Service。
                 *      这个真的Service的onDestroy方法不再调用stopForeground(boolean removeNotification)。
                 *    c:通过a，b两部操作，这个真的Service虽然是前台服务，但是并不显示通知栏，这样就实现了前台服务但不显示通知栏的目的。
                 *  3：通过测试，这种方法实现的前台服务，无论是按home键退到后台还是按返回键清空任务栈，该服务都不会被系统回收，及时内存严重不足或通过第三方清理软件
                 *     清理，该前台服务仍然会一直运行不会被回收。可进设置里正在运行的程序里查看。但是部分手机(华为和小米会回收，三星不会)如果进任务栏关掉
                 *     或滑掉该进程，该前台服务就会被回收。还没有验证是否可通过多进程实现即时关闭进程也不被回收。
                 */
                Intent fakeIntent = new Intent(this, FakeForeService.class);
                startService(fakeIntent);
                Intent realIntent = new Intent(this, RealForeService.class);
                startService(realIntent);
                break;
        }
    }
}
