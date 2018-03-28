package com.yin.yzjcourse.DiyWidget;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FourTextActivity extends BaseActivity {

    @BindView(R.id.tv_view)
    TextLineView tvView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four_text);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_left, R.id.bt_center, R.id.bt_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_left:
                tvView.setAlign(Paint.Align.LEFT);
                break;
            case R.id.bt_center:
                tvView.setAlign(Paint.Align.CENTER);
                break;
            case R.id.bt_right:
                tvView.setAlign(Paint.Align.RIGHT);
                break;
        }
    }
}
