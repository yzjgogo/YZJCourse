package com.yin.yzjcourse.Jetpack;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class JetpackHomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jetpack_home);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_lifecycle, R.id.bt_lifecycle2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_lifecycle:
                startActivity(new Intent(this, MyLCActivity.class));
                break;
            case R.id.bt_lifecycle2:
                break;
        }
    }
}