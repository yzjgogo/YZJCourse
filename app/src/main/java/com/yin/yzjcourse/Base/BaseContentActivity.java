package com.yin.yzjcourse.Base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class BaseContentActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_content);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.bt_android_message)
    public void onClickMessage() {
        startActivity(new Intent(this, AndroidMessageActivity.class));
    }
}
