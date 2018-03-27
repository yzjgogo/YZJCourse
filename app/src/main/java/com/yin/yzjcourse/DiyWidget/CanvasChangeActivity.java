package com.yin.yzjcourse.DiyWidget;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class CanvasChangeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas_change);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_translate, R.id.bt_rotate, R.id.bt_scale})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_translate:
                startActivity(new Intent(this,CanvasTranslateActivity.class));
                break;
            case R.id.bt_rotate:
                startActivity(new Intent(this,CanvasRotateActivity.class));
                break;
            case R.id.bt_scale:
                startActivity(new Intent(this,CanvasScaleActivity.class));
                break;
        }
    }
}
