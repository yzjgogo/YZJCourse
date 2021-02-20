package com.yin.yzjcourse.DiyWidget;

import android.content.Intent;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;
import com.yin.yzjcourse.utils.DLog;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Rotate3DActivity extends BaseActivity {
    @BindView(R.id.iv)
    ImageView iv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotate_3d);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {

    }

    @OnClick({R.id.bt_start})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_start:

                // 计算中心点（这里是使用view的中心作为旋转的中心点）
                final float centerX = iv.getWidth() / 2.0f;
                final float centerY = iv.getHeight() / 2.0f;

                //括号内参数分别为（上下文，开始角度，结束角度，x轴中心点，y轴中心点，深度，是否扭曲）
                final Rotate3dAnimation rotation = new Rotate3dAnimation(Rotate3DActivity.this, 0, 180, centerX, centerY, 0f, true);

//                rotation.setDuration(30000);                         //设置动画时长
                rotation.setDuration(10000);                         //设置动画时长
                rotation.setFillAfter(true);                        //保持旋转后效果
                rotation.setInterpolator(new LinearInterpolator());    //设置插值器
                iv.startAnimation(rotation);
                break;
        }
    }
}
