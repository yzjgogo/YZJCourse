package com.yin.yzjcourse.Base;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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

    @OnClick({R.id.bt_android_message, R.id.bt_title_menu,R.id.bt_vp,R.id.bt_span})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_android_message:
                startActivity(new Intent(this, AndroidMessageActivity.class));
                break;
            case R.id.bt_title_menu:
                startActivity(new Intent(this, TitleMenuActivity.class));
                break;
            case R.id.bt_vp:
                startActivity(new Intent(this, VPFirstActivity.class));
                break;
            case R.id.bt_span:
                startActivity(new Intent(this, SpanActivity.class));
                break;
        }
    }

//    @OnClick(R.id.bt_android_message,R.id.bt_)
//    public void onClickMessage() {
//        startActivity(new Intent(this, AndroidMessageActivity.class));
//    }
}
