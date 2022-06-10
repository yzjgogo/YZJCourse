package com.yin.yzjcourse.Optimize;

import android.os.Bundle;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;
import com.yin.yzjcourse.utils.DLog;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ObjectPoolActivity extends BaseActivity {
    private UserPool userPool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_pool);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.bt_get)
    public void onGet() {
        init();
    }

    private void init() {
        //调用测试
        userPool = UserPool.obtain();
        DLog.eLog("修改前=" + userPool.name);

        userPool.name = "修改内容";
        userPool.recycle();//使用完毕务必要将对象归还到对象池


        UserPool userAgain = UserPool.obtain();
        DLog.eLog("修改后=" + userAgain.name);
        userPool.recycle();//使用完毕务必要将对象归还到对象池
    }
}
