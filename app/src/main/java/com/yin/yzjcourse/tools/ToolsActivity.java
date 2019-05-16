package com.yin.yzjcourse.tools;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Toast;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;
import com.yin.yzjcourse.utils.DLog;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ToolsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tools);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_heap_size,R.id.bt_format_ms,R.id.bt_count_down,R.id.bt_crash_catch})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_heap_size:
                ActivityManager manager = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
                int heapSize = manager.getMemoryClass();//单位是Mb
                DLog.eLog("分配的内存大小:"+heapSize+"Mb");
                break;
            case R.id.bt_format_ms:
                SimpleDateFormat formatter = new SimpleDateFormat("HH小时mm分");
                formatter.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));//必须设置时区，否则有8个小时的差距，如果不设置时区结果就是11小时17分钟
                String hms = formatter.format(3*60*60*1000+17*60*1000);//3小时17分钟
                DLog.eLog("格式化后："+hms);
                break;
            case R.id.bt_count_down:
                //第一个参数：一共倒计时多少ms，注意是毫秒
                //第二个参数：每隔多少ms倒计时一次，注意是毫秒
                CountDownTimer timer = new CountDownTimer(60*1000,1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {//参数是剩余多少毫秒，CountDownTimer第一个参数的剩余
                        //每倒计时一次就调用一次，多用于更新UI
                        long s = millisUntilFinished/1000;
                        DLog.eLog("当前："+s+"秒");
                        if (s == 50l){
                            DLog.eLog("去取消");
//                            cancel();
                        }
                    }

                    @Override
                    public void onFinish() {
                        //倒计时结束的回调
                        DLog.eLog("倒计时结束");
                    }
                }.start();//调用start()才能开始倒计时
//                timer.cancel();别忘了用完取消,如果倒计时没完成久取消，不会调用onFinish
                break;
            case R.id.bt_crash_catch:
                Toast.makeText(ToolsActivity.this,"只有两个类，看一下就行了",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
