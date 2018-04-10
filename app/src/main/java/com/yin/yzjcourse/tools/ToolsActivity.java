package com.yin.yzjcourse.tools;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;
import com.yin.yzjcourse.utils.DLog;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ToolsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tools);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_heap_size})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_heap_size:
                ActivityManager manager = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
                int heapSize = manager.getMemoryClass();//单位是Mb
                DLog.eLog("分配的内存大小:"+heapSize+"Mb");
                break;
//            case R.id.bt_heap:
//                break;
        }
    }
}
