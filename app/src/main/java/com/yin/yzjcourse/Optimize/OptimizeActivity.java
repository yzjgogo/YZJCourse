package com.yin.yzjcourse.Optimize;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class OptimizeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_optimize);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_one, R.id.bt_two})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_one:
                startActivity(new Intent(this,OptimizeOneActivity.class));
                break;
            case R.id.bt_two:
                startActivity(new Intent(this,OptimizeTwoActivity.class));
                break;
        }
    }
}
