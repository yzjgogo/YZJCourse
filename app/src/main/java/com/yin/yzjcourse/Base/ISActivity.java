package com.yin.yzjcourse.Base;

import android.content.Intent;
import android.os.Bundle;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ISActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_is);
        ButterKnife.bind(this);
    }

    /**
     * 发起多个后台任务，后台任务会顺序执行，执行完所有的后台任务后IntentService才销毁
     */
    private void runIntentService() {
        Intent service = new Intent(this, LocalIntentService.class);
        service.putExtra("task_action", "com.ryg.action.TASK1");
        startService(service);
        service.putExtra("task_action", "com.ryg.action.TASK2");
        startService(service);
        service.putExtra("task_action", "com.ryg.action.TASK3");
        startService(service);
    }

    @OnClick(R.id.bt_start_is)
    public void onClick() {
        runIntentService();
    }
}
