package com.yin.yzjcourse.DiyWidget;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class CanvasTextActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas_text);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_text_base, R.id.bt_text_four})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_text_base:
                startActivity(new Intent(this,DrawTextActivity.class));
                break;
            case R.id.bt_text_four:
                startActivity(new Intent(this,FourTextActivity.class));
                break;
        }
    }
}
