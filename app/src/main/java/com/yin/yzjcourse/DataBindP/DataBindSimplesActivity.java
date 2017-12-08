package com.yin.yzjcourse.DataBindP;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dev.think.mylibrary.ModuleDataBindActivity;
import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;
import com.yin.yzjcourse.utils.DLog;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class DataBindSimplesActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_bind_simples);
        ButterKnife.bind(this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                DLog.eLog("我是一个普通的线程");
            }
        }).start();
        new Thread(() -> {
            DLog.eLog("我是一个lambda的线程");
        }).start();
    }


    @OnClick({R.id.bt_activity_db, R.id.bt_fragment_db, R.id.bt_module_db})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_activity_db:
//                startActivity(new Intent(this, DataBindActivity.class));
                break;
            case R.id.bt_fragment_db:
//                startActivity(new Intent(this, DataBindFragmentActivity.class));
                break;
            case R.id.bt_module_db:
                //在module中也要在gradle中配置
//                dataBinding {
//                enabled = true
//            }
//                startActivity(new Intent(this, ModuleDataBindActivity.class));
                break;
        }
    }
}
