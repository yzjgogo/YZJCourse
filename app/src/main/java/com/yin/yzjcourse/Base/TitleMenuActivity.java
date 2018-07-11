package com.yin.yzjcourse.Base;

import android.content.Intent;
import android.os.Bundle;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class TitleMenuActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title_menu);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.bt_diy_menu)
    public void onClick() {
        startActivity(new Intent(this, DiyMenuActivity.class));
    }
}
