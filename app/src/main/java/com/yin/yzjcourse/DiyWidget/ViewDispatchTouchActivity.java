package com.yin.yzjcourse.DiyWidget;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 参考百度脑图：ViewGroup触摸事件
 */
public class ViewDispatchTouchActivity extends BaseActivity{

    @BindView(R.id.my_btn)
    TouchButton myBtn;
    @BindView(R.id.mylayout)
    TouchLinearLayout mylayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_dispatch_touch);
        ButterKnife.bind(this);
    }
}
