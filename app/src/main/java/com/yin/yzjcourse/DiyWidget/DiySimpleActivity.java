package com.yin.yzjcourse.DiyWidget;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.DiyWidget.QQDelete.QQDeleteActivity;
import com.yin.yzjcourse.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class DiySimpleActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diy_simple);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_qq_delete, R.id.bt_shade})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_qq_delete:
                startActivity(new Intent(this, QQDeleteActivity.class));
                break;
            case R.id.bt_shade:
                startActivity(new Intent(this, WeightSimpleActivity.class));
                break;
        }
    }
}
