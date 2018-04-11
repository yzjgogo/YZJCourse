package com.yin.yzjcourse.Optimize;

import android.os.Bundle;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;

/**
    参考android_monitor_usage.png
 android_device_monitor_heap_usage.png
 allocation_tracker_usage.png
 */
public class OptimizeOneActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_optimize_one);

        CommUtil commUtil = CommUtil.getInstance(this);
    }
}
